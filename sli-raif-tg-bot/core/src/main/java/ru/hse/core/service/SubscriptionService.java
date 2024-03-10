package ru.hse.core.service;

import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.core.repository.SubscriptionRepository;
import ru.hse.core.repository.entity.Subscription;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public void addSubscription(String tgChatId) {
        if (subscriptionRepository.findSubscriptionsByChatId(tgChatId).isEmpty()) {
            var s = new Subscription();
            s.setChatId(tgChatId);
            subscriptionRepository.save(s);
        }
    }

    public Set<Subscription> getAllSubscriptions() {
        return new HashSet<>(subscriptionRepository.findAll());
    }

    public boolean isSubscribed(String tgChatId) {
        return !subscriptionRepository.findSubscriptionsByChatId(tgChatId).isEmpty();
    }
}
