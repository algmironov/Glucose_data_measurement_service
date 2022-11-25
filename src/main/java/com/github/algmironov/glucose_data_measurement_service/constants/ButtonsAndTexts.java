package com.github.algmironov.glucose_data_measurement_service.constants;

import java.util.List;

public class ButtonsAndTexts {
    public static String MAIN_MENU = "Главное меню";
    public static String MAKE_RECORD = "Сделать запись";
    public static String VIEW_RECORDS = "Посмотреть записи";
    public static String VIEW_CHART = "Посмотреть график";
    public static String HELP = "Помощь";
    public static String SEND_MESSAGE = "Обратная связь";
    public static String WELCOME_MESSAGE = "Добро пожаловать в Glucoseer! \nЗдесь вы можете сохранять записи о показателях глюкозы в крови.";
    public static String ON_SUCCESS = "Запись успешно сохранена";
    public static String ON_FAIL = "Произошла ошибка, попробуйте еще раз";
    public static String ASK_TO_SEND_RECORD = "Отправьте показания уровня глюкозы";
    public static String BACK = "Вернуться";
    public static String UNSUPPORTED_MESSAGE = "Неподдерживаемый формат сообщения";
    public static String HIDDEN_BUTTON = "";
//    public static String


    public static List<String> mainMenuList = List.of(MAKE_RECORD, HELP, VIEW_RECORDS, VIEW_CHART);
    public static List<String> backToMainMenuList = List.of(MAIN_MENU);
}
