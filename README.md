# Jobsity Java Challenge - Vinicius Ribeiro

## Assignment
<hr/>
The goal of this exercise is to demonstrate your ability to build a greenfield project, specifically a command-line application to score a game of ten-pin bowling. You can find the rules here.
The code should handle the bowling scores rules described in the specs and here.

## Mandatory Features
<hr/>

The program should run from the command-line and take a text file as input


The program should read the input text file and parse its content, which should have the results for several players bowling 10 frames each, written according to these guidelines:



Each line represents a player and a chance with the subsequent number of pins knocked down.
An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).
The columns in each row are tab-separated.

### Considerations

 - Unit test: Tests should cover at least the non-trivial classes and methods
    - Covered

 - Integration test: At least cover the three main cases: Sample input (2 players), perfect score, zero score
   - Covered

#### Scenarios covered
   - Good scenarios
     - Normal Ten Bowling Game result. `File:/resources/positive/scores.txt`
     - Perfect Match with all Strikes. `File:/resources/positive/perfect.txt`
   - Bad scenarios
     - No file path passed in the execution. `Execute jar without passing parameter`
     - Non-existing file passed in the execution. `Execute jar passing a wrong path`
     - Pinfall more than 10. `File:/resources/positive/over-than-ten-pinfall.txt`
     - Player playing more than ten frames. `File:/resources/positive/over-than-ten-frames.txt`
     - File exists but with empty content. `File:/resources/negative/empty.txt`
     - Perfect Match with extra score. `File:/resources/negative/extra-score.txt`
     - File with no valid content. `File:/resources/negative/free-text.txt`
     - Pinfall with invalid text in the pinfall. `File:/resources/negative/invalid-score.txt`
     - Pinfall with negative pinfall value. `File:/resources/negative/negative.txt`

#### Components used
   - Java 11+
   - Maven
   - Junit 5

#### Compile project

To compile project you will need maven, you must run
the command bellow in the root project to generate jar file:

```
mvn clean install
```

The jar file will be generated in the folder "target", with the name
`JavaChallenge-1.0-SNAPSHOT.jar`

#### Run BowlingChallenge

After you generated the jar file, you can run this command 
from the root folder:

```
java -jar target/JavaChallenge-1.0-SNAPSHOT.jar src/test/resources/positive/scores.txt
```

This will use the file path from `src/test/resources/positive/` folder to print the score file.
You can try to use the other example files to make a manual test.

All files were used in the unit test classes.


I hope you like the solution! :) 