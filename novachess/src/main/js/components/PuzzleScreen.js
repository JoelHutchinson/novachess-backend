const React = require('react');

import PuzzleBoard from "./PuzzleBoard";


class PuzzleScreen extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
            users: [],
            puzzles: [],
            currentPuzzleIndex: 0
        };

        this.handleNextPuzzleClick = this.handleNextPuzzleClick.bind(this);
	}

	componentDidMount() {
        client({method: 'GET', path: '/api/puzzles'}).done(response => {
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

	render() {
		return (
            <div>
                <h2>Puzzles</h2>
                <table>
                    <thead>
                        <tr>
                            <th>FEN</th>
                            <th>Moves</th>
                            <th>Popularity Score</th>
                        </tr>
                    </thead>
                    <tbody>                    
                        {this.state.puzzles.map(puzzle => {
                            return (
                                <tr key={puzzle.fen}>
                                    <td>{puzzle.fen}</td>
                                    <td>{puzzle.moves}</td>
                                    <td>{puzzle.popularity}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
                {this.state.puzzles.length > 0 &&
                <PuzzleBoard
                puzzle={this.state.puzzles[this.state.currentPuzzleIndex]}
                loadNextPuzzle={this.handleNextPuzzleClick}
                />}
                <button onClick={this.handleNextPuzzleClick}>Next Puzzle</button>
            </div>
			
		)
	}
};

export default PuzzleScreen;