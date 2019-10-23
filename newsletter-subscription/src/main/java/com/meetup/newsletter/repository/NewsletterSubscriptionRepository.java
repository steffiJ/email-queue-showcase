package com.meetup.newsletter.repository;

import com.meetup.newsletter.entity.NewsletterSubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsletterSubscriptionRepository extends JpaRepository<NewsletterSubscriptionEntity, Long> {
}
