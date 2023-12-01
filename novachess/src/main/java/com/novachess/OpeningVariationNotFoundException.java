package com.novachess;

class OpeningVariationNotFoundException extends RuntimeException {

	OpeningVariationNotFoundException(long id) {
		super("Could not find opening variation with id " + id);
	}
}
