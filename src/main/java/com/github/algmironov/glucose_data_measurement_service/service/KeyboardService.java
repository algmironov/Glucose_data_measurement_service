package com.github.algmironov.glucose_data_measurement_service.service;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageCaption;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.List;

public interface KeyboardService {

    SendMessage menuLoader (Message message, String text, List<String> listButtons);

    SendMessage menuLoader (Update update, String text, List<String> listButtons);

    EditMessageText editMenuLoader(Update update, String text, List<String> listButtons);

    EditMessageCaption editMenuLoaderForCaption(Update update, String text, List<String> listButtons);

    EditMessageText editMenuLoader(Update update, String text, List<String> listButtons, List<String> callBacks);

    EditMessageText editMenuLoader(Update update, String text);

    String getHashFromButton(String message);

    InlineKeyboardMarkup keyboardCreator(List<String> list);
}
