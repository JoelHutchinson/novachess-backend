const React = require('react');
import { useState, useEffect } from "react";
import { Chess } from "chess.js";
import { Chessboard } from "react-chessboard";

export default function PuzzleBoard(props) {
    const [game, setGame] = useState(new Chess(props.puzzleFen));
    const [moveIndex, setMoveIndex] = useState(0);

    useEffect(() => {
        if (props.puzzleFen) {
            // Initialize puzzle when props.puzzleFen changes.
            setGame(new Chess(props.puzzleFen));
        }
    }, [props.puzzleFen]);

    function makeAMove(move) {
        const gameCopy = new Chess(game.fen());
        const result = gameCopy.move(move);
        setGame(gameCopy);

        console.log("Move: " + move.from + move.to);
        console.log("Expected: " + props.solutionUciMoves.split(" ")[moveIndex]);
        const moveCorrect = (move.from + move.to) === props.solutionUciMoves.split(" ")[moveIndex];
        console.log("Move correctness: " + moveCorrect);

        if (moveCorrect) {
            setMoveIndex(prevMoveIndex => prevMoveIndex + 1);
            return result;
        }
        return null;
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

  return (
    <div>
      <Chessboard boardWidth={"400"} position={game.fen()} onPieceDrop={onDrop}/>
      <p>Solution: {props.solutionUciMoves.split(" ")}</p>
      <button onClick={() => makeAMove(uciToMove(props.solutionUciMoves.split(" ")[moveIndex]))}>Next Move</button>
    </div>
  );
}