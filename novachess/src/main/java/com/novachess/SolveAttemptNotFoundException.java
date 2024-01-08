package com.novachess;

class SolveAttemptNotFoundException extends RuntimeException {

    SolveAttemptNotFoundException(Long id) {
        super("Could not find solve attempt " + id);
    }
}
