const React = require('react');
import { useState } from "react";
import { Chess } from "chess.js";
import { Chessboard } from "react-chessboard";

export default function PuzzleBoard() {
    const [game, setGame] = useState(new Chess());

    function makeAMove(move) {
        const gameCopy = new Chess(game.fen());
        const result = gameCopy.move(move);
        setGame(gameCopy);
        return result; // null if the move was illegal, the move object if the move was legal
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