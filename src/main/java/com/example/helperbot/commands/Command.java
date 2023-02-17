package com.example.helperbot.commands;

import java.util.List;

@FunctionalInterface
public interface Command {
    String executeCommand(Long chatId);
}
