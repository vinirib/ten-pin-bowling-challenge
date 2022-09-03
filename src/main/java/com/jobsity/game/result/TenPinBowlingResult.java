package com.jobsity.game.result;

import com.jobsity.dto.BowlingResultRepresentationEnum;
import com.jobsity.dto.FrameResult;

import java.util.List;

public class TenPinBowlingResult implements BowlingResult {

    @Override
    public void matchBowlingResult(List<FrameResult> frameResultList) {
        int partialSum = 0;
        for (var frameResult : frameResultList) {
            if (frameResult.isStrikeOrSpare()) {
                partialSum += 10
                        + BowlingResultRepresentationEnum
                        .scoreFromPinsKnockedDown(findNextMatch(frameResult.getNumber(), frameResultList, 0));
                if (frameResult.isStrike()) {
                    partialSum += BowlingResultRepresentationEnum
                            .scoreFromPinsKnockedDown(findNextMatch(frameResult.getNumber(), frameResultList, 1));
                }

            } else {
                partialSum += frameResult.getScore();
            }
            frameResult.setAccumulativeScore(partialSum);
        }
    }

    private String findNextMatch(int number, List<FrameResult> frames, int skip) {
        try {
            List<String> pinsKnockedDown = frames.get(number).getPinsKnockedDown();
            return skip < pinsKnockedDown.size() ? pinsKnockedDown.get(skip) : findNextMatch(number + 1, frames, skip - 1);
        } catch (Exception e) {
            return frames.get(frames.size() - 1).getPinsKnockedDown().get(skip == 0 ? 2 : 1);
        }
    }
}
