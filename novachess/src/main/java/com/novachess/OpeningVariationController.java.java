package com.novachess;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

// tag::hateoas-imports[]
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
// end::hateoas-imports[]

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.hateoas.IanaLinkRelations;


@RestController
class OpeningVariationController {

	private final OpeningVariationRepository repository;
	private final OpeningVariationModelAssembler assembler;

	OpeningVariationController(OpeningVariationRepository repository, OpeningVariationModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/api/openingVariations")
	CollectionModel<EntityModel<OpeningVariation>> all() {
		List<EntityModel<OpeningVariation>> openingVariations = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());

		return CollectionModel.of(openingVariations, linkTo(methodOn(OpeningVariationController.class).all()).withSelfRel());
		}
	// end::get-aggregate-root[]

	// Single item
	@GetMapping("/api/openingVariations/{id}")
	EntityModel<OpeningVariation> one(@PathVariable long id) {
		OpeningVariation openingVariation = repository.findById(id)
			.orElseThrow(() -> new OpeningVariationNotFoundException(id));

		return assembler.toModel(openingVariation);
	}

    @PostMapping("/api/openingVariations")
    ResponseEntity<?> newOpeningVariation(@RequestBody OpeningVariation newOpeningVariation) {
        EntityModel<OpeningVariation> openingVariationEntityModel = assembler.toModel(repository.save(newOpeningVariation));

        return ResponseEntity
            .created(openingVariationEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(openingVariationEntityModel);
    }

    @PutMapping("/api/openingVariations/{id}")
    ResponseEntity<?> replaceOpeningVariation(@RequestBody OpeningVariation newOpeningVariation, @PathVariable long id) {
        OpeningVariation updatedOpeningVariation = repository.findById(id) //
				.map(openingVariation -> {
					openingVariation.setStartFen(newOpeningVariation.getStartFen());
					openingVariation.setSanMoves(newOpeningVariation.getSanMoves());
					return repository.save(openingVariation);
				}) //
				.orElseGet(() -> {
                    newOpeningVariation.setId(id);
					return repository.save(newOpeningVariation);
				});

		EntityModel<OpeningVariation> entityModel = assembler.toModel(updatedOpeningVariation);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/api/openingVariations/{id}")
	ResponseEntity<?> deleteOpeningVariation(@PathVariable long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}