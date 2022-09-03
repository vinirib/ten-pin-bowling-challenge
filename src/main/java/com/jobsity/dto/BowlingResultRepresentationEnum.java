package com.jobsity.dto;

import java.util.Arrays;

public enum BowlingResultRepresentationEnum {

    SPARE("/", 10),
    STRIKE("X", 10),
    FOUL("F", 0),
    INVALID(null, -1);

    private final String representation;
    private final int value;

    BowlingResultRepresentationEnum(String representation, int value) {
        this.representation = representation;
        this.value = value;
    }

    public static int scoreFromPinsKnockedDown(String pinsKnockedDown) {
        int score;
        try {
            score = Integer.parseInt(pinsKnockedDown);
        } catch (NumberFormatException e) {
            score = Arrays.stream(BowlingResultRepresentationEnum.values())
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
