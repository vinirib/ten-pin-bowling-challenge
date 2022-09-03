package com.jobsity.game;

import com.jobsity.dto.BowlingResultRepresentationEnum;
import com.jobsity.dto.FrameResult;
import com.jobsity.dto.ScoreLine;
import com.jobsity.exception.InvalidBowlingInputException;
import com.jobsity.game.result.BowlingResult;
import com.jobsity.game.result.TenPinBowlingResult;
import com.jobsity.game.validator.BowlingValidator;
import com.jobsity.game.validator.TenPinBowlingValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TenPinBowlingGame implements BowlingGame {

    private static final int DEFAULT_FRAMES_QUANTITY = 10;
    private final BowlingResult tenPinBowlingResult;
    private final BowlingValidator tenPinBowlingValidator;

    public TenPinBowlingGame() {
        this.tenPinBowlingResult = new TenPinBowlingResult();
        this.tenPinBowlingValidator = new TenPinBowlingValidator();
    }

    @Override
    public Map<String, List<FrameResult>> receiveScoreLinesAndMatchResult(List<ScoreLine> scoreLines) {
        Map<String, List<FrameResult>> matchResultMap = scoreLines
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
                        if (frameNumber > DEFAULT_FRAMES_QUANTITY) {
                            throw new InvalidBowlingInputException("Player "+match.getKey()+ " played more than ten"
                                    + " frames");
                        }
                        String pinsKnockedDown = scoreLine.getPinsKnockedDown();
                        int matchValue = BowlingResultRepresentationEnum.scoreFromPinsKnockedDown(pinsKnockedDown);
                        tempPinsKnockedDown.add(pinsKnockedDown);
                        if (isMatchEnded(tempScore, matchNumbers, matchValue, frameNumber)) {
                            framesResult.add(FrameResult.FrameResultBuilder.aFrameResult()
                                    .withNumber(frameNumber++)
                                    .withPinsKnockedDown(new ArrayList<>(tempPinsKnockedDown))
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
        tenPinBowlingValidator.validateMatch(matchResultMap);
        matchResultMap.forEach((player, results) -> tenPinBowlingResult.matchBowlingResult(results));
        return matchResultMap;
    }


    private boolean isMatchEnded(int tempScore, int matchNumbers, int matchValue, int frameNumber) {
        boolean isLastTurn = frameNumber == DEFAULT_FRAMES_QUANTITY;
        return isLastTurn ? matchNumbers == 3 || (matchNumbers == 2 && (tempScore + matchValue < 10)) :
                matchNumbers == 2 || (matchValue == 10);
    }
}
