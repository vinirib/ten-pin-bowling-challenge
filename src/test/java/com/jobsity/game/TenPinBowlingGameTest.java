package com.jobsity.game;

import com.jobsity.dto.FrameResult;
import com.jobsity.dto.ScoreLine;
import com.jobsity.exception.InvalidBowlingInputException;
import com.jobsity.reader.ScoreFileReader;
import com.jobsity.util.ResourcesFileReaderUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TenPinBowlingGameTest {

    private static final String JEFF = "Jeff";
    private static final String JOHN = "John";
    private static final String CARL = "Carl";
    private static final String OVER_THAN_TEN_FRAMES_RESULT_MESSAGE = "Player Jeff played more than ten frames";
    private final BowlingGame bowlingGame = new TenPinBowlingGame();

    @Test
    public void normal_match_result() {
        List<ScoreLine> scoreLineList = getScoreLines("./positive/scores.txt");
        Map<String, List<FrameResult>> scoreMatchMap = bowlingGame
                .receiveScoreLinesAndMatchResult(scoreLineList);
        Integer jeffTotalScore = scoreMatchMap.get(JEFF)
                .stream()
                .reduce((a, b) -> b)
                .get()
                .getAccumulativeScore();
        Integer johnTotalScore = scoreMatchMap.get(JOHN).stream()
                .reduce((a, b) -> b)
                .get()
                .getAccumulativeScore();
        assertNotNull(scoreMatchMap);
        assertTrue(scoreMatchMap.containsKey(JEFF));
        assertTrue(scoreMatchMap.containsKey(JOHN));
        assertEquals(167, jeffTotalScore);
        assertEquals(151, johnTotalScore);
    }

    @Test
    public void perfect_match_result() {
        List<ScoreLine> scoreLineList = getScoreLines("./positive/perfect.txt");
        Map<String, List<FrameResult>> scoreMatchMap = bowlingGame
                .receiveScoreLinesAndMatchResult(scoreLineList);
        Integer carlTotalScore = scoreMatchMap.get(CARL)
                .stream()
                .reduce((a, b) -> b)
                .get()
                .getAccumulativeScore();
        assertNotNull(scoreMatchMap);
        assertTrue(scoreMatchMap.containsKey(CARL));
        assertEquals(300, carlTotalScore);
    }

    @Test
    public void over_than_ten_matches_should_throws_InvalidBowlingInputException() {
        List<ScoreLine> scoreLineList = getScoreLines("./positive/over-than-ten-frames.txt");
        assertThrows(InvalidBowlingInputException.class, () -> bowlingGame
                        .receiveScoreLinesAndMatchResult(scoreLineList),
                OVER_THAN_TEN_FRAMES_RESULT_MESSAGE);
    }

    private List<ScoreLine> getScoreLines(String resourceFilePath) {
        String file = ResourcesFileReaderUtil
                .getFile(resourceFilePath);
        return ScoreFileReader.readFileAndParseResults(file);
    }
}