package ru.hse.configurtion;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import org.telegram.telegrambots.starter.TelegramBotInitializer;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TgConfiguration {

    @Value("${telegram.bot.token}")
    private String tgBotToken;

    @Value("${telegram.miniapp.url}")
    private String miniAppUrl;

    @Bean
    public String botToken() {
        return tgBotToken;
    }

    @Bean
    public String miniAppUrl() {
        return miniAppUrl;
    }

    @Bean
    @ConditionalOnMissingBean(TelegramBotsApi.class)
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public TelegramBotInitializer telegramBotInitializer(TelegramBotsApi telegramBotsApi,
                                                         ObjectProvider<List<LongPollingBot>> longPollingBots,
                                                         ObjectProvider<List<SpringWebhookBot>> webHookBots) {
        return new TelegramBotInitializer(telegramBotsApi,
                longPollingBots.getIfAvailable(Collections::emptyList),
                webHookBots.getIfAvailable(Collections::emptyList));
    }
}