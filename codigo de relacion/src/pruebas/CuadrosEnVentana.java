package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CuadrosEnVentana extends JFrame {

    public CuadrosEnVentana() {
        setTitle("Cuadros en Ventana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        
        // Crear un JPanel personalizado donde dibujaremos los cuadros
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int numCuadros = 10;
                int espacioEntreCuadros = 20; // 2 cm en píxeles (depende de la resolución)
                int anchoCuadro = (getWidth() - (numCuadros - 1) * espacioEntreCuadros) / numCuadros;
                int altoCuadro = getHeight() / 2; // Dividir la altura en dos secciones

                for (int i = 0; i < numCuadros; i++) {
                    int x = i * (anchoCuadro + espacioEntreCuadros);
                    int y = getHeight() / 2; // Posición vertical para la segunda fila de cuadros
                    g2d.setColor(Color.BLUE);
                    Rectangle2D cuadro = new Rectangle2D.Double(x, y, anchoCuadro, altoCuadro);
                    g2d.fill(cuadro);
                }
            }
        };

        getContentPane().add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuadrosEnVentana ventana = new CuadrosEnVentana();
            ventana.setVisible(true);
        });
    }
}

