package org.wasila.ntree_example.uiexample;

import org.wasila.ntree_example.Element;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * TODO 16.02.16: description
 */
public class CellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = -7799441088157759804L;
    private FileSystemView fileSystemView;
    private JLabel label;
    private Color textSelectionColor = Color.BLACK;
    private Color backgroundSelectionColor = Color.CYAN;
    private Color textNonSelectionColor = Color.BLACK;
    private Color backgroundNonSelectionColor = Color.WHITE;

    public CellRenderer() {
        label = new JLabel();
        label.setOpaque(true);
        fileSystemView = FileSystemView.getFileSystemView();
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean selected,
            boolean expanded) {

        if (value instanceof Element) {
            Element element = (Element) value;

            try {
                URL url = ClassLoader.getSystemResource(element.getIcon().getIconName());
                Image image = ImageIO.read(url);
                Icon icon = new ImageIcon(image);
                label.setIcon(icon);
            } catch (IOException e) {
                e.printStackTrace();
            }

            label.setText(element.getName());

        } else {
            label.setText(value.toString());
            label.setIcon(null);
        }

        if (selected) {
            label.setBackground(backgroundSelectionColor);
            label.setForeground(textSelectionColor);
        } else {
            label.setBackground(backgroundNonSelectionColor);
            label.setForeground(textNonSelectionColor);
        }

        return label;
    }

}
