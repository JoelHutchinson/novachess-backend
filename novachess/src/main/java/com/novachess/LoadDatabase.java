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
  CommandLineRunner initDatabase(PuzzleRepository repository) {

    return args -> {
        log.info("Preloading " + repository.save(new Puzzle(
            "q3k1nr/1pp1nQpp/3p4/1P2p3/4P3/B1PP1b2/B5PP/5K2 b k - 0 17",
            "e8d7 a2e6 d7d8 f7f8",
            1760,
            0)));
        log.info("Preloading " + repository.save(new Puzzle(
            "r3r1k1/p4ppp/2p2n2/1p6/3P1qb1/2NQR3/PPB2PP1/R1B3K1 w - - 5 18",
            "e3g3 e8e1 g1h2 e1c1 a1c1 f4h6 h2g1 h6c1",
            2671,
            0)));
    };
  }
}