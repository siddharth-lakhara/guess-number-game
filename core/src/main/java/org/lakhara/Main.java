package org.lakhara;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String CONFIG_LOCATION = "beans.xml";

    public static void main(String[] args) {
        logger.info("Guess the number game");

//        Create context container
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(CONFIG_LOCATION);

//        Get number generator bean context container
        NumberGenerator numberGenerator
                = context.getBean("numberGeneratorId", NumberGeneratorImpl.class);

        int number = numberGenerator.next();

        logger.info("number = {}", number);

//        Get game bean context container
        Game game = context.getBean(Game.class);

        game.reset();

//        Close context container
        context.close();

    }
}
