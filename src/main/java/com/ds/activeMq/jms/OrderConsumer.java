package com.ds.activeMq.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
@Component
/**
 * 点对点消费者
 */
public class OrderConsumer {

//	@JmsListener(destination="order.queue")
//	public void receiveQueue(String text){
//		System.out.println("OrderConsumer收到的报文为:"+text);
//	}
}
