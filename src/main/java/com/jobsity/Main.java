package com.jobsity;

import com.jobsity.dto.FrameResult;
import com.jobsity.dto.ScoreLine;
import com.jobsity.exception.InvalidFileException;
import com.jobsity.game.BowlingGame;
import com.jobsity.game.TenPinBowlingGame;
import com.jobsity.reader.ScoreFileReader;
import com.jobsity.writer.ScoreWriter;
import com.jobsity.writer.TenPinBowlingConsoleWriter;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new InvalidFileException("You should pass the file location..");
        }
        List<ScoreLine> scoreLines = ScoreFileReader.readFileAndParseResults(args[0]);
        BowlingGame tenPinBowlingGame = new TenPinBowlingGame();
        Map<String, List<FrameResult>> tenPinBowlingGameResultMap = tenPinBowlingGame
                .receiveScoreLinesAndMatchResult(scoreLines);
        ScoreWriter consoleScoreWriter = new TenPinBowlingConsoleWriter();
        consoleScoreWriter.writeResult(tenPinBowlingGameResultMap);
    }
}
