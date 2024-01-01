package com.example.demo.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}