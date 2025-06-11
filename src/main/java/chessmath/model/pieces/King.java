package chessmath.model.pieces;

import chessmath.model.*;
import java.util.List;
import java.util.ArrayList;

public class King extends Piece {

    public King(Position position, boolean isWhite) {
        super(position, isWhite, "Rei");
        this.symbol = isWhite ? "♔" : "♚";
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
            Position newPos = new Position(
                position.getRow() + dir[0],
                position.getCol() + dir[1]
            );

            if (isValidMove(newPos, board)) {
                moves.add(newPos);
            }
        }

        return moves;
    }

    @Override
    public String getMoveFunction() {
        return "f(x,y) = (x+δx, y+δy), |δx|≤1, |δy|≤1";
    }
}