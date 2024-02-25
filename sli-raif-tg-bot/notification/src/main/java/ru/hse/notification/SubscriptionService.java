package ru.hse.notification;

import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.notification.repository.SubscriptionRepository;
import ru.hse.notification.repository.entity.Subscription;

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
}
