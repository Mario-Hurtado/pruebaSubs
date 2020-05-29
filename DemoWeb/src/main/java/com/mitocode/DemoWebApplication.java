package com.mitocode;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWebApplication 
{

	public static IMqttClient publisher;
	public static String publisherId;
	
	public static void main(String[] args) 
	{
		publisherId = UUID.randomUUID().toString();
		try 
		{
			publisher = new MqttClient("tcp://52.91.152.101",publisherId);
			MqttConnectOptions options = new MqttConnectOptions();
			options.setAutomaticReconnect(true);
			options.setCleanSession(true);
			options.setConnectionTimeout(10);
			publisher.connect(options);
		} 
		catch (MqttException e) 
		{
			e.printStackTrace();
		}
		
		SpringApplication.run(DemoWebApplication.class, args);
	}

}
