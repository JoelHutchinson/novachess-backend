const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

import PuzzleScreen from './components/PuzzleScreen';

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
            users: [],
        };
	}

	render() {
		return (
            <PuzzleScreen />                
		)
	}
};

ReactDOM.render(
	<App />,
	document.getElementById('react')
);