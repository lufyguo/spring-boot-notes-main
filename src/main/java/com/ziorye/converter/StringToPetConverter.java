package com.ziorye.converter;

import com.ziorye.bean.Pet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

/**
 * @author ziorye
 */
public class StringToPetConverter implements Converter<String, Pet> {
    @Override
    public Pet convert(String source) {
        // 假设提交过来的字符串是用逗号分隔的，形如：petName,petWeight
        // 逗号左边代表 Pet 的 name 属性值；逗号右边代表 Pet 的 weight 数值
        if (StringUtils.isNotEmpty(source) && source.contains(",")) {
            Pet pet = new Pet();
            String[] split = source.split(",");
            pet.setName(split[0]);
            pet.setWeight(Double.parseDouble(split[1]));

            return pet;
        }
        return null;
    }
}
