package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class SolveAttemptModelAssembler implements RepresentationModelAssembler<SolveAttempt, EntityModel<SolveAttempt>> {
    @Override
    public EntityModel<SolveAttempt> toModel(SolveAttempt attempt) {
        return EntityModel.of(attempt,
            linkTo(methodOn(SolveAttemptController.class).one(attempt.getId())).withSelfRel(),
            linkTo(methodOn(SolveAttemptController.class).all()).withRel("solveattempts"));
    }
}
