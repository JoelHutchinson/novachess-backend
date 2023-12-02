const React = require('react');

import { Chessboard } from "react-chessboard";

export default function ChessboardWrapper() {
  return (
    <div>
      <Chessboard id="BasicBoard" boardWidth={"400"}/>
    </div>
  );
}