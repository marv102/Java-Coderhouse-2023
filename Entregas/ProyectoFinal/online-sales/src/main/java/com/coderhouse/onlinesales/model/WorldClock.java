package com.coderhouse.onlinesales.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class WorldClock {

    public WorldClock() {
    }

    public WorldClock(String currentDateTime, String utcOffset) {
        this.currentDateTime = currentDateTime;
        this.utcOffset = utcOffset;
    }

    @JsonProperty("currentDateTime")
    private String currentDateTime;
    @JsonProperty("utcOffset")
    private String utcOffset;

    public LocalDateTime getLocalDateTime(){ return convertToLocalDateTime(this.currentDateTime, this.utcOffset); }
    private LocalDateTime convertToLocalDateTime (String utcDatetime, String utcOffset){

        OffsetDateTime dateTime = OffsetDateTime.parse(utcDatetime);

        int hours = Integer.parseInt(utcOffset.substring(0, 2));
        int minutes = Integer.parseInt(utcOffset.substring(3, 5));
        int seconds = Integer.parseInt(utcOffset.substring(6, 8));

        ZoneOffset offset = ZoneOffset.ofHoursMinutesSeconds(hours, minutes, seconds);

        OffsetDateTime newDateTime = dateTime.withOffsetSameInstant(offset);

        return newDateTime.toLocalDateTime();
    }




}
