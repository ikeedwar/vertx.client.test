package com.zjb.client;

import io.vertx.amqp.AmqpClient;
import io.vertx.amqp.AmqpClientOptions;
import io.vertx.amqp.AmqpConnection;
import io.vertx.amqp.AmqpMessage;
import io.vertx.amqp.AmqpMessageBuilder;
import io.vertx.amqp.AmqpSender;

/**
 * @author zhangjunbo02
 */
public class AmqpVertxClient {

    public void testClient(){
        AmqpClientOptions options = new AmqpClientOptions()
                .setHost("localhost")
                .setPort(5672)
                .setUsername("user")
                .setPassword("secret");

        //使用自身内部的 vertx 隐式创建 client 实例
        AmqpClient client = AmqpClient.create(options);

        client.connect(ar -> {
            if (ar.failed()) {
                System.out.println("Unable to connect to the broker");
            } else {
                System.out.println("Connection succeeded");
                AmqpConnection connection = ar.result();

                connection.createSender("my-queue", done -> {
                    if (done.failed()) {
                        System.out.println("Unable to create a sender");
                    } else {
                        AmqpSender result = done.result();
                        AmqpMessageBuilder builder = AmqpMessage.create();
                        AmqpMessage m1 = builder.withBody("hello").build();
                        result.send(m1);
                        System.out.println("send message success");
                    }
                });
            }
        });
    }



}
