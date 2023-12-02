const React = require('react');

import { Chessboard } from "react-chessboard";

export default function PuzzleBoard() {
    function onDrop(sourceSquare, targetSquare) {
        console.log("Source: " + sourceSquare);
        console.log("Target: " + targetSquare);
        return true;
    };

  return (
    <div>
      <Chessboard id="BasicBoard" boardWidth={"400"} onPieceDrop={onDrop}/>
    </div>
  );
}