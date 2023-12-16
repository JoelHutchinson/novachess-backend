import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

import { Puzzle } from '../@types/puzzle';

interface PuzzleTableProps {
    puzzles: Puzzle[];
};

export default function PuzzleTable(props: PuzzleTableProps) {
    return (
        <Table>
            <TableHead>
                <TableRow>
                    <TableCell>FEN</TableCell>
                    <TableCell>Moves</TableCell>
                    <TableCell>Popularity Score</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>                    
                {props.puzzles.map(puzzle => {
                    return (
                        <TableRow key={puzzle.id + puzzle.fen}>
                            <TableCell>{puzzle.fen}</TableCell>
                            <TableCell>{puzzle.moves}</TableCell>
                            <TableCell>{puzzle.popularity}</TableCell>
                        </TableRow>
                    );
                })}
            </TableBody>
        </Table>
    );
}