package org.lakhara.console;

import org.lakhara.Game;
import org.lakhara.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleNumberGuess {

//    == Constants ==
    private static final Logger log = LoggerFactory.getLogger(ConsoleNumberGuess.class);

//    == fields ==
    @Autowired
    private Game game;

    @Autowired
    private MessageGenerator messageGenerator;

//    == events ==
    @EventListener
    public void start(ContextRefreshedEvent event) {
        log.info("start() --> Container ready for use.");

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()) {
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n ?");

                String shouldRestartGame = scanner.nextLine().trim();
                if (!shouldRestartGame.equalsIgnoreCase("y")) {
                    break;
                }
                game.reset();
            }
        }


    }
}
