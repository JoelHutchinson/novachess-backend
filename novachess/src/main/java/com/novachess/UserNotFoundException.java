package com.novachess;

class UserNotFoundException extends RuntimeException {

	UserNotFoundException(String username) {
		super("Could not find user " + username);
	}
}
