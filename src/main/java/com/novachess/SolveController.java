package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.IanaLinkRelations;

@RestController
class SolveController {

    private final SolveRepository repository;
    private final SolveModelAssembler assembler;
    private final UserRepository userRepository;
    private final PuzzleRepository puzzleRepository;

    SolveController(SolveRepository repository,
                           SolveModelAssembler assembler,
                           UserRepository userRepository,
                           PuzzleRepository puzzleRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.userRepository = userRepository;
        this.puzzleRepository = puzzleRepository;
    }

    @GetMapping("/api/solves")
    CollectionModel<EntityModel<Solve>> all() {
        List<EntityModel<Solve>> attempts = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(attempts, linkTo(methodOn(SolveController.class).all()).withSelfRel());
    }

    @GetMapping("/api/solves/{id}")
    EntityModel<Solve> one(@PathVariable Long id) {
        Solve attempt = repository.findById(id)
            .orElseThrow(() -> new SolveNotFoundException(id));

        return assembler.toModel(attempt);
    }

    @PostMapping("/api/solves")
    ResponseEntity<?> createSolveAttempt(@RequestBody Solve newSolve) {
        // Validate the user and puzzle exist
        userRepository.findById(newSolve.getUser().getId())
            .orElseThrow(() -> new UserNotFoundException(newSolve.getUser().getEmail()));
        puzzleRepository.findById(newSolve.getPuzzle().getId())
            .orElseThrow(() -> new PuzzleNotFoundException(newSolve.getPuzzle().getId()));

        Solve savedSolveAttempt = repository.save(newSolve);
        EntityModel<Solve> entityModel = assembler.toModel(savedSolveAttempt);

        return ResponseEntity
            .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
            .body(entityModel);
    }
}
