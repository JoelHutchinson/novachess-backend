export interface Puzzle {
    id?: string;
    fen: string;
    moves: string[];
    rating: number;
    popularity: number;
};

export type Move = {
    from: string;
    to: string;
    promotion?: string;
};

export type MoveOutcome = 'correct' | 'incorrect' | 'illegal';