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
class UserController {

	private final UserRepository repository;
	private final UserModelAssembler assembler;

	UserController(UserRepository repository, UserModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}


	// Aggregate root
	// tag::get-aggregate-root[]
	@GetMapping("/users")
	CollectionModel<EntityModel<User>> all() {
		List<EntityModel<User>> users = repository.findAll().stream()
			.map(assembler::toModel)
			.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
		}
	// end::get-aggregate-root[]

	// Single item
	@GetMapping("/users/{id}")
	EntityModel<User> one(@PathVariable long id) {
		User user = repository.findById(id)
			.orElseThrow(() -> new UserNotFoundException(id));

		return assembler.toModel(user);
	}

    @PostMapping("/users")
    ResponseEntity<?> newUser(@RequestBody User newUser) {
        EntityModel<User> userEntityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity
            .created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
            .body(userEntityModel);
    }

    @PutMapping("/users/{id}")
    ResponseEntity<?> replaceUser(@RequestBody User newUser, @PathVariable long id) {
        User updatedUser = repository.findById(id) //
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setPassword(newUser.getPassword());
					return repository.save(user);
				}) //
				.orElseGet(() -> {
                    newUser.setId(id);
					return repository.save(newUser);
				});

		EntityModel<User> entityModel = assembler.toModel(updatedUser);

		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/users/{id}")
	ResponseEntity<?> deleteUser(@PathVariable long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}