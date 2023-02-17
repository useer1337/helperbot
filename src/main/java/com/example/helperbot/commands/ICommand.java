package com.example.helperbot.commands;

@FunctionalInterface
public interface ICommand {
    String executeCommand(Long chatId);
}
