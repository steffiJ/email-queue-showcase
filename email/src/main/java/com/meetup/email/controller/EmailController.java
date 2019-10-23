package com.meetup.email.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetup.email.entity.NewsletterSubscriptionEntity;
import com.meetup.email.mapper.NewsletterSubscriptionMapper;
import com.meetup.email.model.NewsletterSubsciption;
import com.meetup.email.repository.NewsletterSubscriptionRepository;
import com.meetup.email.service.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmailController {

    @Autowired
    private NewsletterSubscriptionRepository newsletterSubscriptionRepository;
    @Autowired
    private NewsletterSubscriptionMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @GetMapping("/")
    public String main(Model model) {
        List<NewsletterSubscriptionEntity> entities = newsletterSubscriptionRepository.findAll();
        if (entities == null || entities.isEmpty()) {
            return "empty";
        }
        List<NewsletterSubsciption> subscriptions = mapper.reverseMapAll(entities);
        model.addAttribute("subscriptions", subscriptions);
        return "subscriptions";
    }
}
