package com.novachess;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


// tag::hateoas-imports[]
// end::hateoas-imports[]
@RestController
class PuzzleController {

	private final PuzzleRepository repository;

	PuzzleController(PuzzleRepository repository) {
		this.repository = repository;
	}


	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/puzzles")
	List<Puzzle> all() {
		return repository.findAll();
	}
	// end::get-aggregate-root[]

	// Single item
	
	@GetMapping("/puzzles/{id}")
	Puzzle one(@PathVariable Long id) {
		return repository.findById(id)
            .orElseThrow(() -> new PuzzleNotFoundException(id));
	}

	@PatchMapping("/puzzles/{id}/upvote")
	Puzzle upvotePuzzle(@PathVariable Long id) {
		Puzzle upvotedPuzzle = repository.findById(id)
			.map(puzzle -> {
				puzzle.setPopularity(puzzle.getPopularity() + 1);
				return repository.save(puzzle);
			})
			.orElseThrow(() -> new PuzzleNotFoundException(id));

		return upvotedPuzzle;
	}

	@PatchMapping("/puzzles/{id}/downvote")
	Puzzle downvotePuzzle(@PathVariable Long id) {
		Puzzle downvotedPuzzle = repository.findById(id)
			.map(puzzle -> {
				puzzle.setPopularity(puzzle.getPopularity() - 1);
				return repository.save(puzzle);
			})
			.orElseThrow(() -> new PuzzleNotFoundException(id));

		return downvotedPuzzle;
	}
}