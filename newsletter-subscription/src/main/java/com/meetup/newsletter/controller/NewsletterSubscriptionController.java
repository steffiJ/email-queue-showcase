package com.meetup.newsletter.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.newsletter.mapper.NewsletterSubscriptionMapper;
import com.meetup.newsletter.model.NewsletterSubsciption;
import com.meetup.newsletter.repository.NewsletterSubscriptionRepository;
import com.meetup.newsletter.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewsletterSubscriptionController {

    @Autowired
    private NewsletterSubscriptionRepository newsletterSubscriptionRepository;
    @Autowired
    private NewsletterSubscriptionMapper mapper;
    @Autowired
    private Producer producer;

    private static final Logger logger = LoggerFactory.getLogger(NewsletterSubscriptionController.class);

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("subscription", new NewsletterSubsciption());
        return "newsletter";
    }

    @PostMapping("/subscribe")
    public String subscribe(@ModelAttribute NewsletterSubsciption subscription) {

        newsletterSubscriptionRepository.save(mapper.map(subscription));

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(subscription);
            this.producer.sendMessage(json);
        } catch (JsonProcessingException e) {
            logger.error("failed to convert subscription to json", e);
        }
        return "success";
    }
}
