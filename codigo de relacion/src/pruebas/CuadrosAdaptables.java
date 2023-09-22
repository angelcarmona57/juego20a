package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;

public class CuadrosAdaptables extends JFrame {

    public CuadrosAdaptables() {
        setTitle("Cuadros Fijos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int separacionX = 20; // Separación horizontal de 2 cm entre cuadros
                int separacionY = 20; // Separación vertical de 2 cm entre cuadros
                int cuadroWidth = 100; // Ancho de cada cuadro
                int cuadroHeight = 100; // Alto de cada cuadro

                for (int i = 0; i < 10; i++) {
                    int fila = i / 5;
                    int columna = i % 5;

                    int x = separacionX + columna * (cuadroWidth + separacionX);
                    int y = getHeight() - cuadroHeight - separacionY + fila * (cuadroHeight + separacionY);

                    Rectangle2D.Double cuadro = new Rectangle2D.Double(x, y, cuadroWidth, cuadroHeight);
                    g2d.setColor(Color.BLUE); // Cambia el color del cuadro si lo deseas
                    g2d.fill(cuadro);
                }
            }
        };

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuadrosAdaptables cuadrosFijos = new CuadrosAdaptables();
            cuadrosFijos.setVisible(true);
        });
    }
}
