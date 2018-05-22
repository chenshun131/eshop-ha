package com.chenshun.eshopproductha;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.chenshun.eshopproductha.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class EshopProductHaApplication {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.需要定义一个Convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2.添加fastjson的配置信息，比如是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        // -数值字段如果为null,输出为0,而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullNumberAsZero);
        // List字段如果为null,输出为[],而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty);
        // 字符类型字段如果为null,输出为:"",而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullStringAsEmpty);
        // Boolean字段如果为null,输出为false,而非null
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullBooleanAsFalse);

        // 3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

    public static void main(String[] args) {
        SpringApplication.run(EshopProductHaApplication.class, args);
    }

}
