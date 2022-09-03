package com.jobsity.reader;

import com.jobsity.dto.ScoreLine;
import com.jobsity.exception.InvalidBowlingInputException;
import com.jobsity.exception.InvalidFileException;
import com.jobsity.exception.InvalidScoreLineException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

class ScoreFileReaderTest {

    private static final String INVALID_LINE_DETECTED_MESSAGE = "Invalid line detected..Lorem ipsum dolor sit amet, "
            + "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
            + "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex "
            + "ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore"
            + " eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia "
            + "deserunt mollit anim id est laborum.\n";

    private static final String INVALID_MATCH_RESULT_MESSAGE = "Result value lorem is invalid";
    private static final String NEGATIVE_MATCH_RESULT_MESSAGE = "Less than zero pins knocked down was passed: -5";
    private static final String OVER_THAN_TEN_MATCH_RESULT_MESSAGE = "More than ten pins knocked down was passed: 22";


    @Test
    void read_empty_file_should_throw_invalid_file_exception() {
        String uri = getFile("./negative/empty.txt");
        Assertions.assertThrows(InvalidFileException.class, () -> ScoreFileReader.readFileAndParseResults(uri),
                ScoreFileReader.FILE_DOES_NOT_REPRESENT_BOWLING_SCORE);
    }

    @Test
    void read_file_with_extra_score_should_throw_invalid_file_exception() {
        String uri = getFile("./negative/extra-score.txt");
        List<ScoreLine> scoreLines = ScoreFileReader.readFileAndParseResults(uri);
    }

    @Test
    void read_file_invalid_format_should_throw_invalid_file_exception() {
        String uri = getFile("./negative/free-text.txt");
        Assertions.assertThrows(InvalidScoreLineException.class, () -> ScoreFileReader.readFileAndParseResults(uri),
                INVALID_LINE_DETECTED_MESSAGE);
    }

    @Test
    void read_file_invalid_text_in_match_result_should_throw_invalid_bowling_input_exception() {
        String uri = getFile("./negative/invalid-score.txt");
        Assertions.assertThrows(InvalidBowlingInputException.class, () -> ScoreFileReader.readFileAndParseResults(uri),
                INVALID_MATCH_RESULT_MESSAGE);
    }

    @Test
    void read_file_negative_value_in_match_result_should_throw_invalid_bowling_input_exception() {
        String uri = getFile("./negative/negative.txt");
        Assertions.assertThrows(InvalidBowlingInputException.class, () -> ScoreFileReader.readFileAndParseResults(uri),
                NEGATIVE_MATCH_RESULT_MESSAGE);
    }

    @Test
    void read_file_over_than_ten_value_in_match_result_should_throw_invalid_bowling_input_exception() {
        String uri = getFile("./positive/over-than-ten.txt");
        Assertions.assertThrows(InvalidBowlingInputException.class, () -> ScoreFileReader.readFileAndParseResults(uri),
                OVER_THAN_TEN_MATCH_RESULT_MESSAGE);
    }

    @Test
    void read_perfect_match_file() {
        String uri = getFile("./positive/perfect.txt");
        List<ScoreLine> scoreLines = ScoreFileReader.readFileAndParseResults(uri);
        Assertions.assertAll(IntStream.range(0, scoreLines.size())
                .mapToObj(i -> () -> Assertions.assertEquals(Integer.parseInt(scoreLines.get(i)
                                .getPinsKnockedDown()),
                        10)));
    }
    @Test
    void read_normal_match_file() {
        String uri = getFile("./positive/scores.txt");
        List<ScoreLine> scoreLines = ScoreFileReader.readFileAndParseResults(uri);
        Assertions.assertAll(IntStream.range(0, scoreLines.size())
                .mapToObj(i -> () -> {
                    Assertions.assertNotNull(scoreLines.get(i).getPinsKnockedDown());
                    Assertions.assertNotNull(scoreLines.get(i).getPlayer());
                }));
    }


    private String getFile(String resourcePath) {
        return Thread.currentThread().getContextClassLoader().getResource(resourcePath).getFile();
    }
}