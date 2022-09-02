package com.jobsity.reader;

import com.jobsity.dto.ScoreLine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScoreFileReader extends FileReader {

    public ScoreFileReader(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public static List<ScoreLine> readFileAndParseResults(String filePath) {
        Objects.requireNonNull(filePath, "Score file path is require to execute the program..");
        try {
            List<ScoreLine> scoreList = Files.lines(Paths.get(filePath))
                    .map(ScoreLine::new)
                    .collect(Collectors.toList());
            return scoreList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
