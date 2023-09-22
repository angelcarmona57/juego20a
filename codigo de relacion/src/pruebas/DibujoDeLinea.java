package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class DibujoDeLinea extends JFrame {

    private Point primerPunto = null;
    private Point segundoPunto = null;
    private JPanel lienzo;

    public DibujoDeLinea() {
        setTitle("Cuadros, Puntos y Línea en Movimiento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);

        lienzo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                dibujarCuadros(g);
                dibujarPuntos(g);
                dibujarLinea(g);
            }
        };

        lienzo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manejarClic(e);
            }
        });

        lienzo.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                manejarMovimiento(e);
            }
        });

        getContentPane().add(lienzo);
    }

    private void dibujarCuadros(Graphics g) {
        int lado = 100;
        g.setColor(Color.BLUE);
        g.fillRect(50, 50, lado, lado);
        g.fillRect(50, 50 + lado + 50, lado, lado);
    }

    private void dibujarPuntos(Graphics g) {
        if (primerPunto != null) {
            g.setColor(Color.GREEN);
            g.fillOval(primerPunto.x - 5, primerPunto.y - 5, 10, 10);
        }
        if (segundoPunto != null) {
            g.setColor(Color.GREEN);
            g.fillOval(segundoPunto.x - 5, segundoPunto.y - 5, 10, 10);
        }
    }

    private void dibujarLinea(Graphics g) {
        if (primerPunto != null && segundoPunto != null) {
            g.setColor(Color.RED);
            g.drawLine(primerPunto.x, primerPunto.y, segundoPunto.x, segundoPunto.y);
        }
    }

    private void manejarMovimiento(MouseEvent e) {
        if (primerPunto != null && segundoPunto == null) {
            lienzo.repaint();
        }
    }

    private void manejarClic(MouseEvent e) {
        if (primerPunto == null) {
            primerPunto = e.getPoint();
        } else if (segundoPunto == null) {
            segundoPunto = e.getPoint();
            lienzo.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DibujoDeLinea ventana = new DibujoDeLinea();
            ventana.setVisible(true);
        });
    }
}
