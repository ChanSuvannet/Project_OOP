package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Score;
import com.example.demo.repository.ScoreRepository;
import com.google.protobuf.Option;

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
    @Override
    public Map<String, Double> getTotalScoreByStudent() {
        List<Score> scores = getAllScores();
        return scores.stream()
            .collect(Collectors.groupingBy(score -> score.getStudent().getNameid(),
                    Collectors.summingDouble(Score::getGrade)));
    }

    @Override
public Map<String, Double> getAverageScoreByStudent() {
    List<Score> scores = getAllScores();
    return scores.stream()
        .collect(Collectors.groupingBy(score -> score.getStudent().getNameid(),
                Collectors.averagingDouble(Score::getGrade)))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> Math.round(e.getValue() * 100.0) / 100.0));
}
    @Override
    public Map<String, Long> getRankByStudent() {
        // List<Score> scores = getAllScores();
        Map<String, Double> averageScores = getAverageScoreByStudent();
    
        // Sort the averageScores in descending order
        List<Map.Entry<String, Double>> sortedScores = new ArrayList<>(averageScores.entrySet());
        sortedScores.sort(Map.Entry.<String, Double>comparingByValue().reversed());
    
        // Generate the ranks
        Map<String, Long> ranks = new LinkedHashMap<>();
        long rank = 1;
        for (Map.Entry<String, Double> entry : sortedScores) {
            ranks.put(entry.getKey(), rank++);
        }
    
        return ranks;
    }
}