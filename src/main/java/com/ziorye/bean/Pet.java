package com.ziorye.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ziorye
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    private String name;
    private Double weight;
}
