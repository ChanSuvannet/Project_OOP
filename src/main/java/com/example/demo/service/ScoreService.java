package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.model.Score;
import com.example.demo.model.Teacher;

public interface ScoreService {
    List<Score> getAllScores();
    void saveScore(Score score);
    Score getScoreByNumber(long id);

    Map<String, List<Score>> getScoresGroupedByStudent();
    Map<String, Double> getTotalScoreByStudent();
    Map<String, Double> getAverageScoreByStudent();
    Map<String, Long> getRankByStudent();
    // Method to calculate GPA based on average score
    Map<String, String> getGradeByStudent();
}

