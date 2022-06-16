package com.zjb;

import com.zjb.client.AmqpVertxClient;

/**
 * @author zhangjunbo02
 */
public class Start {

    public static void main(String[] args){
        AmqpVertxClient amqpVertxClient = new AmqpVertxClient();
        amqpVertxClient.testClient();
    }

}
