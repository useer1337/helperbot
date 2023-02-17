package com.example.helperbot.commands.impl;

import com.example.helperbot.annotations.Command;
import com.example.helperbot.commands.ICommand;

@Command(commandName = "/start")
public class StartCommand implements ICommand {

    private final static String message = "Привет, я бот помощник. Готов помогать тебе всем доступным мне функционалом.";

    @Override
    public String executeCommand(Long chatId) {
        return message;
    }
}
