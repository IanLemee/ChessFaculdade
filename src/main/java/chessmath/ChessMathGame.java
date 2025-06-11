package chessmath;

import chessmath.view.GamePanel;
import chessmath.controller.GameController;
import javax.swing.*;
import java.awt.*;

/**
 * ChessMath - Jogo educacional que combina xadrez com conceitos de Estruturas Matemáticas
 * 
 * @author Equipe ChessMath
 * @version 1.0
 */
public class ChessMathGame extends JFrame {
    private GameController controller;
    private GamePanel gamePanel;

    public ChessMathGame() {
        setTitle("ChessMath - Xadrez Matemático");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializar componentes
        controller = new GameController();
        gamePanel = new GamePanel(controller);

        // Adicionar painéis
        add(gamePanel, BorderLayout.CENTER);

        // Configurar janela
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // Adicionar ícone e configurações extras
        setIconImage(createIcon());
    }

    private Image createIcon() {
        // Criar um ícone simples para o jogo
        return Toolkit.getDefaultToolkit().createImage(new byte[0]);
    }

    public static void main(String[] args) {
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ChessMathGame game = new ChessMathGame();
            game.setVisible(true);
        });
    }
}
