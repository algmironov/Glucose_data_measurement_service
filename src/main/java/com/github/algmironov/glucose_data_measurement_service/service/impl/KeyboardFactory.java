package com.github.algmironov.glucose_data_measurement_service.service.impl;

import com.github.algmironov.glucose_data_measurement_service.service.KeyboardService;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.EditMessageCaption;
import com.pengrad.telegrambot.request.EditMessageText;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.github.algmironov.glucose_data_measurement_service.constants.ButtonsAndTexts.HIDDEN_BUTTON;

@Service
public class KeyboardFactory implements KeyboardService {

    @Override
    public SendMessage menuLoader(Message message, String text, List<String> listButtons) {
        return null;
    }

    @Override
    public SendMessage menuLoader(Update update, String text, List<String> listButtons) {
        return null;
    }

    @Override
    public EditMessageText editMenuLoader(Update update, String text, List<String> listButtons) {
        return null;
    }

    @Override
    public EditMessageCaption editMenuLoaderForCaption(Update update, String text, List<String> listButtons) {
        return null;
    }

    @Override
    public EditMessageText editMenuLoader(Update update, String text, List<String> listButtons, List<String> callBacks) {
        return null;
    }

    @Override
    public EditMessageText editMenuLoader(Update update, String text) {
        return null;
    }

    @Override
    public InlineKeyboardMarkup keyboardCreator(List<String> list) {
        if (list == null) {
            throw new NullPointerException("Inline menu list is null");
        }
        list = list.stream().filter(buttonText -> !buttonText.equals(HIDDEN_BUTTON)).collect(Collectors.toList());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        if (list.size() <= 10) {
            for (int i = 0; i < list.size(); i++) {
                inlineKeyboardMarkup.addRow(
                        new InlineKeyboardButton(list.get(i))
                                .callbackData(getHashFromButton(list.get(i))));
            }
        }
        if (list.size() > 10 && list.size() % 2 == 0) {
            for (int i = 0; i < list.size(); i = i + 2) {
                inlineKeyboardMarkup.addRow(
                        new InlineKeyboardButton(list.get(i))
                                .callbackData(getHashFromButton(list.get(i))),
                        new InlineKeyboardButton(list.get(i + 1))
                                .callbackData(getHashFromButton(list.get(i + 1))));
            }
        }
        if (list.size() > 10 && list.size() % 2 != 0) {
            for (int i = 0; i < list.size() - 1; i = i + 2) {
                inlineKeyboardMarkup.addRow(
                        new InlineKeyboardButton(list.get(i))
                                .callbackData(getHashFromButton(list.get(i))),
                        new InlineKeyboardButton(list.get(i + 1))
                                .callbackData(getHashFromButton(list.get(i + 1))));
            }
            inlineKeyboardMarkup.addRow(
                    new InlineKeyboardButton(list.get(list.size() - 1))
                            .callbackData(getHashFromButton(list.get(list.size() - 1))));
        }
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardMarkup keyboardCreator(List<String> buttons, List<String> callBacks) {
        if (buttons == null) {
            throw new NullPointerException("Inline menu list is null");
        }
        buttons = buttons.stream().filter(buttonText -> !buttonText.equals(HIDDEN_BUTTON)).collect(Collectors.toList());
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        Iterator<String> iterator = callBacks.listIterator();
        buttons.stream()
                .map(InlineKeyboardButton::new)
                .map(button -> button.callbackData(iterator.hasNext() ? iterator.next() : getHashFromButton(button.text())))
                .forEach(inlineKeyboardMarkup::addRow);

        return inlineKeyboardMarkup;
    }

    public String getHashFromButton(String message) {
        int hash = Objects.hash(message);
        return Integer.toString(hash);
    }
}
