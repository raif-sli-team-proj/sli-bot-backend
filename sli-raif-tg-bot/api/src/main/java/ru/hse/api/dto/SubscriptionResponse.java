package ru.hse.api.dto;

public record SubscriptionResponse(String tgChatId, boolean isSubscribed) {
}
