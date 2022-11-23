package com.github.algmironov.glucose_data_measurement_service.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UpdateListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(UpdateListener.class);

    private final TelegramBot telegramBot;

    public UpdateListener(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("====Processing update: {}", update);
            Message message = getMessage(update);
            User user = getUserFromMessage(message);
            if (startCommandChecker(update)) {
                telegramBot.execute(new SendMessage(message.from().id(), "Hello!"));
            }


        });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private Message getMessage(Update update) {
        Message message = new Message();
        if (update.message() != null) {
            message = update.message();
        } else if (update.callbackQuery().message() != null) {
            message = update.callbackQuery().message();
        }
        return message;
    }

    private User getUserFromMessage(Message message) {
        return message.from();
    }

    private boolean startCommandChecker(Update update) {
        return getMessage(update).text().equals("/start");
    }
}
