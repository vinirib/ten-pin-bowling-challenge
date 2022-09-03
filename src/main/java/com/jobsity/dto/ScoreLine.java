package com.jobsity.dto;

import com.jobsity.exception.InvalidScoreLineException;

import java.io.Serializable;
import java.util.Optional;

public class ScoreLine implements Serializable {
    private static final String DATA_SEPARATOR = " ";
    private String player;
    private String pinsKnockedDown;

    private ScoreLine() {
    }

    public ScoreLine(String line) {
        parseLineToAttributes(line);
    }

    private void parseLineToAttributes(String line) {
        String[] flatLine = line.split(DATA_SEPARATOR);
        player = Optional.ofNullable(flatLine[0]).orElseThrow(() ->
                new InvalidScoreLineException("Score line must contain Player and score number.. "
                        + "Player name is missing"));
        pinsKnockedDown = Optional.ofNullable(flatLine[1]).orElseThrow(() ->
                new InvalidScoreLineException("Score line must contain Player and score number.. "
                        + "Score value is missing"));
    }

    public String getPinsKnockedDown() {
        return pinsKnockedDown;
    }

    public String getPlayer() {
        return player;
    }
}
