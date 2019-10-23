package com.meetup.email.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.email.entity.NewsletterSubscriptionEntity;
import com.meetup.email.mapper.NewsletterSubscriptionMapper;
import com.meetup.email.model.NewsletterSubsciption;
import com.meetup.email.repository.NewsletterSubscriptionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @Autowired
    private NewsletterSubscriptionRepository newsletterSubscriptionRepository;
    @Autowired
    private NewsletterSubscriptionMapper newsletterSubscriptionMapper;

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private static final String TOPIC = "subscribers";

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String message){
        logger.info(String.format("$$ -> Consumed Message -> %s",message));

        ObjectMapper mapper = new ObjectMapper();
        try {
            NewsletterSubsciption subscription = mapper.readValue(message, NewsletterSubsciption.class);
            NewsletterSubscriptionEntity entity = newsletterSubscriptionMapper.map(subscription);
            newsletterSubscriptionRepository.save(entity);
        } catch (JsonProcessingException e) {
            logger.error("failed to read subscription from message", e);
        }
    }

}
