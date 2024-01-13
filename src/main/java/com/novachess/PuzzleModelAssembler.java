package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PuzzleModelAssembler implements RepresentationModelAssembler<Puzzle, EntityModel<Puzzle>> {
    @Override
    public EntityModel<Puzzle> toModel(Puzzle puzzle) {
        return EntityModel.of(puzzle,
            linkTo(methodOn(PuzzleController.class).one(puzzle.getId())).withSelfRel(),
            linkTo(methodOn(PuzzleController.class).all()).withRel("puzzles"),
            linkTo(methodOn(PuzzleController.class).upvotePuzzle(puzzle.getId())).withRel("upvote"),
            linkTo(methodOn(PuzzleController.class).downvotePuzzle(puzzle.getId())).withRel("downvote"));
    }
}
