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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.IanaLinkRelations;

@RestController
class UserController {

	private final UserRepository repository;
	private final UserModelAssembler assembler;

	UserController(UserRepository repository, UserModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/api/users")
	CollectionModel<EntityModel<User>> all() {
		List<EntityModel<User>> users = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

    // Single item
	// TODO: Username instead of email as path variable.
    @GetMapping("/api/users/{username}")
    EntityModel<User> one(@PathVariable String username) {
        User user = repository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException(username));

        return assembler.toModel(user);
    }

	@PostMapping("/api/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> userEntityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity
            .created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(userEntityModel);
    }
}