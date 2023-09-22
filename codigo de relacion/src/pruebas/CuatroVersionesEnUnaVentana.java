package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;



public class CuatroVersionesEnUnaVentana extends JFrame {

    private List<Point> primerPuntos = new ArrayList<>();
    private List<Point> segundoPuntos = new ArrayList<>();
    private List<List<LineaEnMovimiento>> lineasMovimiento = new ArrayList<>();
    private List<JPanel> versionPanels = new ArrayList<>();

    public CuatroVersionesEnUnaVentana() {
        setTitle("Cuatro Versiones en Una Ventana");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new GridLayout(1, 4)); // 1 fila, 4 columnas

        for (int i = 1; i <= 4; i++) {
            primerPuntos.add(null);
            segundoPuntos.add(null);
            lineasMovimiento.add(new ArrayList<>());

            JPanel versionPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    dibujarCuadros(g, this);
                    dibujarPuntos(g);
                    dibujarLineas(g);
                }
            };
            versionPanels.add(versionPanel);

            versionPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int index = versionPanels.indexOf(versionPanel);
                    manejarClic(e, index);
                    versionPanel.repaint();
                }
            });

            versionPanel.addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    int index = versionPanels.indexOf(versionPanel);
                    manejarMovimiento(e, index);
                    versionPanel.repaint();
                }
            });

            add(versionPanel);
        }
    }

    private void dibujarCuadros(Graphics g, JPanel panel) {
        int lado = 100;
        g.setColor(Color.BLUE);
        g.fillRect(20, 50, lado, lado);
        g.fillRect(20, 50 + lado + 20, lado, lado);
    }

    private void dibujarPuntos(Graphics g) {
        for (int i = 0; i < primerPuntos.size(); i++) {
            Point primerPunto = primerPuntos.get(i);
            Point segundoPunto = segundoPuntos.get(i);
            if (primerPunto != null) {
                g.setColor(Color.GREEN);
                g.fillOval(primerPunto.x - 5, primerPunto.y - 5, 10, 10);
            }
            if (segundoPunto != null) {
                g.setColor(Color.GREEN);
                g.fillOval(segundoPunto.x - 5, segundoPunto.y - 5, 10, 10);
            }
        }
    }

    private void dibujarLineas(Graphics g) {
        for (int i = 0; i < lineasMovimiento.size(); i++) {
            for (LineaEnMovimiento lineaMovimiento : lineasMovimiento.get(i)) {
                if (lineaMovimiento.primerPunto != null && lineaMovimiento.segundoPunto != null) {
                    g.setColor(Color.RED);
                    g.drawLine(lineaMovimiento.primerPunto.x, lineaMovimiento.primerPunto.y,
                            lineaMovimiento.segundoPunto.x, lineaMovimiento.segundoPunto.y);
                }
            }
        }
    }

    private void manejarMovimiento(MouseEvent e, int index) {
        if (index >= 0 && index < primerPuntos.size()) {
            if (primerPuntos.get(index) != null && segundoPuntos.get(index) == null) {
                List<LineaEnMovimiento> lineas = lineasMovimiento.get(index);
                Point puntoActual = e.getPoint();
                lineas.add(new LineaEnMovimiento(primerPuntos.get(index), puntoActual));
            }
        }
    }

    private void manejarClic(MouseEvent e, int index) {
        if (index >= 0 && index < primerPuntos.size()) {
            if (primerPuntos.get(index) == null) {
                Point puntoActual = e.getPoint();
                primerPuntos.set(index, puntoActual);
            } else if (segundoPuntos.get(index) == null) {
                Point puntoActual = e.getPoint();
                segundoPuntos.set(index, puntoActual);
            }
        }
    }

    private class LineaEnMovimiento {
        private final Point primerPunto;
        private final Point segundoPunto;

        public LineaEnMovimiento(Point primerPunto, Point segundoPunto) {
            this.primerPunto = primerPunto;
            this.segundoPunto = segundoPunto;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuatroVersionesEnUnaVentana ventana = new CuatroVersionesEnUnaVentana();
            ventana.setVisible(true);
        });
    }
}



