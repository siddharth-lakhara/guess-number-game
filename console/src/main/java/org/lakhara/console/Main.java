package org.lakhara.console;

import org.lakhara.config.AppConfig;
import org.lakhara.MessageGenerator;
import org.lakhara.NumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Guess the number game");

//        Create context container
        ConfigurableApplicationContext context
                = new AnnotationConfigApplicationContext(AppConfig.class);

//        Get number generator bean context container
        NumberGenerator numberGenerator
                = context.getBean(NumberGenerator.class);

        int number = numberGenerator.next();

        logger.info("number = {}", number);

        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        logger.info("getMainMessage = {}", messageGenerator.getMainMessage());
        logger.info("getResultMessage = {}", messageGenerator.getResultMessage());

//        Close context container
        context.close();

    }
}

