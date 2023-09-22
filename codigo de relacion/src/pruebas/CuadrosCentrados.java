package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CuadrosCentrados extends JFrame {

    public CuadrosCentrados() {
        setTitle("Cuadros Centrados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Tamaño de la ventana

        // Panel que contendrá los cuadros
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int numCuadros = 10;
                int cuadroSize = 50; // Tamaño de cada cuadro en píxeles
                int separacion = 20; // Separación en píxeles entre los cuadros
                int margenDerecho = 20;
                int margenInferior = 20;

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Calcula el espacio disponible para los cuadros
                int availableWidth = panelWidth - margenDerecho - (numCuadros - 1) * separacion;
                int availableHeight = panelHeight - margenInferior - cuadroSize;

                // Calcula el tamaño máximo de los cuadros
                int maxCuadroSize = Math.min(availableWidth / numCuadros, availableHeight);

                Graphics2D g2d = (Graphics2D) g;

                for (int i = 0; i < numCuadros; i++) {
                    int x = margenDerecho + i * (maxCuadroSize + separacion);
                    int y = panelHeight - margenInferior - maxCuadroSize;

                    // Dibuja el cuadro
                    Rectangle2D cuadro = new Rectangle2D.Double(x, y, maxCuadroSize, maxCuadroSize);
                    g2d.setColor(Color.BLUE); // Cambia el color del cuadro
                    g2d.fill(cuadro);
                }
            }
        };

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuadrosCentrados frame = new CuadrosCentrados();
            frame.setVisible(true);
        });
    }
}

