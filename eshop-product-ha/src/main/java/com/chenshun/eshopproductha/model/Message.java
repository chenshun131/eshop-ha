package com.chenshun.eshopproductha.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: mew <p />
 * Time: 18/5/11 10:30  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
@NoArgsConstructor
@Data
public class Message {

    private String serviceId;

    private Object data;

    public Message(String serviceId) {
        this.serviceId = serviceId;
    }

}
