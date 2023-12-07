const React = require('react');

import { useState, useEffect } from "react";
import { Chess } from "chess.js";
import { Chessboard } from "react-chessboard";


export default function PuzzleBoard(props) {
    const [game, setGame] = useState(new Chess(props.puzzle.fen));
    const [playedMoves, setPlayedMoves] = useState([]);
    const [notPlayedMoves, setNotPlayedMoves] = useState(uciListToMoveStack(props.puzzle.moves));
    

    useEffect(() => {
        console.log("LOADING NEW PUZZLE.");
        if (props.puzzle.fen) {
            // Initialize puzzle when props.puzzleFen changes.
            setGame(new Chess(props.puzzle.fen));
            setPlayedMoves([]);
            setNotPlayedMoves(uciListToMoveStack(props.puzzle.moves));

            // Make the first solution move.
            //setTimeout(makeNextSolutionMove, 1000);
        }
    }, [props.puzzle]);

    function makeAMove(move) {
        if (notPlayedMoves.length > 0) {
            // Check if the played move matches the solution move.
            const solutionMove = notPlayedMoves[0];
            const isMoveCorrect = (move.from + move.to) === solutionMove;

            // Log the move.
            console.log("Move: " + move.from + move.to);
            console.log("Expected: " + notPlayedMoves[0]);
            console.log("Move correctness: " + isMoveCorrect);

            if (isMoveCorrect) {
                const gameCopy = new Chess(game.fen());
                const result = gameCopy.move(move);
                setGame(gameCopy);

                // Push the top solution move onto the played stack.
                setPlayedMoves([notPlayedMoves[0], ...playedMoves]);
                
                // Pop the top solution move off of the unplayed stack.
                setNotPlayedMoves(notPlayedMoves.slice(1));

                return result;
            }
            else {
                return null;
            }
        }
    }


    function onDrop(sourceSquare, targetSquare) {
        console.log("Source: " + sourceSquare);
        console.log("Target: " + targetSquare);

        const move = makeAMove({
            from: sourceSquare,
            to: targetSquare,
            promotion: "q", // always promote to a queen for example simplicity
          });
      
          // illegal move
          if (move === null) return false;

          return true;
    };

    function uciToMove(uci) {
        return {
            from: uci.substring(0, 2),
            to: uci.substring(2, 4),
            promotion: "q", // always promote to a queen for example simplicity
          };
    }

    function handleNextMoveClick() {
        if (notPlayedMoves.length === 0) {
            props.loadNextPuzzle();
        }
        else {
            makeNextSolutionMove();
        }
    }

    function uciListToMoveStack(uciList) {
        return uciList.split(" ");
    }

    function makeNextSolutionMove() {
        makeAMove(uciToMove(notPlayedMoves[0]));
    }

  return (
    <div>
      <div style={{display: "flex", flexDirection: "column"}}>
        <Chessboard boardWidth={"400"} position={game.fen()} onPieceDrop={onDrop}/>
        <div style={{display: "flex", flexDirection: "row", justifyContent: "space-around"}}>
            <button onClick={handleNextMoveClick}>Next Move</button>
            <button onClick={props.loadNextPuzzle}>Next Puzzle</button>
        </div>
      </div>
      
      <p>Played moves: {playedMoves.join(", ")}</p>
      <p>Not played moves: {notPlayedMoves.join(", ")}</p>
    </div>
  );
}