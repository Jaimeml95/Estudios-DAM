/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miscomponentes;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelImagen extends JPanel implements Serializable {

    // Atributo de la clase intermedia
    private ImagenFondo imagenFondo;
    private boolean ratonPresionado = false;
    private Point puntoPresion;
    private ArrastreListener arrastreListener;

    public JPanelImagen() {
        // Inicialización básica para evitar errores en tiempo de diseño
        this.imagenFondo = new ImagenFondo();
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e
            ) {
                if (ratonPresionado) {
                    Point posicionActual = e.getPoint();
                    if (Math.abs(puntoPresion.y - posicionActual.y) > 50) {
                        if (arrastreListener != null) {
                            arrastreListener.arrastre();
                        }
                    }
                }
                ratonPresionado = false;
            }

            @Override
            public void mousePressed(MouseEvent e
            ) {
                ratonPresionado = true;
                puntoPresion = e.getPoint();
            }
        });
    }

    public void addArrastreListener(ArrastreListener arrastreListener) {
        this.arrastreListener = arrastreListener;
    }

    public void removeArrastreListener() {
        this.arrastreListener = null;
    }

    public ImagenFondo getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(ImagenFondo imagenFondo) {
        this.imagenFondo = imagenFondo;
        repaint(); // Redibuja el panel al cambiar la propiedad
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null && imagenFondo.getRutaInicial() != null && imagenFondo.getRutaInicial().exists()) {
            Graphics2D g2d = (Graphics2D) g;
            ImageIcon icon = new ImageIcon(imagenFondo.getRutaInicial().getAbsolutePath());

            // Aplicamos opacidad
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imagenFondo.getOpacidadInicial()));
            // Dibujamos ajustado al tamaño del panel
            g2d.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
        }
    }
}
