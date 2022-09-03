package com.jobsity.game;

import com.jobsity.dto.BowlingResultRepresentationEnum;
import com.jobsity.dto.FrameResult;
import com.jobsity.dto.ScoreLine;

import java.util.*;
import java.util.stream.Collectors;

public class TenPinBowlingGame implements BowlingGame {

    @Override
    public Map<String, List<FrameResult>> receiveScoreLinesAndMatchResult(List<ScoreLine> scoreLines) {
        return scoreLines
            .stream()
            .collect(Collectors.groupingBy(ScoreLine::getPlayer))
            .entrySet()
            .stream()
            .map(match -> {
                Map<String, List<FrameResult>> framesPerPlayer = new HashMap<>();
                List<FrameResult> framesResult = new ArrayList<>();
                int tempScore = 0, frameNumber = 1, matchNumbers = 1;
                List<String> tempPinsKnockedDown = new ArrayList<>();

                for (ScoreLine scoreLine : match.getValue()) {
                    String pinsKnockedDown = scoreLine.getPinsKnockedDown();
                    int matchValue = BowlingResultRepresentationEnum.scoreFromPinsKnockedDown(pinsKnockedDown);
                    tempPinsKnockedDown.add(pinsKnockedDown);
                    if (isMatchEnded(matchNumbers, matchValue)) {
                        framesResult.add(FrameResult.FrameResultBuilder.aFrameResult()
                                .withNumber(frameNumber++)
                                .withPinsKnockedDown(Collections.unmodifiableList(tempPinsKnockedDown))
                                .withPlayer(match.getKey())
                                .withScore(tempScore + matchValue)
                                .build());
                        matchNumbers = 1;
                        tempScore = 0;
                        tempPinsKnockedDown.clear();
                    } else {
                        matchNumbers++;
                        tempScore += matchValue;
                    }
                }
                framesPerPlayer.put(match.getKey(), framesResult);
                return framesPerPlayer;
            })
            .reduce(new HashMap<>(), (resultMap, frameResultList) -> {
                resultMap.putAll(frameResultList);
                return resultMap;
            });
    }

    //TODO Test values
    private boolean isMatchEnded(int matchNumbers, int matchValue) {
        return matchNumbers == 2 || (matchValue == 10);
    }
}
