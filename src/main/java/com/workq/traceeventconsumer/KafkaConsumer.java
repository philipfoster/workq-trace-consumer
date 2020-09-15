package com.workq.traceeventconsumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workq.traceeventconsumer.events.ProcessTraceEvent;
import com.workq.traceeventconsumer.events.SlaViolatedTraceEvent;
import com.workq.traceeventconsumer.events.TaskTraceEvent;
import com.workq.traceeventconsumer.events.TraceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private final ObjectMapper mapper;
    private final MongoOperations mongoOperations;

    @Autowired
    public KafkaConsumer(ObjectMapper mapper, MongoOperations mongoOperations) {
        this.mapper = mapper;
        this.mongoOperations = mongoOperations;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}")
    public void listen(String message) throws JsonProcessingException {
        TraceEvent event = parseTraceEvent(message);
        LOGGER.info("Got message from kafka {}", event);
        mongoOperations.insert(event);
    }

    private TraceEvent parseTraceEvent(String json) throws JsonProcessingException {
        TraceEvent event = mapper.readValue(json, TraceEvent.class);
        switch (event.getTraceEventType()) {
            case ProcessTraceEvent:
                return mapper.readValue(json, ProcessTraceEvent.class);
            case SlaViolatedEvent:
                return mapper.readValue(json, SlaViolatedTraceEvent.class);
            case TaskTraceEvent:
                return mapper.readValue(json, TaskTraceEvent.class);
            default:
                LOGGER.warn("Failed to parse json {}", json);
                return event;
        }
    }

}
