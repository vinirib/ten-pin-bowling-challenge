package com.jobsity.dto;

import com.jobsity.exception.InvalidScoreLineException;

import java.util.ArrayList;
import java.util.List;

public class PlayerMatches {

    private static final String VALID_INPUTS = "(F|X|\\/)";
    private final String name;
    private final List<String> results = new ArrayList<>();

    public PlayerMatches(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addResult(String pinFall) {
        if (pinFall.matches("\\d*")
                && Integer.parseInt(pinFall) <= 10 ||
                pinFall.matches(VALID_INPUTS)) {
            this.results.add(pinFall);
        } else {
            throw new InvalidScoreLineException("Invalid line result: " + pinFall);
        }
    }

    public List<String> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "PlayerMatches{" +
                "name='" + name + '\'' +
                ", results=" + results.stream().reduce((k, v) -> k) +
                '}';
    }
}
