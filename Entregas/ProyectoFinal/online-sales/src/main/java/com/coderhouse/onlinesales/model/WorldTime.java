package com.coderhouse.onlinesales.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.grammars.hql.HqlParser;
import org.hibernate.type.descriptor.java.OffsetTimeJavaType;
import org.springframework.cglib.core.Local;

import java.time.*;

@Getter
@Setter
//Class used for mapping WorldTimeAPI Json Data
public class WorldTime {
    public WorldTime() {
    }

    public WorldTime(String utcDatetime, String utcOffset) {
        this.utcDatetime = utcDatetime;
        this.utcOffset = utcOffset;
    }

    @JsonProperty("utc_datetime")
    private String utcDatetime;
    @JsonProperty("utc_offset")
    private String utcOffset;


    public LocalDateTime getLocalDateTime(){ return convertToLocalDateTime(this.utcDatetime, this.utcOffset); }
    public LocalDateTime convertToLocalDateTime (String utcDatetime, String utcOffset){

        OffsetDateTime dateTime = OffsetDateTime.parse(utcDatetime);

        ZoneOffset offset = ZoneOffset.of(utcOffset);

        OffsetDateTime newDateTime = dateTime.withOffsetSameInstant(offset);

        return newDateTime.toLocalDateTime();
    }

}
