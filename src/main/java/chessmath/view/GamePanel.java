package chessmath.view;

import chessmath.controller.GameController;
import chessmath.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Painel principal do jogo com visualização do tabuleiro e informações matemáticas
 */
public class GamePanel extends JPanel {
    private GameController controller;
    private BoardRenderer boardRenderer;
    private MathPanel mathPanel;
    private Position selectedPosition;
    private List<Position> validMoves;
    private JLabel statusLabel;

    public GamePanel(GameController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        // Painel superior com status
        JPanel topPanel = new JPanel(new FlowLayout());
        statusLabel = new JLabel("Turno: " + controller.getCurrentPlayer());
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JButton resetButton = new JButton("Novo Jogo");
        resetButton.addActionListener(e -> resetGame());

        topPanel.add(statusLabel);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(resetButton);

        // Painel do tabuleiro
        boardRenderer = new BoardRenderer();
        boardRenderer.addMouseListener(new BoardClickListener());

        // Painel de informações matemáticas
        mathPanel = new MathPanel();

        // Adicionar componentes
        add(topPanel, BorderLayout.NORTH);
        add(boardRenderer, BorderLayout.CENTER);
        add(mathPanel, BorderLayout.EAST);

        updateStatus();
    }

    private void resetGame() {
        controller.resetGame();
        selectedPosition = null;
        validMoves = null;
        updateStatus();
        mathPanel.clearInfo();
        repaint();
    }

    private void updateStatus() {
        statusLabel.setText("Turno: " + controller.getCurrentPlayer() + 
                           " | Jogadas: " + controller.getMoveCount());
    }

    private class BoardRenderer extends JPanel {
        private static final int SQUARE_SIZE = 70;
        private static final int BOARD_SIZE = 8;

        public BoardRenderer() {
            setPreferredSize(new Dimension(SQUARE_SIZE * BOARD_SIZE, SQUARE_SIZE * BOARD_SIZE));
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Desenhar tabuleiro
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    Color squareColor = (row + col) % 2 == 0 ? 
                        new Color(240, 217, 181) : new Color(181, 136, 99);
                    g2d.setColor(squareColor);
                    g2d.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);

                    // Desenhar coordenadas
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font("Arial", Font.PLAIN, 10));
                    if (col == 0) {
                        g2d.drawString(String.valueOf(8 - row), 2, row * SQUARE_SIZE + 12);
                    }
                    if (row == 7) {
                        g2d.drawString(String.valueOf((char)('A' + col)), 
                                     col * SQUARE_SIZE + SQUARE_SIZE - 12, 
                                     BOARD_SIZE * SQUARE_SIZE - 2);
                    }
                }
            }

            // Destacar posição selecionada
            if (selectedPosition != null) {
                g2d.setColor(new Color(255, 255, 0, 150));
                g2d.fillRect(selectedPosition.getCol() * SQUARE_SIZE, 
                            selectedPosition.getRow() * SQUARE_SIZE, 
                            SQUARE_SIZE, SQUARE_SIZE);

                // Borda da seleção
                g2d.setColor(Color.YELLOW);
                g2d.setStroke(new BasicStroke(3));
                g2d.drawRect(selectedPosition.getCol() * SQUARE_SIZE, 
                            selectedPosition.getRow() * SQUARE_SIZE, 
                            SQUARE_SIZE, SQUARE_SIZE);
            }

            // Mostrar movimentos válidos
            if (validMoves != null) {
                for (Position pos : validMoves) {
                    Piece targetPiece = controller.getBoard().getPieceAt(pos);
                    if (targetPiece != null) {
                        // Captura - círculo vermelho
                        g2d.setColor(new Color(255, 0, 0, 120));
                        g2d.fillOval(pos.getCol() * SQUARE_SIZE + 5, 
                                    pos.getRow() * SQUARE_SIZE + 5, 
                                    SQUARE_SIZE - 10, SQUARE_SIZE - 10);
                    } else {
                        // Movimento normal - círculo verde
                        g2d.setColor(new Color(0, 255, 0, 120));
                        g2d.fillOval(pos.getCol() * SQUARE_SIZE + 25, 
                                    pos.getRow() * SQUARE_SIZE + 25, 
                                    20, 20);
                    }
                }
            }

            // Desenhar peças
            Board board = controller.getBoard();
            g2d.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    Piece piece = board.getPieceAt(new Position(row, col));
                    if (piece != null) {
                        // Sombra da peça
                        g2d.setColor(Color.GRAY);
                        g2d.drawString(piece.getSymbol(), 
                                     col * SQUARE_SIZE + 12, 
                                     row * SQUARE_SIZE + 52);

                        // Peça
                        g2d.setColor(piece.isWhite() ? Color.WHITE : Color.BLACK);
                        g2d.drawString(piece.getSymbol(), 
                                     col * SQUARE_SIZE + 10, 
                                     row * SQUARE_SIZE + 50);
                    }
                }
            }

            // Desenhar vetores de movimento
            if (selectedPosition != null && validMoves != null && !validMoves.isEmpty()) {
                g2d.setColor(new Color(0, 0, 255, 180));
                g2d.setStroke(new BasicStroke(2));
                int startX = selectedPosition.getCol() * SQUARE_SIZE + SQUARE_SIZE/2;
                int startY = selectedPosition.getRow() * SQUARE_SIZE + SQUARE_SIZE/2;

                for (Position target : validMoves) {
                    if (Math.abs(target.getRow() - selectedPosition.getRow()) <= 3 && 
                        Math.abs(target.getCol() - selectedPosition.getCol()) <= 3) {
                        int endX = target.getCol() * SQUARE_SIZE + SQUARE_SIZE/2;
                        int endY = target.getRow() * SQUARE_SIZE + SQUARE_SIZE/2;
                        drawArrow(g2d, startX, startY, endX, endY);
                    }
                }
            }
        }

        private void drawArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
            g2d.drawLine(x1, y1, x2, y2);

            double angle = Math.atan2(y2 - y1, x2 - x1);
            int arrowLength = 8;
            int x3 = (int) (x2 - arrowLength * Math.cos(angle - Math.PI/6));
            int y3 = (int) (y2 - arrowLength * Math.sin(angle - Math.PI/6));
            int x4 = (int) (x2 - arrowLength * Math.cos(angle + Math.PI/6));
            int y4 = (int) (y2 - arrowLength * Math.sin(angle + Math.PI/6));

            g2d.drawLine(x2, y2, x3, y3);
            g2d.drawLine(x2, y2, x4, y4);
        }
    }

    private class BoardClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int col = e.getX() / BoardRenderer.SQUARE_SIZE;
            int row = e.getY() / BoardRenderer.SQUARE_SIZE;
            Position clickedPos = new Position(row, col);

            if (!clickedPos.isValid()) return;

            if (selectedPosition == null) {
                // Selecionar peça
                if (controller.isValidSelection(clickedPos)) {
                    selectedPosition = clickedPos;
                    Piece piece = controller.getBoard().getPieceAt(clickedPos);
                    validMoves = piece.getValidMoves(controller.getBoard());
                    mathPanel.updateInfo(piece, clickedPos, validMoves);
                }
            } else {
                // Tentar mover
                if (validMoves != null && validMoves.contains(clickedPos)) {
                    if (controller.makeMove(selectedPosition, clickedPos)) {
                        mathPanel.showMoveVector(selectedPosition, clickedPos);
                        updateStatus();
                    }
                }
                selectedPosition = null;
                validMoves = null;
            }

            repaint();
        }
    }
}
