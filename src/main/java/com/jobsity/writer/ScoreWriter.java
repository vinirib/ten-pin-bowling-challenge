package com.jobsity.writer;

import com.jobsity.dto.FrameResult;

import java.util.List;
import java.util.Map;

public interface ScoreWriter {
    void writeResult(Map<String, List<FrameResult>> tenPinBowlingGameResultMap);
}
