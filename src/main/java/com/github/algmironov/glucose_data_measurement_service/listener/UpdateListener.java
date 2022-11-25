package com.github.algmironov.glucose_data_measurement_service.listener;

import com.github.algmironov.glucose_data_measurement_service.constants.ButtonsAndTexts;
import com.github.algmironov.glucose_data_measurement_service.service.RecordService;
import com.github.algmironov.glucose_data_measurement_service.service.ResponseDealer;
import com.github.algmironov.glucose_data_measurement_service.service.UserService;
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
import static com.github.algmironov.glucose_data_measurement_service.constants.ButtonsAndTexts.*;

@Service
public class UpdateListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(UpdateListener.class);

    private final TelegramBot telegramBot;

    private final UserService userService;

    private final RecordService recordService;

    private final ResponseDealer responseDealer;


    public UpdateListener(TelegramBot telegramBot,
                          RecordService recordService,
                          UserService userService,
                          ResponseDealer responseDealer) {
        this.telegramBot = telegramBot;
        this.recordService = recordService;
        this.userService = userService;
        this.responseDealer = responseDealer;
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
            User currentUser = getUserFromMessage(message);
            checkUserInDatabase(currentUser);
            if (startCommandChecker(update)) {
                logger.info("==== Answering to user with Welcome message and Main menu");
                responseDealer.responseMaker(update, WELCOME_MESSAGE, mainMenuList);

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

    private void checkUserInDatabase(User user) {
        if (!userService.checkUserInDataBase(user)) {
            userService.saveTgUser(user);
        }
    }
}
