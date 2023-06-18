package com.ziorye.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author ziorye
 */
@Data
@ToString
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;
}
