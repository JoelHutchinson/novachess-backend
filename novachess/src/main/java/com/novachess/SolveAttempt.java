package com.novachess;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
class SolveAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "puzzle_id")
    private Puzzle puzzle;

    private LocalDateTime attemptTime;
    private boolean success;

    SolveAttempt() {}

    SolveAttempt(User user, Puzzle puzzle, LocalDateTime attemptTime, boolean success, String solutionAttempt) {
        this.user = user;
        this.puzzle = puzzle;
        this.attemptTime = attemptTime;
        this.success = success;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public LocalDateTime getAttemptTime() {
        return attemptTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public void setAttemptTime(LocalDateTime attemptTime) {
        this.attemptTime = attemptTime;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "SolveAttempt{" +
                "id=" + id +
                ", user=" + user +
                ", puzzle=" + puzzle +
                ", attemptTime=" + attemptTime +
                ", success=" + success + '\'' +
                '}';
    }
}
