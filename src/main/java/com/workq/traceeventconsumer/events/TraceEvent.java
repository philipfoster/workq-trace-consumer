package com.workq.traceeventconsumer.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.workq.traceeventconsumer.marshalling.LocalDateTimeDeserializer;
import com.workq.traceeventconsumer.marshalling.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Data model for a generic trace event. IF additional trace types are added, they should inherit from this class.
 */
@JsonPropertyOrder({"ProcessInstanceId", "TimeStamp", "TraceEventType"})
public class TraceEvent {


    private String ProcessInstanceId;
    private LocalDateTime timestamp;
    private TraceEventType traceEventType = TraceEventType.None;

    @JsonProperty("ProcessInstanceId")
    public String getProcessInstanceId() {
        return this.ProcessInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.ProcessInstanceId = processInstanceId;
    }

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonProperty("TimeStamp")
    public LocalDateTime getTimeStamp() {
        return this.timestamp;
    }

    public void setTimeStamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("TraceEventType")
    public TraceEventType getTraceEventType() {
        return this.traceEventType;
    }

    public void setTraceEventType(TraceEventType traceEventType) {
        this.traceEventType = traceEventType;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TraceEvent{" +
            ", ProcessInstanceId='" + ProcessInstanceId + '\'' +
            ", timestamp=" + timestamp +
            ", traceEventType=" + traceEventType +
            '}';
    }
}
