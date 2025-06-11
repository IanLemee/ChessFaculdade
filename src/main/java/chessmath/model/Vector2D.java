package chessmath.model;

/**
 * Classe que representa um vetor 2D para cálculos de movimento
 * Demonstra conceitos de vetores e produto cartesiano
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Operações vetoriais
    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        double mag = magnitude();
        if (mag == 0) return new Vector2D(0, 0);
        return new Vector2D(x / mag, y / mag);
    }

    public double dotProduct(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public double angle() {
        return Math.atan2(y, x);
    }

    // Getters e Setters
    public double getX() { return x; }
    public double getY() { return y; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    @Override
    public String toString() {
        return String.format("Vector2D(%.2f, %.2f) |v|=%.2f", x, y, magnitude());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2D) {
            Vector2D other = (Vector2D) obj;
            return Math.abs(this.x - other.x) < 0.001 && Math.abs(this.y - other.y) < 0.001;
        }
        return false;
    }
}
