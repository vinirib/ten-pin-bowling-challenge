package com.jobsity.game.validator;

import com.jobsity.dto.BowlingResultRepresentationEnum;
import com.jobsity.dto.FrameResult;
import com.jobsity.exception.InvalidBowlingInputException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TenPinBowlingValidator implements BowlingValidator {

    @Override
    public void validateMatch(Map<String, List<FrameResult>> matchResultMap) {
        matchResultMap.forEach((player,results) -> {
            if (isMoreThanTwelveMatches(results) && isPerfectMatch(results)) {
                throw new InvalidBowlingInputException("Player " + player + " played a perfect match but also "
                        + "exceeded matches limit");
            }
        });
    }

    private boolean isMoreThanTwelveMatches(List<FrameResult> results) {
        return results.stream()
                .flatMap(fr -> fr.getPinsKnockedDown().stream())
                .count() > 12;
    }

    private boolean isPerfectMatch(List<FrameResult> results) {
        return results
                .stream()
                .flatMap(r -> r.getPinsKnockedDown().stream())
                .map(BowlingResultRepresentationEnum::scoreFromPinsKnockedDown)
                .allMatch(match -> match == 10);
    }
}
