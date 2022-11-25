package com.github.algmironov.glucose_data_measurement_service.service.impl;

import com.github.algmironov.glucose_data_measurement_service.repository.UserRepository;
import com.github.algmironov.glucose_data_measurement_service.service.UserService;
import com.github.algmironov.glucose_data_measurement_service.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User userMatcher(com.pengrad.telegrambot.model.User tgUser) {
        User user = new User();
        user.setId(tgUser.id());
        user.setUsername(tgUser.firstName() + " " + tgUser.lastName());
        return user;
    }

    @Override
    public boolean checkUserInDataBase(com.pengrad.telegrambot.model.User tgUser) {
        User user = userMatcher(tgUser);
        logger.info("Searching for User: {}", user.getUsername());
        return userRepository.findById(user.getId()).isPresent();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User saveTgUser(com.pengrad.telegrambot.model.User tgUser) {
        User user = userMatcher(tgUser);
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findUser(Long chatId) {
        User user = new User();
        if (userRepository.findById(chatId).isPresent()) {
            user = userRepository.findById(chatId).get();
        }
        return user;
    }
}
