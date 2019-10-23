package com.meetup.newsletter.mapper;

import com.meetup.newsletter.entity.NewsletterSubscriptionEntity;
import com.meetup.newsletter.model.NewsletterSubsciption;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NewsletterSubscriptionMapper {


    public NewsletterSubscriptionEntity map(NewsletterSubsciption subscription) {
        NewsletterSubscriptionEntity entity = new NewsletterSubscriptionEntity();

        entity.setName(subscription.getName());
        entity.setEmail(subscription.getEmail());

        return entity;
    }
}
