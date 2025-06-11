package chessmath.controller;

import chessmath.model.*;

/**
 * Controlador principal do jogo
 * Gerencia a lógica de negócio e estado do jogo
 */
public class GameController {
    private Board board;
    private boolean whiteTurn;
    private Position selectedPosition;
    private int moveCount;

    public GameController() {
        board = new Board();
        whiteTurn = true;
        selectedPosition = null;
        moveCount = 0;
    }

    public boolean makeMove(Position from, Position to) {
        Piece piece = board.getPieceAt(from);

        if (piece != null && piece.isWhite() == whiteTurn) {
            if (piece.getValidMoves(board).contains(to)) {
                board.movePiece(from, to);
                whiteTurn = !whiteTurn;
                moveCount++;
                return true;
            }
        }

        return false;
    }

    public boolean isValidSelection(Position pos) {
        Piece piece = board.getPieceAt(pos);
        return piece != null && piece.isWhite() == whiteTurn;
    }

    public void resetGame() {
        board = new Board();
        whiteTurn = true;
        selectedPosition = null;
        moveCount = 0;
    }

    // Getters
    public Board getBoard() { return board; }
    public boolean isWhiteTurn() { return whiteTurn; }
    public Position getSelectedPosition() { return selectedPosition; }
    public void setSelectedPosition(Position pos) { this.selectedPosition = pos; }
    public int getMoveCount() { return moveCount; }
    public String getCurrentPlayer() { return whiteTurn ? "Brancas" : "Pretas"; }
}
