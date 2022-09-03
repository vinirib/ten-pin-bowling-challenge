package com.jobsity.game;

import com.jobsity.dto.FrameResult;
import com.jobsity.dto.ScoreLine;

import java.util.List;
import java.util.Map;

public interface BowlingGame {

    Map<String, List<FrameResult>> receiveScoreLinesAndMatchResult(List<ScoreLine> scoreLines);
}
