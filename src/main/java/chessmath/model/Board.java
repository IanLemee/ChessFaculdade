package chessmath.model;

import chessmath.model.pieces.*;

/**
 * Representa o tabuleiro como uma matriz 8x8
 * Demonstra conceitos de matrizes e sistemas de coordenadas
 */
public class Board {
    private Piece[][] matrix;
    private static final int SIZE = 8;

    public Board() {
        matrix = new Piece[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        // Limpar tabuleiro
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i][j] = null;
            }
        }

        // Peças brancas
        for (int col = 0; col < SIZE; col++) {
            matrix[6][col] = new Pawn(new Position(6, col), true);
        }
        matrix[7][0] = new Rook(new Position(7, 0), true);
        matrix[7][7] = new Rook(new Position(7, 7), true);
        matrix[7][1] = new Knight(new Position(7, 1), true);
        matrix[7][6] = new Knight(new Position(7, 6), true);
        matrix[7][2] = new Bishop(new Position(7, 2), true);
        matrix[7][5] = new Bishop(new Position(7, 5), true);
        matrix[7][3] = new Queen(new Position(7, 3), true);
        matrix[7][4] = new King(new Position(7, 4), true);

        // Peças pretas
        for (int col = 0; col < SIZE; col++) {
            matrix[1][col] = new Pawn(new Position(1, col), false);
        }
        matrix[0][0] = new Rook(new Position(0, 0), false);
        matrix[0][7] = new Rook(new Position(0, 7), false);
        matrix[0][1] = new Knight(new Position(0, 1), false);
        matrix[0][6] = new Knight(new Position(0, 6), false);
        matrix[0][2] = new Bishop(new Position(0, 2), false);
        matrix[0][5] = new Bishop(new Position(0, 5), false);
        matrix[0][3] = new Queen(new Position(0, 3), false);
        matrix[0][4] = new King(new Position(0, 4), false);
    }

    public Piece getPieceAt(Position pos) {
        if (!pos.isValid()) return null;
        return matrix[pos.getRow()][pos.getCol()];
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPieceAt(from);
        if (piece != null) {
            matrix[to.getRow()][to.getCol()] = piece;
            matrix[from.getRow()][from.getCol()] = null;
            piece.setPosition(to);
        }
    }

    public void setPiece(Position pos, Piece piece) {
        if (pos.isValid()) {
            matrix[pos.getRow()][pos.getCol()] = piece;
            if (piece != null) {
                piece.setPosition(pos);
            }
        }
    }

    // Representação matricial do tabuleiro
    public int[][] toIntMatrix() {
        int[][] intMatrix = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (matrix[i][j] != null) {
                    intMatrix[i][j] = matrix[i][j].isWhite() ? 1 : -1;
                } else {
                    intMatrix[i][j] = 0;
                }
            }
        }
        return intMatrix;
    }

    // Clonar tabuleiro para análise
    public Board clone() {
        Board newBoard = new Board();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newBoard.matrix[i][j] = this.matrix[i][j];
            }
        }
        return newBoard;
    }

    public Piece[][] getMatrix() { return matrix; }
    public static int getSize() { return SIZE; }
}
