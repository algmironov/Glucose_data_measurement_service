package com.github.algmironov.glucose_data_measurement_service.service.impl;

import com.github.algmironov.glucose_data_measurement_service.service.KeyboardService;
import com.github.algmironov.glucose_data_measurement_service.service.ResponseDealer;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.github.algmironov.glucose_data_measurement_service.constants.ButtonsAndTexts.*;

@Service
public class ResponseDealerImpl implements ResponseDealer {

    Logger logger = LoggerFactory.getLogger(ResponseDealerImpl.class);
    private final TelegramBot telegramBot;
    private final KeyboardService keyboardService;


    private enum MessageTypes {
        MESSAGE,
        CALLBACK,
        PICTURE
    }


    public ResponseDealerImpl(TelegramBot telegramBot,
                              KeyboardService keyboardService) {
        this.telegramBot = telegramBot;
        this.keyboardService = keyboardService;
    }


    @Override
    public void responseMaker(Update update, String text, List<String> buttons) {
        long chatId = 0L;
        if (messageTypeChecker(update) == null) {
            chatId = update.message().from().id();
            telegramBot.execute(responseToUser(chatId, UNSUPPORTED_MESSAGE, backToMainMenuList));
        } else if (messageTypeChecker(update) == MessageTypes.MESSAGE) {
            chatId = update.message().chat().id();

        } else if (messageTypeChecker(update) == MessageTypes.CALLBACK) {
            chatId = update.callbackQuery().from().id();
        } else {
            chatId = update.message().from().id();
        }
        telegramBot.execute(responseToUser(chatId, text, buttons));
    }

    private MessageTypes messageTypeChecker(Update update) {

        if (update.message().text() != null) {
            return MessageTypes.MESSAGE;
        } else if (update.message().photo() != null) {
            return MessageTypes.PICTURE;
        } else if (update.callbackQuery().message().text() != null) {
            return MessageTypes.CALLBACK;
        }
        return null;
    }

    private SendMessage responseToUser(Long chatId, String message, List<String> buttons) {
        return new SendMessage(chatId, message).
                replyMarkup(keyboardService.keyboardCreator(buttons));
    }


}
