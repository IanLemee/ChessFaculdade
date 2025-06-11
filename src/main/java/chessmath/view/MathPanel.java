package chessmath.view;

import chessmath.model.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Painel que exibe informações matemáticas sobre os movimentos
 */
public class MathPanel extends JPanel {
    private JTextArea infoArea;
    private JLabel functionLabel;
    private JLabel vectorLabel;
    private JLabel pieceLabel;

    public MathPanel() {
        setPreferredSize(new Dimension(350, 600));
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Título
        JLabel title = new JLabel("Análise Matemática", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(new Color(50, 50, 150));
        add(title, BorderLayout.NORTH);

        // Painel central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(245, 245, 245));

        // Informações da peça
        pieceLabel = new JLabel("Peça: ");
        pieceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        centerPanel.add(pieceLabel);
        centerPanel.add(Box.createVerticalStrut(5));

        // Função de movimento
        functionLabel = new JLabel("Função: ");
        functionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        functionLabel.setForeground(new Color(100, 50, 150));
        centerPanel.add(functionLabel);
        centerPanel.add(Box.createVerticalStrut(5));

        // Vetor de movimento
        vectorLabel = new JLabel("Último Vetor: ");
        vectorLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        vectorLabel.setForeground(new Color(150, 50, 50));
        centerPanel.add(vectorLabel);
        centerPanel.add(Box.createVerticalStrut(15));

        // Área de informações detalhadas
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        infoArea.setBackground(Color.WHITE);
        infoArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setPreferredSize(new Dimension(330, 300));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Movimentos Válidos"));
        centerPanel.add(scrollPane);

        add(centerPanel, BorderLayout.CENTER);

        // Painel de conceitos
        JPanel conceptPanel = new JPanel();
        conceptPanel.setLayout(new GridLayout(5, 1, 2, 2));
        conceptPanel.setBackground(new Color(245, 245, 245));
        conceptPanel.setBorder(BorderFactory.createTitledBorder("Conceitos Aplicados"));

        conceptPanel.add(createConceptLabel("• Vetores e Produto Cartesiano"));
        conceptPanel.add(createConceptLabel("• Funções e Relações"));
        conceptPanel.add(createConceptLabel("• Matrizes 8x8"));
        conceptPanel.add(createConceptLabel("• Transformações Lineares"));
        conceptPanel.add(createConceptLabel("• Geometria Analítica"));

        add(conceptPanel, BorderLayout.SOUTH);

        // Inicializar com informações padrão
        clearInfo();
    }

    private JLabel createConceptLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        return label;
    }

    public void updateInfo(Piece piece, Position pos, List<Position> validMoves) {
        pieceLabel.setText("Peça: " + piece.getName() + " " + piece.getSymbol());
        functionLabel.setText("<html>Função: " + piece.getMoveFunction() + "</html>");

        StringBuilder info = new StringBuilder();
        info.append("=== ANÁLISE DA PEÇA ===\n");
        info.append("Nome: ").append(piece.getName()).append("\n");
        info.append("Cor: ").append(piece.isWhite() ? "Branca" : "Preta").append("\n");
        info.append("Posição: ").append(pos.toString()).append("\n");
        info.append("Coordenadas: (").append(pos.getCol()).append(", ").append(pos.getRow()).append(")\n\n");

        info.append("=== MOVIMENTOS POSSÍVEIS ===\n");
        info.append("Total: ").append(validMoves.size()).append(" movimentos\n\n");

        if (validMoves.size() > 0) {
            info.append("Destino    | Vetor     | Magnitude\n");
            info.append("-----------|-----------|----------\n");

            for (Position target : validMoves) {
                Vector2D vector = pos.vectorTo(target);
                info.append(String.format("%-10s | (%-2.0f,%-2.0f)  | %.2f\n", 
                    target.toString(), 
                    vector.getX(), vector.getY(), 
                    vector.magnitude()));
            }

            // Análise estatística
            info.append("\n=== ANÁLISE ESTATÍSTICA ===\n");
            double avgMagnitude = validMoves.stream()
                .mapToDouble(target -> pos.vectorTo(target).magnitude())
                .average().orElse(0.0);
            info.append(String.format("Magnitude média: %.2f\n", avgMagnitude));

            double maxMagnitude = validMoves.stream()
                .mapToDouble(target -> pos.vectorTo(target).magnitude())
                .max().orElse(0.0);
            info.append(String.format("Alcance máximo: %.2f\n", maxMagnitude));
        }

        infoArea.setText(info.toString());
        infoArea.setCaretPosition(0);
    }

    public void showMoveVector(Position from, Position to) {
        Vector2D vector = from.vectorTo(to);
        vectorLabel.setText(String.format("Último Vetor: (%.0f, %.0f) |v|=%.2f", 
            vector.getX(), vector.getY(), vector.magnitude()));
    }

    public void clearInfo() {
        pieceLabel.setText("Peça: Selecione uma peça");
        functionLabel.setText("Função: -");
        vectorLabel.setText("Último Vetor: -");
        infoArea.setText("Clique em uma peça para ver\nsua análise matemática.\n\n" +
                        "O ChessMath mostra:\n" +
                        "• Vetores de movimento\n" +
                        "• Funções matemáticas\n" +
                        "• Coordenadas cartesianas\n" +
                        "• Análise estatística\n\n" +
                        "Cada peça tem uma função\nmatemática específica que\n" +
                        "define seus movimentos!");
    }
}
