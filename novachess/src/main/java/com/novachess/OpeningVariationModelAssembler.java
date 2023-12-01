package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class OpeningVariationModelAssembler implements RepresentationModelAssembler<OpeningVariation, EntityModel<OpeningVariation>> {
    @Override
    public EntityModel<OpeningVariation> toModel(OpeningVariation openingVariation) {
        return EntityModel.of(openingVariation,
            linkTo(methodOn(OpeningVariationController.class).one(openingVariation.getId())).withSelfRel(),
            linkTo(methodOn(OpeningVariationController.class).all()).withRel("openingVariations"));
    }
}
