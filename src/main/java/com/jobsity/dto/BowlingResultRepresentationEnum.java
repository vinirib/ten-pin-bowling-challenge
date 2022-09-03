package com.jobsity.dto;

import com.jobsity.exception.InvalidBowlingInputException;

import java.util.Arrays;

public enum BowlingResultRepresentationEnum {

    SPARE("/", 10),
    STRIKE("X", 10),
    FOUL("F", 0);

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
            if (score < 0) {
                throw new InvalidBowlingInputException("Less than zero pins knocked down was passed: " +pinsKnockedDown);
            } else if (score > 10) {
                throw new InvalidBowlingInputException("More than ten pins knocked down was passed: " +pinsKnockedDown);
            }
        } catch (NumberFormatException e) {
            score = Arrays.stream(BowlingResultRepresentationEnum.values())
                    .filter(s -> s.representation.equals(pinsKnockedDown))
                    .findFirst()
                    .orElseThrow(() ->
                            new InvalidBowlingInputException("Result value " +pinsKnockedDown+ "is invalid"))
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
