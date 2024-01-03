package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.model.Score;
import com.example.demo.model.Teacher;

public interface ScoreService {
    void  saveScore(Score score);
    List<Score> getAllScores();
    Map<String, List<Score>> getScoresGroupedByStudent();
    Map<String, Double> getTotalScoreByStudent();
    Map<String, Double> getAverageScoreByStudent();
    Map<String, Long> getRankByStudent();
    Score getScoreByNumber(long id);
}
