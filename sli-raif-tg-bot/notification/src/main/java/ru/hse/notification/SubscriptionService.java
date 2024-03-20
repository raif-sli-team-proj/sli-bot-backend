package ru.hse.notification;

import java.util.HashSet;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    public void removeSubscription(String chatId) {
        subscriptionRepository.deleteByChatId(chatId);
    }

    public Set<Subscription> getAllSubscriptions() {
        return new HashSet<>(subscriptionRepository.findAll());
    }

    public boolean isSubscribed(String tgChatId) {
        return !subscriptionRepository.findSubscriptionsByChatId(tgChatId).isEmpty();
    }
}
