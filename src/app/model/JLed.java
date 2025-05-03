package app.model;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class JLed extends JLabel {

    private Color coloreCerchio;
    private String numero;
    private boolean mostraCerchioGiallo = false;

    public JLed(Color coloreCerchio, String numero) {
        this.coloreCerchio = coloreCerchio;
        this.numero = numero;
        setPreferredSize(new Dimension(20, 20));
        setMinimumSize(new Dimension(20, 20));
        setMaximumSize(new Dimension(20, 20));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int diametro = 18; // lasciamo un pixel di margine
        int x = (getWidth() - diametro) / 2;
        int y = (getHeight() - diametro) / 2;

        // Cerchio principale
        g2d.setColor(coloreCerchio);
        g2d.fillOval(x, y, diametro, diametro);

        // Cerchio giallo (più grande)
        if (mostraCerchioGiallo) {
            g2d.setColor(new Color(255, 255, 0, 255)); // giallo trasparente
            g2d.fillOval(x - 1, y - 1, diametro + 2, diametro + 2);
        }

        // Numero centrato
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        FontMetrics fm = g2d.getFontMetrics();
        int stringWidth = fm.stringWidth(numero);
        int stringAscent = fm.getAscent();

        int textX = x + (diametro - stringWidth) / 2;
        int textY = y + (diametro + stringAscent) / 2 - 1;

        g2d.drawString(numero, textX, textY);
    }

    public void mostraCerchioGiallo() {
        mostraCerchioGiallo = true;
        repaint();
    }

    public void nascondiCerchioGiallo() {
        mostraCerchioGiallo = false;
        repaint();
    }
}
