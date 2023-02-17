package com.example.helperbot.commands;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class CommandsPool {

    private static final Map<String, Command> comandsMap = Map.of(
            "/start", new StartCommand()
    );

    public String executeCommand(String commandText, Long chatId) {
        return Optional.ofNullable(comandsMap.get(commandText)).map(command -> command.executeCommand(chatId))
                .orElse(null);
    }

}
