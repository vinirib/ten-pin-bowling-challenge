package com.jobsity.writer;

import com.jobsity.dto.FrameResult;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ConsoleScoreWriter implements ScoreWriter {

    private static final String HEADER = buildHeader();

    @Override
    public void writeResult(Map<String, List<FrameResult>> tenPinBowlingGameResultMap) {

    }

    private static String buildHeader() {
        StringBuilder header = new StringBuilder("Frame\t");
        IntStream.range(1, 10).boxed()
                .forEach((n) -> header.append(n + "\t  \t"));
        return header.toString();
    }
}
