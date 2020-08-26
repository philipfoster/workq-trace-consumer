package com.workq.traceeventconsumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workq.traceeventconsumer.events.ProcessTraceEvent;
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
        ProcessTraceEvent event = mapper.readValue(message, ProcessTraceEvent.class);
        LOGGER.info("Got message {}", event);
        mongoOperations.insert(event);
    }

}
