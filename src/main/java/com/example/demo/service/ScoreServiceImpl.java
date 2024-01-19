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
import com.example.demo.model.Teacher;
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
    // ..... more 
    // UPDATE
    @Override
    public Score getScoreByNumber(long id) {
        Optional<Score> optional = scoreRepository.findById(id);// have or not
        Score score = null;
        if (optional.isPresent()) {
            score = optional.get();
        } else {
            throw new RuntimeException("Score not found for id :: " + id);
        }
        return score;
    }

    @Override
    public Map<String, List<Score>> getScoresGroupedByStudent() {
        // scores from the database
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
        Map<String, Double> averageScores = getAverageScoreByStudent();

        // Sort the averageScores in descending order, handling ties by sorting by name
        List<Map.Entry<String, Double>> sortedScores = new ArrayList<>(averageScores.entrySet());
        sortedScores.sort((entry1, entry2) -> {
            int scoreComparison = entry2.getValue().compareTo(entry1.getValue());
            if (scoreComparison != 0) {
                return scoreComparison;
            } else {
                // If scores are equal, sort by name
                return entry1.getKey().compareTo(entry2.getKey());
            }
        });

        // Generate the ranks, handling ties by assigning equal ranks
        Map<String, Long> ranks = new LinkedHashMap<>();
        long rank = 1;
        double previousScore = Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry : sortedScores) {
            if (entry.getValue() < previousScore) {
                ranks.put(entry.getKey(), rank);
                rank++;
            } else {
                ranks.put(entry.getKey(), rank - 1);
            }
            previousScore = entry.getValue();
        }

        return ranks;
    }
    // find grade student 
    @Override
    public Map<String, String> getGradeByStudent() {
        Map<String, Double> averageScores = getAverageScoreByStudent();
        Map<String, String> grades = new HashMap<>();

        for (Map.Entry<String, Double> entry : averageScores.entrySet()) {
            double averageScore = entry.getValue();
            String grade;

            // Determine the grade based on average score
            if (averageScore >= 85) {
                grade = "A";
            }else if(averageScore >= 80){
                 grade = "B+";
            }else if(averageScore >= 70){
                 grade = "B";
            }else if (averageScore >= 65) {
                grade = "C+";
            } else if (averageScore >= 50) {
                grade = "C";
            }else if (averageScore >= 45) {
                grade = "D";
            } else if (averageScore >= 40) {
                grade = "E";
            } else {
                grade = "F";
            }
            grades.put(entry.getKey(), grade);
        }

        return grades;
    }

}