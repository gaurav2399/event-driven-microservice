package com.microservice.kafka.twitter_to_kafka_service;

import com.microservice.kafka.twitter_to_kafka_service.runner.StreamRunner;
import com.microservice.kafka.config.TwitterToKafkaServiceConfigData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "com.microservice.kafka")
public class TwitterToKafkaServiceApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterToKafkaServiceApplication.class);

    private final TwitterToKafkaServiceConfigData configData;

    private final StreamRunner streamRunner;

    TwitterToKafkaServiceApplication(TwitterToKafkaServiceConfigData configData,
                                     StreamRunner streamRunner){
        this.configData = configData;
        this.streamRunner = streamRunner;
    }

    public static void main(String[] args) {
        SpringApplication.run(TwitterToKafkaServiceApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("App starts");
        LOG.info(Arrays.toString(configData.getTwitterKeywords().toArray(new String[] {})));
        LOG.info(configData.getWelcomeMessage());
        streamRunner.start();
    }
}