package com.novachess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(PuzzleRepository puzzleRepository, UserRepository userRepository) {

    return args -> {
        // Load sample puzzles.
        /*
        puzzleRepository.save(new Puzzle(
            "q3k1nr/1pp1nQpp/3p4/1P2p3/4P3/B1PP1b2/B5PP/5K2 b k - 0 17",
            "e8d7 a2e6 d7d8 f7f8",
            1760,
            0));
        puzzleRepository.save(new Puzzle(
            "r3r1k1/p4ppp/2p2n2/1p6/3P1qb1/2NQR3/PPB2PP1/R1B3K1 w - - 5 18",
            "e3g3 e8e1 g1h2 e1c1 a1c1 f4h6 h2g1 h6c1",
            2671,
            0));
        puzzleRepository.save(new Puzzle(
            "Q1b2r1k/p2np2p/5bp1/q7/5P2/4B3/PPP3PP/2KR1B1R w - - 1 17",
            "d1d7 a5e1 d7d1 e1e3 c1b1 e3b6",
            2235,
            0)); 
        puzzleRepository.save(new Puzzle(
            "1k1r4/pp3pp1/2p1p3/4b3/P3n1P1/8/KPP2PN1/3rBR1R b - - 2 31",
            "b8c7 e1a5 b7b6 f1d1",
            998,
            0));
        puzzleRepository.findAll().forEach(puzzle -> log.info("Preloaded " + puzzle));

        // Load sample users.
        userRepository.save(new User(
            "Joel Hutchinson",
            "joel.hutchinson2411@gmail.com",
            "abc"));
        userRepository.save(new User(
            "John Doe",
            "john.doe@gmail.com",
            "cba"));
        userRepository.findAll().forEach(user -> log.info("Preloaded " + user));
        */
    };
  }
}