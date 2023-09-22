package pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;



public class CuadrosConLineas extends JFrame {
    private List<Relacion> relaciones = new ArrayList<>();
    private Rectangle2D.Double primerCuadroSeleccionado;

    public CuadrosConLineas() {
        setTitle("Cuadros con Líneas");
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

                        // Dibuja todas las relaciones almacenadas
                        g2d.setColor(Color.RED); // Cambia el color de la línea si lo deseas
                        for (Relacion relacion : relaciones) {
                            g2d.draw(relacion.getLinea());
                        }
                    }
                }
            }
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point punto = e.getPoint();
                Rectangle2D.Double cuadroClic = buscarCuadroClic(punto);

                if (cuadroClic != null) {
                    if (primerCuadroSeleccionado == null) {
                        primerCuadroSeleccionado = cuadroClic; // Establecer el primer cuadro seleccionado
                    } else {
                        // Evitar la relación de un cuadro consigo mismo
                        if (!primerCuadroSeleccionado.equals(cuadroClic)) {
                            relaciones.add(new Relacion(primerCuadroSeleccionado, cuadroClic)); // Establecer la relación
                            primerCuadroSeleccionado = null; // Reiniciar el primer cuadro seleccionado
                            repaint(); // Vuelve a dibujar para mostrar las líneas de relación
                        }
                    }
                }
            }
        });

        add(panel);
    }

    // Método para buscar el cuadro clicado
    private Rectangle2D.Double buscarCuadroClic(Point punto) {
        for (int i = 0; i < relaciones.size(); i++) {
            Rectangle2D.Double cuadro = obtenerCuadro(i);
            if (cuadro.contains(punto)) {
                return cuadro;
            }
        }
        return null;
    }

    // Método para obtener la posición de un cuadro según su índice en la lista de relaciones
    private Rectangle2D.Double obtenerCuadro(int indice) {
        int filas = 2; // Número de filas
        int columnas = 5; // Número de columnas
        int cuadroWidth = 150; // Ancho de cada cuadro
        int cuadroHeight = 150; // Alto de cada cuadro
        int separacionX = 80; // Separación horizontal entre cuadros
        int separacionY = 80; // Separación vertical entre cuadros

        int cuadroX = indice % columnas;
        int cuadroY = indice / columnas;

        int x = cuadroX * (cuadroWidth + separacionX);
        int y = cuadroY * (cuadroHeight + separacionY);

        x += (getWidth() - (columnas * (cuadroWidth + separacionX))) / 2;
        y += (getHeight() - (filas * (cuadroHeight + separacionY))) / 5;

        return new Rectangle2D.Double(x, y, cuadroWidth, cuadroHeight);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CuadrosConLineas cuadrosConLineas = new CuadrosConLineas();
            cuadrosConLineas.setVisible(true);
        });
    }
}

class Relacion {
    private Line2D linea;

    public Relacion(Rectangle2D.Double cuadro1, Rectangle2D.Double cuadro2) {
        Point2D centro1 = new Point2D.Double(cuadro1.getCenterX(), cuadro1.getCenterY());
        Point2D centro2 = new Point2D.Double(cuadro2.getCenterX(), cuadro2.getCenterY());
        this.linea = new Line2D.Double(centro1, centro2);
    }

    public Line2D getLinea() {
        return linea;
    }
}


