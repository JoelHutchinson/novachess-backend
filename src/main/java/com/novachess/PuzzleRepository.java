package com.novachess;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface PuzzleRepository extends JpaRepository<Puzzle, Long> {
    // Method to find puzzles within a specific difficulty rating range
    List<Puzzle> findByRatingBetween(Integer minDifficulty, Integer maxDifficulty);
}