package ru.hse.notification.telegram;

import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TgBot extends TelegramLongPollingBot {

    private final String tgBotToken;

    private final String miniAppUrl;
    private final String miniAppMessageUrl;

    @Autowired
    public TgBot(
            @Qualifier("botToken") String botToken,
            @Qualifier("miniAppUrl") String miniAppUrl,
            @Qualifier("miniAppMessageUrl") String miniAppMessageUrl) {
        super(botToken);
        tgBotToken = botToken;
        this.miniAppUrl = miniAppUrl;
        this.miniAppMessageUrl = miniAppMessageUrl;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            System.out.println("Processing update");

            if (update.hasMessage()) {
                System.out.println("Processing message");
                var msgIn = update.getMessage();
                if (!msgIn.hasText()) {
                    sendError("Incoming message is empty", msgIn.getChatId());
                    System.out.println("Incoming message is empty");
                    return;
                }
                if (msgIn.getText().equals("/info")) {
                    var msg = new SendMessage(Long.toString(msgIn.getChatId()), "Your chat id: " + msgIn.getChatId());
                    execute(msg);
                }
                if (msgIn.getText().equals("/start")) {
                    sendStartMiniAppButton(msgIn.getChatId(), msgIn.getChat().isUserChat());
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public String getBotUsername() {
        return "raif_status_bot";
    }

    @Override
    public String getBotToken() {
        return tgBotToken;
    }

    private void sendError(String text, long chatId) throws TelegramApiException {
        var msg = new SendMessage(Long.toString(chatId), text);
        execute(msg);
    }

    private void sendError(String text, String chatInstance) throws TelegramApiException {
        var msg = new SendMessage(chatInstance, text);
        execute(msg);
    }

    private void sendStartMiniAppButton(long chatId, boolean isPrivateChat) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        if (isPrivateChat) {
            var inlineButton = new InlineKeyboardButton("Open Mini App");
            inlineButton.setWebApp(new WebAppInfo(this.miniAppUrl));

            List<List<InlineKeyboardButton>> keyboard = List.of(List.of(inlineButton));

            message.setText("Go!");
            message.setReplyMarkup(new InlineKeyboardMarkup(keyboard));
        } else {
            message.setText(miniAppMessageUrl);
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Failed to send start mini app button");
            e.printStackTrace(System.out);
        }
    }

    public void sendMessage(String chatId, String message)  {
        var msg = new SendMessage(chatId, message);
        try {
            execute(msg);
        } catch (TelegramApiException err) {
            log.error("Message was not sent&", err);
        }
    }

    public void sendMessages(Set<String> chatIds, String message) {
        chatIds.forEach(id -> sendMessage(id, message));
    }

}
