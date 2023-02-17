package com.example.helperbot.commands;

import com.example.helperbot.annotations.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandsPool {

    private final ApplicationContext applicationContext;
    private final ClassPathScanningCandidateComponentProvider scanner;

    public String executeCommand(String message, Long chatId) {
        log.debug("Consumed message = \"{}\" from chat with id = {}", message, chatId);
        try{
            Set<BeanDefinition> definitions = scanner.findCandidateComponents("com.example.helperbot.commands");

            for (BeanDefinition bd: definitions){
                if (bd instanceof AnnotatedBeanDefinition) {
                    var abd = (AnnotatedBeanDefinition) bd;
                    Map<String, Object> annotAttributeMap = abd
                            .getMetadata()
                            .getAnnotationAttributes(Command.class.getCanonicalName());
                    String commandName = annotAttributeMap.get("commandName").toString();
                    if (message.equals(commandName)){
                        Class<?> clazz = Class.forName(bd.getBeanClassName());
                        return ((ICommand) applicationContext.getBean(clazz)).executeCommand(chatId);
                    }
                }
            }

            return null;
        } catch (Exception ex){
            log.error(ex.getMessage(), ex);
            return null;
        }
    }

}
