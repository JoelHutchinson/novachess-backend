package com.novachess;

class UserNotFoundException extends RuntimeException {

	UserNotFoundException(String email) {
		super("Could not find user " + email);
	}
}
