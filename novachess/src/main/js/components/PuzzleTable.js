import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

export default function PuzzleTable(props) {
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
                        <TableRow key={puzzle.fen}>
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