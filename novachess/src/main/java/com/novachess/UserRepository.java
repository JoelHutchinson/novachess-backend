package com.novachess;

import org.springframework.data.repository.Repository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
interface UserRepository extends Repository<User, Long> {

}
