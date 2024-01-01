package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Score;

public interface ScoreService {
    void  saveScore(Score score);
    List<Score> getAllScores();
    Map<String, List<Score>> getScoresGroupedByStudent();
}
