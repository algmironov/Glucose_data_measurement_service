package com.github.algmironov.glucose_data_measurement_service.service;


import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public interface ResponseDealer {

    public void responseMaker(Update update, String text, List<String> buttons);

}
