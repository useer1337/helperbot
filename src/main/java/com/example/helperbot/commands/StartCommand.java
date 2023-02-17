package com.example.helperbot.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StartCommand implements Command {

    private final static String message = "Привет, я бот помощник. Готов помогать тебе всем доступным мне функционалом.";

    @Override
    public String executeCommand(Long chatId) {
        log.debug("Consume command /start from user with chatId={}", chatId);
        return message;
    }
}
