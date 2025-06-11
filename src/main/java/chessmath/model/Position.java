package chessmath.model;

/**
 * Representa uma posição no tabuleiro usando coordenadas cartesianas
 */
public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Vector2D toVector() {
        return new Vector2D(col, row);
    }

    public boolean isValid() {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public Position add(Vector2D vector) {
        return new Position(
            row + (int)vector.getY(),
            col + (int)vector.getX()
        );
    }

    // Calcula vetor entre duas posições
    public Vector2D vectorTo(Position other) {
        return new Vector2D(
            other.col - this.col,
            other.row - this.row
        );
    }

    // Calcula distância euclidiana
    public double distanceTo(Position other) {
        return vectorTo(other).magnitude();
    }

    // Getters
    public int getRow() { return row; }
    public int getCol() { return col; }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return this.row == other.row && this.col == other.col;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return row * 8 + col;
    }

    @Override
    public String toString() {
        char colChar = (char)('A' + col);
        int rowNum = 8 - row;
        return "" + colChar + rowNum;
    }
}
