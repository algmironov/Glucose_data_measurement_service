package com.github.algmironov.glucose_data_measurement_service.service;


import com.github.algmironov.glucose_data_measurement_service.model.User;

public interface UserService {
    boolean checkUserInDataBase(com.pengrad.telegrambot.model.User user);

    User saveUser(User user);

    User saveTgUser(com.pengrad.telegrambot.model.User tgUser);

    User findUser(Long chatId);
}
