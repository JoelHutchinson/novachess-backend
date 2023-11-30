const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
            users: [],
            puzzles: []
        };
	}

	componentDidMount() {
		client({method: 'GET', path: '/api/users'}).done(response => {
			this.setState({users: response.entity._embedded.userList});
		});

        client({method: 'GET', path: '/api/puzzles'}).done(response => {
			this.setState({puzzles: response.entity._embedded.puzzleList});
		});
	}

	render() {
		return (
            <div>
                <table>
                    <tbody>
                        {this.state.users.map(user => {
                            return (
                                <tr key={user.username}>
                                    <td>{user.username}</td>
                                    <td>{user.password}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
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
            </div>
			
		)
	}
};

ReactDOM.render(
	<App />,
	document.getElementById('react')
);