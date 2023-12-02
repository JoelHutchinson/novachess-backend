const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

import PuzzleBoard from './components/PuzzleBoard';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
            users: [],
            puzzles: []
        };
	}

	componentDidMount() {
        client({method: 'GET', path: '/api/puzzles'}).done(response => {
			this.setState({puzzles: response.entity._embedded.puzzles});
		});
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
                <PuzzleBoard />
            </div>
			
		)
	}
};

ReactDOM.render(
	<App />,
	document.getElementById('react')
);