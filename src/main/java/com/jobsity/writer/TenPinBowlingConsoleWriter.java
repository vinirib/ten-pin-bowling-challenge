package com.jobsity.writer;

import com.jobsity.dto.BowlingResultRepresentationEnum;
import com.jobsity.dto.FrameResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TenPinBowlingConsoleWriter extends ConsoleScoreWriter {

    @Override
    public void writeResult(Map<String, List<FrameResult>> tenPinBowlingGameResultMap) {

        String frameResult = prepareFrame(tenPinBowlingGameResultMap);

        String players = tenPinBowlingGameResultMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(playerResultMap -> {
                    String playerName = playerResultMap.getKey();
                    String pinfalls = preparePinfalls(playerResultMap);
                    String score = prepareScore(playerResultMap);
                    return playerName + pinfalls + score;
                })
                .collect(Collectors.joining(BREAK_LINE));
        System.out.println(frameResult + players + BREAK_LINE);
    }

    private String preparePinfalls(Map.Entry<String, List<FrameResult>> playerResultMap) {
        return playerResultMap.getValue()
                .stream()
                .map(frameResult -> {
                    List<String> pinsKnockedDown = frameResult.getPinsKnockedDown();
                    if (frameResult.isStrikeOrSpare()) {
                        if (frameResult.isStrike()) {
                            verifyStrike(frameResult, pinsKnockedDown);
                        } else {
                            pinsKnockedDown.remove(1);
                            pinsKnockedDown.add(BowlingResultRepresentationEnum
                                    .SPARE
                                    .getRepresentation());
                        }
                    }
                    return pinsKnockedDown;
                })
                .flatMap(List<String>::stream)
                .map(result -> result.equals(String.valueOf(BowlingResultRepresentationEnum.STRIKE.getValue()))
                        ? BowlingResultRepresentationEnum.STRIKE.getRepresentation() : result)
                .collect(Collectors.joining("\t", "\nPinfalls\t", BREAK_LINE));
    }

    private void verifyStrike(FrameResult f, List<String> pinsKnockedDown) {
        if (pinsKnockedDown.size() == 1) {
            pinsKnockedDown.add(0, EMPTY_STRING);
        } else if (f.isStrikeAndSpare()) {
            if (pinsKnockedDown.get(0).equals(String.valueOf(BowlingResultRepresentationEnum
                    .STRIKE.getValue()))) {
                pinsKnockedDown.remove(2);
                pinsKnockedDown.add(BowlingResultRepresentationEnum.SPARE.getRepresentation());
            } else {
                pinsKnockedDown.remove(1);
                pinsKnockedDown.add(1, BowlingResultRepresentationEnum.SPARE.getRepresentation());
            }
        }
    }

    private String prepareFrame(Map<String, List<FrameResult>> tenPinBowlingGameResultMap) {
        return tenPinBowlingGameResultMap
                .entrySet()
                .stream()
                .findAny()
                .map(Map.Entry::getValue)
                .orElseThrow()
                .stream()
                .map(FrameResult::getNumber)
                .map(String::valueOf)
                .collect(Collectors.joining("\t\t", "Frame\t\t", BREAK_LINE));
    }

    private String prepareScore(Map.Entry<String, List<FrameResult>> frameResultList) {
        return frameResultList.getValue()
                .stream()
                .map(FrameResult::getAccumulativeScore)
                .map(String::valueOf)
                .collect(Collectors.joining("\t\t", "Score\t\t", EMPTY_STRING));
    }

}
