package com.meetup.email.mapper;

import com.meetup.email.entity.NewsletterSubscriptionEntity;
import com.meetup.email.model.NewsletterSubsciption;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsletterSubscriptionMapper {


    public NewsletterSubscriptionEntity map(NewsletterSubsciption subscription) {
        NewsletterSubscriptionEntity entity = new NewsletterSubscriptionEntity();
        entity.setName(subscription.getName());
        entity.setEmail(subscription.getEmail());
        return entity;
    }

    public List<NewsletterSubsciption> reverseMapAll(List<NewsletterSubscriptionEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return null;
        }
        List<NewsletterSubsciption> subscriptions = new ArrayList<>();

        for (NewsletterSubscriptionEntity entity : entities) {
            subscriptions.add(reverseMap(entity));
        }
        return subscriptions;
    }

    private NewsletterSubsciption reverseMap(NewsletterSubscriptionEntity entity) {
        if (entity == null) {
            return null;
        }
        NewsletterSubsciption newsletterSubsciption = new NewsletterSubsciption();
        newsletterSubsciption.setName(entity.getName());
        newsletterSubsciption.setEmail(entity.getEmail());
        return newsletterSubsciption;
    }
}
