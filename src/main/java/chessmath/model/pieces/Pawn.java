package chessmath.model.pieces;

import chessmath.model.*;
import java.util.List;
import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Position position, boolean isWhite) {
        super(position, isWhite, "Peão");
        this.symbol = isWhite ? "♙" : "♟";
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int direction = isWhite ? -1 : 1;

        Position oneStep = new Position(
            position.getRow() + direction,
            position.getCol()
        );

        if (oneStep.isValid() && board.getPieceAt(oneStep) == null) {
            moves.add(oneStep);

            boolean isStartingPosition = (isWhite && position.getRow() == 6) || 
                                       (!isWhite && position.getRow() == 1);

            if (isStartingPosition) {
                Position twoSteps = new Position(
                    position.getRow() + 2 * direction,
                    position.getCol()
                );

                if (board.getPieceAt(twoSteps) == null) {
                    moves.add(twoSteps);
                }
            }
        }

        int[] captureCols = {position.getCol() - 1, position.getCol() + 1};
        for (int col : captureCols) {
            Position capturePos = new Position(
                position.getRow() + direction,
                col
            );

            if (capturePos.isValid()) {
                Piece target = board.getPieceAt(capturePos);
                if (target != null && target.isWhite() != this.isWhite) {
                    moves.add(capturePos);
                }
            }
        }

        return moves;
    }

    @Override
    public String getMoveFunction() {
        return "f(x,y) = {(x,y±1) se livre, (x±1,y±1) se captura}";
    }
}