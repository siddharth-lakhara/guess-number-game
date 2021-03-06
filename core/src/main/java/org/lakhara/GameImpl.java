package org.lakhara;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class GameImpl implements Game {
    private static final Logger logger = LoggerFactory.getLogger(GameImpl.class);

    @Autowired
    private NumberGenerator numberGenerator;

    @Autowired
    private int guessCount;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @PostConstruct
    @Override
    public void reset() {
        this.smallest = 0;
        this.guess = 0;
        this.remainingGuesses = this.guessCount;
        this.biggest = this.numberGenerator.getMaxNumber();
        this.number = this.numberGenerator.next();
    }

    @PreDestroy
    public void preDestroy() {
        logger.info("in game destroy property");
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public int getGuess() {
        return this.guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return this.smallest;
    }

    @Override
    public int getBiggest() {

        return this.biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return this.remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guessCount;
    }

    @Override
    public void check() {
        checkValidNumberRange();

        if (validNumberRange) {
            if (guess > number) {
                biggest = guess-1;
            }
            if (guess< smallest) {
                smallest = guess + 1;
            }
        }
        remainingGuesses -= 1;
    }

    @Override
    public boolean isValidNumberRange() {
        return this.validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return (this.guess == this.number);
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    private void checkValidNumberRange() {
        validNumberRange = guess >= smallest && guess <= biggest;
    }
}
