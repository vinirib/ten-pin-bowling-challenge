package com.jobsity.reader;

import com.jobsity.dto.ScoreLine;
import com.jobsity.exception.InvalidFileException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreFileReader extends FileReader {

    public static final String FILE_DOES_NOT_REPRESENT_BOWLING_SCORE = "Your file does not represent a valid Bowling "
            + "Score";

    public ScoreFileReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public static List<ScoreLine> readFileAndParseResults(String filePath) {
        try {
            Path path = Path.of(filePath);
            if (!Files.isReadable(path)) {
                throw new InvalidFileException("File: "+ filePath + " not found..");
            }
            List<ScoreLine> scoreList = Files.lines(path)
                    .map(ScoreLine::new)
                    .collect(Collectors.toList());
            if(scoreList.isEmpty()) {
                throw new InvalidFileException(FILE_DOES_NOT_REPRESENT_BOWLING_SCORE);
            }
            return scoreList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
