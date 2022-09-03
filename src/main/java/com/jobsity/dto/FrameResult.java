package com.jobsity.dto;

import java.util.List;

public class FrameResult {

    private int number;

    private String player;

    private List<String> pinsKnockedDown;

    private int score;

    private Integer accumulativeScore;


    public boolean isStrikeOrSpare() {
        return this.getScore() >= 10;
    }

    public boolean isStrikeAndSpare() {
        return this.getScore() == 20;
    }

    public boolean isStrike() {
        return pinsKnockedDown
                .stream()
                .anyMatch(p -> p.equals(BowlingResultRepresentationEnum.STRIKE.getRepresentation())
                        || p.equals(String.valueOf(BowlingResultRepresentationEnum.STRIKE.getValue())));
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }

    public int getNumber() {
        return number;
    }

    public Integer getAccumulativeScore() {
        return accumulativeScore;
    }

    public void setAccumulativeScore(Integer accumulativeScore) {
        this.accumulativeScore = accumulativeScore;
    }

    public List<String> getPinsKnockedDown() {
        return pinsKnockedDown;
    }

    public static final class FrameResultBuilder {
        private int number;
        private String player;
        private List<String> pinsKnockedDown;
        private int score;
        private Integer accumulativeScore;

        private FrameResultBuilder() {
        }

        public static FrameResultBuilder aFrameResult() {
            return new FrameResultBuilder();
        }

        public FrameResultBuilder withNumber(int number) {
            this.number = number;
            return this;
        }

        public FrameResultBuilder withPlayer(String player) {
            this.player = player;
            return this;
        }

        public FrameResultBuilder withPinsKnockedDown(List<String> pinsKnockedDown) {
            this.pinsKnockedDown = pinsKnockedDown;
            return this;
        }

        public FrameResultBuilder withScore(int score) {
            this.score = score;
            return this;
        }

        public FrameResultBuilder withAccumulativeScore(Integer accumulativeScore) {
            this.accumulativeScore = accumulativeScore;
            return this;
        }

        public FrameResult build() {
            FrameResult frameResult = new FrameResult();
            frameResult.accumulativeScore = this.accumulativeScore;
            frameResult.pinsKnockedDown = this.pinsKnockedDown;
            frameResult.player = this.player;
            frameResult.number = this.number;
            frameResult.score = this.score;
            return frameResult;
        }
    }
}
