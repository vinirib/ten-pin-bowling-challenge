package com.jobsity.writer;

import com.jobsity.dto.ScoreLine;

import java.util.List;
import java.util.stream.IntStream;

public class ConsoleScoreWriter implements ScoreWriter {

    private static final String HEADER = buildHeader();

    @Override
    public void writeResult(List<ScoreLine> scoreLines) {

    }

    private static String buildHeader() {
        StringBuilder header = new StringBuilder("Frame\t");
        IntStream.range(1, 10).boxed()
                .forEach((n) -> header.append(n + "\t  \t"));
        return header.toString();
    }
}
