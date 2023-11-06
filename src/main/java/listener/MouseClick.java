package listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** Логика на поределенные действия мыши */
public class MouseClick implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = e.getComponent();
        if (component.getBackground().equals(Color.RED) || component.getBackground().equals(Color.GREEN)){
            component.setBackground(Color.WHITE);
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
