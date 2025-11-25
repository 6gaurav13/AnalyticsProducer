package com.analytics.producer.controller;

import com.analytics.producer.dto.AnalyticsEventRequest;
import com.analytics.producer.service.PublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Producer {

    private final PublishService publishService;

    public ResponseEntity<String> messageProducer(@RequestBody AnalyticsEventRequest analyticsEventRequest, HttpRequest httpRequest) {
        String appId = httpRequest.getAttributes()
                .get("appId").toString();
        if(appId == null){return ResponseEntity.status(401).body("App not Authenticated");}
        //got app_Id
        //now need to send this data via kafka template to the topic
        //App_id is not send through request we get it from jwt token so that for a particular app the app_id and token do not mismatch
        publishService.publishEvent(appId,analyticsEventRequest);
        return ResponseEntity.ok("Request queued");
    }

}
