package com.microservice.kafka.twitter_to_kafka_service.init.impl;

import com.microservice.kafka.twitter_to_kafka_service.init.StreamInitializer;
import com.microservice.kafka.admin.client.KafkaAdminClient;
import com.microservice.kafka.config.KafkaConfigData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaStreamInitializer implements StreamInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;

    private final KafkaAdminClient kafkaAdminClient;

    public KafkaStreamInitializer(KafkaConfigData configData, KafkaAdminClient adminClient) {
        this.kafkaConfigData = configData;
        this.kafkaAdminClient = adminClient;
    }

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
