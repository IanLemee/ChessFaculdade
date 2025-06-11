package chessmath.model.pieces;

import chessmath.model.*;
import java.util.List;
import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(Position position, boolean isWhite) {
        super(position, isWhite, "Cavalo");
        this.symbol = isWhite ? "♘" : "♞";
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();

        int[][] knightMoves = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] move : knightMoves) {
            Position newPos = new Position(
                position.getRow() + move[0],
                position.getCol() + move[1]
            );

            if (isValidMove(newPos, board)) {
                moves.add(newPos);
            }
        }

        return moves;
    }

    @Override
    public String getMoveFunction() {
        return "f(x,y) = {(x±2,y±1), (x±1,y±2)}, |v| = √5";
    }
}