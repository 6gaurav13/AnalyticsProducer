package com.analytics.producer.service;

import com.analytics.producer.dto.AnalyticsEventRequest;
import com.analytics.producer.kafka.KafkaPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PublishService {


    private final KafkaPublisher kafkaPublisher;

    @Value("${kafka.topic}")
    private  String TOPIC;

    public PublishService(KafkaPublisher kafkaPublisher) {
        this.kafkaPublisher = kafkaPublisher;
    }

    public void publishEvent(String appId, AnalyticsEventRequest analyticsEventRequest) {
        Map<String,Object> map = new HashMap<>();
        map.put("appId", appId);
        map.put("eventType", analyticsEventRequest.getEventType());
        map.put("userId", analyticsEventRequest.getUserId());
        map.put("userName", analyticsEventRequest.getUserName());
        map.put("deviceType", analyticsEventRequest.getDeviceType());
        map.put("browser", analyticsEventRequest.getBrowser());
        map.put("timeStamp", analyticsEventRequest.getTimestamp());

        kafkaPublisher.publish(TOPIC,appId,map);


    }
}
