export interface PuzzleApiResponse {
    entity: {
        _embedded: {
            puzzles: Puzzle[];
        };
    };
}