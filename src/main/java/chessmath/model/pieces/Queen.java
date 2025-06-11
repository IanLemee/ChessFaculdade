package chessmath.model.pieces;

import chessmath.model.*;
import java.util.List;
import java.util.ArrayList;

public class Queen extends Piece {

    public Queen(Position position, boolean isWhite) {
        super(position, isWhite, "Rainha");
        this.symbol = isWhite ? "♕" : "♛";
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] dir : directions) {
            for (int i = 1; i < 8; i++) {
                Position newPos = new Position(
                    position.getRow() + dir[0] * i,
                    position.getCol() + dir[1] * i
                );

                if (!newPos.isValid()) break;

                Piece piece = board.getPieceAt(newPos);
                if (piece == null) {
                    moves.add(newPos);
                } else {
                    if (piece.isWhite() != this.isWhite) {
                        moves.add(newPos);
                    }
                    break;
                }
            }
        }

        return moves;
    }

    @Override
    public String getMoveFunction() {
        return "f(x,y) = Torre(x,y) ∪ Bispo(x,y)";
    }
}