const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

import ChessboardWrapper from './components/ChessboardWrapper';

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
                <table>
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
                <ChessboardWrapper />
            </div>
			
		)
	}
};

ReactDOM.render(
	<App />,
	document.getElementById('react')
);