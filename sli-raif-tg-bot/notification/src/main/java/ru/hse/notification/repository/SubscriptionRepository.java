package ru.hse.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.notification.repository.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findSubscriptionsByChatId(String chatId);
}
