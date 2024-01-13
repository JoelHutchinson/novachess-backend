package com.novachess;

class PuzzleNotFoundException extends RuntimeException {

	PuzzleNotFoundException(Long id) {
		super("Could not find puzzle " + id);
	}
}
