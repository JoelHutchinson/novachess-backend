package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

// tag::hateoas-imports[]
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
// end::hateoas-imports[]

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.IanaLinkRelations;

@RestController
class PuzzleController {

	private final PuzzleRepository repository;
	private final PuzzleModelAssembler assembler;

	private final PuzzleService service;

	PuzzleController(PuzzleRepository repository, PuzzleModelAssembler assembler, PuzzleService service) {
		this.repository = repository;
		this.assembler = assembler;
		this.service = service;
	}


	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/api/puzzles")
	CollectionModel<EntityModel<Puzzle>> all() {
		List<EntityModel<Puzzle>> puzzles = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());

		return CollectionModel.of(puzzles, linkTo(methodOn(PuzzleController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	// Single item
	@GetMapping("/api/puzzles/{id}")
	EntityModel<Puzzle> one(@PathVariable Long id) {
		Puzzle puzzle = repository.findById(id)
			.orElseThrow(() -> new PuzzleNotFoundException(id));

		return assembler.toModel(puzzle);
	}

	// Endpoint to get next suggested puzzle for a given user
	@GetMapping("/api/users/{username}/nextSuggestedPuzzle")
    public EntityModel<Puzzle> getNextSuggestedPuzzle(@PathVariable String username) {
        Puzzle puzzle = service.getNextSuggestedPuzzleForUser(username);
        
		return assembler.toModel(puzzle);
    }

	// Endpoint to get puzzles by rating
    @GetMapping("/api/puzzles")
    CollectionModel<EntityModel<Puzzle>> allByRating(
            @RequestParam(required = false) Integer minRating,
            @RequestParam(required = false) Integer maxRating) {

        List<EntityModel<Puzzle>> puzzles;

        if (minRating != null && maxRating != null) {
            puzzles = repository.findByRatingBetween(minRating, maxRating).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        } else {
            // Fallback if no parameters are provided
            puzzles = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        }

        return CollectionModel.of(puzzles, 
                linkTo(methodOn(PuzzleController.class).allByRating(minRating, maxRating)).withSelfRel());
    }

	@PatchMapping("/api/puzzles/{id}/upvote")
	ResponseEntity<?> upvotePuzzle(@PathVariable Long id) {
		Puzzle upvotedPuzzle = repository.findById(id)
			.map(puzzle -> {
				puzzle.setPopularity(puzzle.getPopularity() + 1);
				return repository.save(puzzle);
			})
			.orElseThrow(() -> new PuzzleNotFoundException(id));

		EntityModel<Puzzle> puzzleEntityModel = assembler.toModel(upvotedPuzzle);
		
		return ResponseEntity
			.created(puzzleEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(puzzleEntityModel);
	}

	@PatchMapping("/api/puzzles/{id}/downvote")
	ResponseEntity<EntityModel<Puzzle>> downvotePuzzle(@PathVariable Long id) {
		Puzzle downvotedPuzzle = repository.findById(id)
			.map(puzzle -> {
				puzzle.setPopularity(puzzle.getPopularity() - 1);
				return repository.save(puzzle);
			})
			.orElseThrow(() -> new PuzzleNotFoundException(id));

		EntityModel<Puzzle> puzzleEntityModel = assembler.toModel(downvotedPuzzle);
		
		return ResponseEntity
			.created(puzzleEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
			.body(puzzleEntityModel);
	}
}