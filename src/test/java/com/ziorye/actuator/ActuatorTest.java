package com.ziorye.actuator;

import com.ziorye.controller.TestBaseControllerWithMockHttpSession;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author ziorye
 */
public class ActuatorTest extends TestBaseControllerWithMockHttpSession {
    @Value("${info.name}")
    private String name;

    @Value("${info.version}")
    private String version;

    @Test
    @DisplayName("测试自定义 health 端点健康监控检查包含的组件")
    void testCustomHealthIndicator() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/health")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("components.custom.status").value("UP"))
        ;
    }

    @Test
    @DisplayName("测试 info 端点返回的内容")
    void testInfoEndpoint() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/info")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("version").value(version))
        ;
    }

    @Test
    @DisplayName("测试自定义 info 端点返回的内容")
    void testCustomInfoContributor() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/info")
                .session(this.session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("customKey").value("customValue"))
                .andExpect(MockMvcResultMatchers.jsonPath("example.key").value("value"))
        ;
    }

    @Test
    @DisplayName("测试增加自定义的指标到 metrics 端点")
    void testAddCustomCounterToMeterRegistry() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/metrics")
                .session(this.session)
        )
                /**
                 * {
                 *     "names": [
                 *         "custom.customMetrics.viewsCount",
                 *         "http.server.requests",
                 *         "tomcat.sessions.active.current",
                 *         ...
                 *     ]
                 * }
                 */
                .andExpect(MockMvcResultMatchers.jsonPath("names").value(Matchers.hasItem("custom.customMetrics.viewsCount")))
        ;

        // counter.increment()
        mvc.perform(MockMvcRequestBuilders.get("/custom-metrics").session(session));
        // counter.increment()
        mvc.perform(MockMvcRequestBuilders.get("/custom-metrics").session(session));

        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/metrics/custom.customMetrics.viewsCount")
                .session(this.session)
        )
                /**
                 * {
                 *     "name": "custom.customMetrics.viewsCount",
                 *     "description": null,
                 *     "baseUnit": null,
                 *     "measurements": [
                 *         {
                 *             "statistic": "COUNT",
                 *             "value": 2
                 *         }
                 *     ],
                 *     "availableTags": []
                 * }
                 */
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("custom.customMetrics.viewsCount"))
                .andExpect(MockMvcResultMatchers.jsonPath("measurements[0]").value(Matchers.hasEntry("statistic", "COUNT")))
                .andExpect(MockMvcResultMatchers.jsonPath("measurements[0]").value(Matchers.hasEntry("value", 2.0)))
        ;
    }

    @Test
    @DisplayName("测试自定义端点")
    void testCustomEndpoint() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/actuator")
                .session(this.session)
        )
                /**
                 * {
                 *     "_links": {
                 *         "self": {
                 *             "href": "http://localhost:8080/actuator",
                 *             "templated": false
                 *         },
                 *         "custom-endpoint": {
                 *             "href": "http://localhost:8080/actuator/custom-endpoint",
                 *             "templated": false
                 *         },
                 *         ...
                 *     }
                 * }
                 */
                .andExpect(MockMvcResultMatchers.jsonPath("_links.custom-endpoint.href").value(Matchers.endsWith("actuator/custom-endpoint")))
        ;


        mvc.perform(MockMvcRequestBuilders
                .get("/actuator/custom-endpoint")
                .session(this.session)
        )
                /**
                 * {
                 *     "CustomEndpoint": "readOperation"
                 * }
                 */
                .andExpect(MockMvcResultMatchers.jsonPath("CustomEndpoint").value("readOperation"))
        ;
    }
}
