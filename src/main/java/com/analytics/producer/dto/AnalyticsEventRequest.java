package com.analytics.producer.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsEventRequest {

    @NotBlank
    private String eventType;

    @NotBlank
    private String userId;

    private String userName;

    private String deviceType;

    private String browser;

    @NotNull
    private Long timestamp;

}

