package com.novachess;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PuzzleService {
    private final PuzzleRepository puzzleRepository;
    private final UserRepository userRepository;
    private final SolveRepository solveRepository;

    private final int RANGE = 200;

    public PuzzleService(PuzzleRepository puzzleRepository, 
                         UserRepository userRepository, 
                         SolveRepository solveRepository) {
        this.puzzleRepository = puzzleRepository;
        this.userRepository = userRepository;
        this.solveRepository = solveRepository;
    }

    public List<Puzzle> getSolvedPuzzlesByUser(User user) {
        List<Solve> solves = solveRepository.findByUserAndSuccess(user, true);
        return solves.stream()
                     .map(Solve::getPuzzle)
                     .collect(Collectors.toList());
    }

    public List<Puzzle> getSuggestedPuzzlesForUser(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(username));

        Integer userPuzzleRating = user.getPuzzleRating();
        List<Puzzle> solvedPuzzles = getSolvedPuzzlesByUser(user);

        return puzzleRepository.findByRatingBetween(userPuzzleRating - RANGE, userPuzzleRating + RANGE)
            .stream()
            .filter(puzzle -> !solvedPuzzles.contains(puzzle))
            .collect(Collectors.toList());
    }

    public Puzzle getNextSuggestedPuzzleForUser(String username) {
        List<Puzzle> suggestedPuzzles = getSuggestedPuzzlesForUser(username);

        if (suggestedPuzzles.isEmpty()) {
            // Handle the case where no puzzles are available. This could be returning null, throwing an exception, or returning a default puzzle.
            return null; // or handle this case as appropriate for your application
        }

        return suggestedPuzzles.get(0);
    }
}