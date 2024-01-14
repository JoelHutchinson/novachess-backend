package com.novachess;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface SolveRepository extends JpaRepository<Solve, Long> {
    // Method to find solves by a user and successful status
    List<Solve> findByUserAndSuccess(User user, boolean success);
}