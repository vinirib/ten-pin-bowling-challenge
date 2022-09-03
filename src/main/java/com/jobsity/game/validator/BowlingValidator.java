package com.jobsity.game.validator;

import com.jobsity.dto.FrameResult;

import java.util.List;
import java.util.Map;

public interface BowlingValidator {
    void validateMatch(Map<String, List<FrameResult>> matchResultMap);
}
