package com.novachess;

import org.springframework.data.jpa.repository.JpaRepository;

interface PuzzleRepository extends JpaRepository<Puzzle, Long> {
}
