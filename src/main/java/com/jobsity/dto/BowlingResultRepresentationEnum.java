package com.jobsity.dto;

import java.util.Arrays;

public enum BowlingResultRepresentationEnum {

    SPARE("/", 10),
    STRIKE("X", 10),
    FOUL("F", 0),
    INVALID(null, -1);

    private final String representation;

    BowlingResultRepresentationEnum(String representation, int value) {
        this.representation = representation;
        this.value = value;
    }

    private int value;

    public static int scoreFromPinsKnockedDown(String pinsKnockedDown) {
        var score = 0;
        try {
            score = Integer.parseInt(pinsKnockedDown);
        } catch (NumberFormatException e) {
            score = Arrays.asList(BowlingResultRepresentationEnum.values())
                    .stream()
                    .filter(s -> s.representation.equals(pinsKnockedDown))
                    .findFirst()
                    .orElse(INVALID)
                    .getValue();
        }

        return score;
    }

    public int getValue() {
        return value;
    }

    public String getRepresentation() {
        return representation;
    }
}
