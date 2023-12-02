const React = require('react');
import { useState, useEffect } from "react";
import { Chess } from "chess.js";
import { Chessboard } from "react-chessboard";

export default function PuzzleBoard(props) {
    const [game, setGame] = useState(new Chess(props.puzzleFen));
    const [moveIndex, setMoveIndex] = useState(0);

    useEffect(() => {
        if (props.puzzleFen) {
            // Initialize game when props.puzzleFen changes
            setGame(new Chess(props.puzzleFen));
        }
    }, [props.puzzleFen]);

    function makeAMove(move) {
        const gameCopy = new Chess(game.fen());
        const result = gameCopy.move(move);
        setGame(gameCopy);

        console.log("Move: " + move.from + move.to);
        console.log("Expected: " + props.solutionSanMoves.split(" ")[moveIndex]);
        const moveCorrect = (move.from + move.to) === props.solutionSanMoves.split(" ")[moveIndex];
        console.log("Move correctness: " + moveCorrect);

        if (moveCorrect) {
            setMoveIndex(moveIndex + 1);
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

  return (
    <div>
      <Chessboard boardWidth={"400"} position={game.fen()} onPieceDrop={onDrop}/>
    </div>
  );
}