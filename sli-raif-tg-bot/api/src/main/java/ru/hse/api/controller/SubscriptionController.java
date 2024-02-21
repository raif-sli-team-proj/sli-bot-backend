package ru.hse.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.api.dto.SubscribeRequest;
import ru.hse.notification.SubscriptionService;

@RestController("api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest body) {
        subscriptionService.addSubscription(body.telegramChatId());
        return ResponseEntity.ok().build();
    }
}
