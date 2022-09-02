package com.jobsity.writer;

import com.jobsity.dto.ScoreLine;

import java.util.List;

public interface ScoreWriter {
    void writeResult(List<ScoreLine> scoreLines);
}
