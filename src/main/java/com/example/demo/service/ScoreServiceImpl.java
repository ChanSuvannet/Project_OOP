package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Score;
import com.example.demo.repository.ScoreRepository;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public void saveScore(Score score) {
        scoreRepository.save(score);
    }

    @Override
    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }
    @Override
    public Map<String, List<Score>> getScoresGroupedByStudent() {
        // Fetch the scores from the database
        List<Score> scores = getAllScores();

        // Group the scores by the student's nameid
        return scores.stream()
            .collect(Collectors.groupingBy(score -> score.getStudent().getNameid()));
    }

}