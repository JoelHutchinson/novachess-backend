package com.novachess;

import org.springframework.stereotype.Service;
import java.util.List;
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

    public List<Puzzle> getSuggestedPuzzlesForUser(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(username));

        Integer userPuzzleRating = user.getPuzzleRating();
        List<Puzzle> solvedPuzzles = solveRepository.findSolvedPuzzlesByUser(user);

        return puzzleRepository.findByRatingBetween(userPuzzleRating - RANGE, userPuzzleRating + RANGE)
            .stream()
            .filter(puzzle -> !solvedPuzzles.contains(puzzle))
            .collect(Collectors.toList());
    }
}