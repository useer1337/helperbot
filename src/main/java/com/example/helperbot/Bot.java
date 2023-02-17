package com.example.helperbot;

import com.example.helperbot.commands.CommandsPool;
import com.example.helperbot.configuration.ApplicationConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {

    private final CommandsPool commandsPool;
    private final ApplicationConfiguration applicationConfiguration;

    @Override
    public String getBotUsername() {
        return applicationConfiguration.getBotName();
    }

    @Override
    public String getBotToken() {
        return applicationConfiguration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();

            Optional.ofNullable(commandsPool.executeCommand(message.getText(), message.getChatId()))
                    .ifPresent(answerText -> {
                        SendMessage response = new SendMessage();
                        response.setChatId(String.valueOf(message.getChatId()));
                        response.setText(answerText);

                        try {
                            execute(response);
                        } catch (TelegramApiException ex) {
                            log.error(ex.getMessage(), ex);
                        }
                    });
        }
    }
}
