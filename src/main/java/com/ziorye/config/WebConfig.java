package com.ziorye.config;

import com.ziorye.converter.CustomMessageConverter;
import com.ziorye.converter.StringToPetConverter;
import com.ziorye.interceptor.LoginInterceptor;
import com.ziorye.interceptor.LoginPageViewCountInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author ziorye
 */
@Configuration
public class WebConfig {

    /**
     * 由于在 LoginPageViewCountInterceptor 中通过 @Autowired 自动装配了 StringRedisTemplate
     *     所以，不能通过 registry.addInterceptor(new LoginPageViewCountInterceptor())... 的方式注册 Interceptor
     *     应该使用 @Autowired 从 Spring 容器中获取
     *
     * Filter 与 Interceptor 几乎拥有相同的功能，平时应该用哪种？
     * - Filter 是 Servlet 规范定义的原生组件，脱离 Spring 依旧可以使用
     * - Interceptor 是 Spring 提供的功能，可以使用 Spring 的自动装配等功能
     */
    @Autowired
    LoginPageViewCountInterceptor loginPageViewCountInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new StringToPetConverter());
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new CustomMessageConverter());
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns(
                                /* 这一行是增加 LoginInterceptor 之前的路由，忽略它们，避免影响之前的测试代码。TODO: Mock user login before testing, UserControllerTest 类中增加了一段模拟登录的代码，但是应该有更好的方法，待改进 */
                                "/hello", "/my", "/yaml", "/person", "/person/**", "/requestHeader", "/requestParam", "/requestBody", "/go", "/success", "/mapAndModel", "/checkAttribute", "/savePerson", "/getPerson",

                                /* 登录页面，静态资源不需要被拦截 */
                                "/login", "/resources/**", "/webjars/**"
                        );

                registry.addInterceptor(loginPageViewCountInterceptor)
                        .addPathPatterns("/login");
            }
        };
    }
}
