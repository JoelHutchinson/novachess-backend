import React from 'react';
const client = require('../client');

import PuzzleBoard from "./PuzzleBoard";
import PuzzleTable from "./PuzzleTable";

import SwipeableDrawer from '@mui/material/SwipeableDrawer';
import Button from '@mui/material/Button';

import { Puzzle } from '../@types/puzzle'
import { PuzzleApiResponse } from '../@types/api';

interface PuzzleScreenState {
    puzzles: Puzzle[];
    currentPuzzleIndex: number;
    drawerOpen: boolean;
}

class PuzzleScreen extends React.Component<{}, PuzzleScreenState> {
	constructor(props: {}) {
		super(props);
		this.state = {
            puzzles: [],
            currentPuzzleIndex: 0,
            drawerOpen: false
        };

        this.handleNextPuzzleClick = this.handleNextPuzzleClick.bind(this);
	}

	componentDidMount() {
        client({method: 'GET', path: '/api/puzzles'}).done((response: PuzzleApiResponse) => {
			this.setState({puzzles: response.entity._embedded.puzzles});
		});
	}

    handleNextPuzzleClick() {
        this.setState((state) => ({
            currentPuzzleIndex: (state.currentPuzzleIndex + 1) % state.puzzles.length
        }));
        console.log(this.state.currentPuzzleIndex);
        console.log(this.state.puzzles[this.state.currentPuzzleIndex].fen);
    }

    toggleDrawer = (open: boolean) => (event: React.KeyboardEvent | React.MouseEvent) => {
        if (
          event &&
          event.type === 'keydown' &&
          ((event as React.KeyboardEvent).key === 'Tab' || (event as React.KeyboardEvent).key === 'Shift')
        ) {
          return;
        }
    
        this.setState({drawerOpen: open });
    };

	render() {
		return (
            <div style={{
                height: '100%'
            }}>
                <div style={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%'
                }}>
                    {this.state.puzzles.length > 0 &&
                    <PuzzleBoard
                    puzzle={this.state.puzzles[this.state.currentPuzzleIndex]}
                    loadNextPuzzle={this.handleNextPuzzleClick}
                    />}
                </div>
                <Button onClick={this.toggleDrawer(true)}>
                Puzzle Data
                </Button>
                <SwipeableDrawer
                    anchor={"bottom"}
                    open={this.state.drawerOpen}
                    onOpen={this.toggleDrawer(true)}
                    onClose={this.toggleDrawer(false)}
                >
                    <PuzzleTable puzzles={this.state.puzzles}/>
                </SwipeableDrawer>
            </div>
		)
	}
};

export default PuzzleScreen;