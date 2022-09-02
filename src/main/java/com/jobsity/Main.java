package com.jobsity;

import com.jobsity.dto.ScoreLine;
import com.jobsity.reader.ScoreFileReader;
import com.jobsity.writer.ConsoleScoreWriter;
import com.jobsity.writer.ScoreWriter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<ScoreLine> scoreLines = ScoreFileReader.readFileAndParseResults(args[0]);
        ScoreWriter consoleScoreWriter = new ConsoleScoreWriter();
        consoleScoreWriter.writeResult(scoreLines);
    }
}
