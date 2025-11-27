package com.analytics.producer.controller;

import com.analytics.producer.dto.AnalyticsEventRequest;
import com.analytics.producer.service.PublishService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Producer {

    private final PublishService publishService;

    private  LoggerFactory loggerFactory;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/analytics/produce")
    public ResponseEntity<String> messageProducer(@RequestBody AnalyticsEventRequest analyticsEventRequest, HttpServletRequest httpRequest) {

        loggerFactory.getLogger(getClass()).info("Producing Started ");
        String appId = httpRequest.getAttribute("appId").toString();
        if(appId == null){return ResponseEntity.status(401).body("App not Authenticated");}

        loggerFactory.getLogger(getClass()).info("App Id = "+appId);
        //got app_Id
        //now need to send this data via kafka template to the topic
        //App_id is not send through request we get it from jwt token so that for a particular app the app_id and token do not mismatch
        publishService.publishEvent(appId,analyticsEventRequest);
        return ResponseEntity.ok("Request queued");
    }


    @GetMapping("producer/test")
    public String producerTest()
    {
        kafkaTemplate.send("gauravTopic","Testing Message 1");
        return "Producer Test Working Properly";
    }

}
