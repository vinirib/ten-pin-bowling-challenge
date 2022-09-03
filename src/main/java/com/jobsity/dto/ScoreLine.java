package com.jobsity.dto;

import com.jobsity.exception.InvalidScoreLineException;

import java.io.Serializable;
import java.util.Optional;

public class ScoreLine implements Serializable {
    private static final String DATA_SEPARATOR = "\t";
    private String player;
    private String pinsKnockedDown;

    private ScoreLine() {
    }

    public ScoreLine(String line) {
        parseLineToAttributes(line);
    }

    private void parseLineToAttributes(String line) {
        String[] flatLine = line.split(DATA_SEPARATOR);
        validateLine(line, flatLine);
        player = Optional.ofNullable(flatLine[0]).orElseThrow(() ->
                new InvalidScoreLineException("Score line must contain Player and score number.. "
                        + "Player name is missing"));
        pinsKnockedDown = Optional.ofNullable(flatLine[1]).orElseThrow(() ->
                new InvalidScoreLineException("Score line must contain Player and score number.. "
                        + "Score value is missing"));
    }

    private void validateLine(String line, String[] flatLine) {
        if (flatLine.length != 2 ) {
            throw new InvalidScoreLineException("Invalid line detected.." + line);
        }
        BowlingResultRepresentationEnum.scoreFromPinsKnockedDown(flatLine[1]);
    }

    public String getPinsKnockedDown() {
        return pinsKnockedDown;
    }

    public String getPlayer() {
        return player;
    }
}
