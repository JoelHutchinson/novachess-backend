package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.IanaLinkRelations;

@RestController
class SolveAttemptController {

    private final SolveAttemptRepository repository;
    private final SolveAttemptModelAssembler assembler;

    SolveAttemptController(SolveAttemptRepository repository, SolveAttemptModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/api/solveattempts")
    CollectionModel<EntityModel<SolveAttempt>> all() {
        List<EntityModel<SolveAttempt>> attempts = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(attempts, linkTo(methodOn(SolveAttemptController.class).all()).withSelfRel());
    }

    @GetMapping("/api/solveattempts/{id}")
    EntityModel<SolveAttempt> one(@PathVariable Long id) {
        SolveAttempt attempt = repository.findById(id)
            .orElseThrow(() -> new SolveAttemptNotFoundException(id));

        return assembler.toModel(attempt);
    }
}
