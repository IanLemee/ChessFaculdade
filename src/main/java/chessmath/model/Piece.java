package chessmath.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe abstrata que representa uma peça de xadrez
 * Cada peça tem uma função matemática de movimento
 */
public abstract class Piece {
    protected Position position;
    protected boolean isWhite;
    protected String symbol;
    protected String name;

    public Piece(Position position, boolean isWhite, String name) {
        this.position = position;
        this.isWhite = isWhite;
        this.name = name;
    }

    // Método abstrato - cada peça define sua função de movimento
    public abstract List<Position> getValidMoves(Board board);

    // Método abstrato - retorna a função matemática do movimento
    public abstract String getMoveFunction();

    // Verifica se um movimento é válido
    protected boolean isValidMove(Position target, Board board) {
        if (!target.isValid()) return false;

        Piece targetPiece = board.getPieceAt(target);
        if (targetPiece != null && targetPiece.isWhite == this.isWhite) {
            return false;
        }

        return true;
    }

    // Calcula o vetor de movimento
    public Vector2D getMoveVector(Position target) {
        return position.vectorTo(target);
    }

    // Verifica se o caminho está livre (para peças de longo alcance)
    protected boolean isPathClear(Position target, Board board) {
        Vector2D direction = position.vectorTo(target);
        double steps = Math.max(Math.abs(direction.getX()), Math.abs(direction.getY()));

        if (steps <= 1) return true;

        Vector2D stepVector = new Vector2D(
            direction.getX() / steps,
            direction.getY() / steps
        );

        for (int i = 1; i < steps; i++) {
            Position checkPos = new Position(
                position.getRow() + (int)(stepVector.getY() * i),
                position.getCol() + (int)(stepVector.getX() * i)
            );

            if (board.getPieceAt(checkPos) != null) {
                return false;
            }
        }

        return true;
    }

    // Getters e Setters
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }
    public boolean isWhite() { return isWhite; }
    public String getSymbol() { return symbol; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " em " + position.toString();
    }
}
