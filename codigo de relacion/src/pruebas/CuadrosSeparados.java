package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class CuadrosSeparados extends JFrame {

    public CuadrosSeparados() {
        setTitle("Cuadros Separados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setResizable(false);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int filas = 2; // Número de filas
                int columnas = 5; // Número de columnas
                int cuadroWidth = 150; // Ancho de cada cuadro
                int cuadroHeight = 150; // Alto de cada cuadro
                int separacionX = 80; // Separación horizontal entre cuadros
                int separacionY = 80; // Separación vertical entre cuadros

                // Calcula el ancho y alto total de la cuadrícula de cuadros
                int gridWidth = columnas * cuadroWidth + (columnas - 1) * separacionX;
                int gridHeight = filas * cuadroHeight + (filas - 1) * separacionY;

                // Calcula el espacio en blanco necesario en la ventana para centrar la cuadrícula
                int espacioX = (getWidth() - gridWidth) / 2;
                int espacioY = (getHeight() - gridHeight) / 5;

                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        int x = espacioX + j * (cuadroWidth + separacionX);
                        int y = espacioY + i * (cuadroHeight + separacionY);

                        Rectangle2D.Double cuadro = new Rectangle2D.Double(x, y, cuadroWidth, cuadroHeight);
                        g2d.setColor(Color.BLUE); // Cambia el color del cuadro si lo deseas
                        g2d.fill(cuadro);
                    }
                }
            }
        };

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuadrosSeparados cuadrosSeparados = new CuadrosSeparados();
            cuadrosSeparados.setVisible(true);
        });
    }
}
