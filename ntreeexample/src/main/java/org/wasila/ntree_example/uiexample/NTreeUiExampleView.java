package org.wasila.ntree_example.uiexample;

import org.wasila.ntree_example.Element;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

/**
 * TODO 13.02.16: description
 */
public class NTreeUiExampleView {

    private NTreeUiExample model;

    private JFrame mainWindow;

    private DefaultListModel<Object> listModel;

    public NTreeUiExampleView() {
        mainWindow = new JFrame();
        mainWindow.setSize(400, 800);
        mainWindow.setMenuBar(provideMenu());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JList<Object> list = new JList<>();

        list.setCellRenderer(new CellRenderer());

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
//                    System.out.println("" + list.getSelectedIndex());
                }
            }
        });

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        System.out.println("Double-clicked on: " + o.toString());
                        onClick(o.toString());
                    }
                }
            }
        };
        list.addMouseListener(mouseListener);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listModel = new DefaultListModel<>();
        listModel.addElement("..");

        list.setModel(listModel);
        mainPanel.add(list, BorderLayout.CENTER);
        mainWindow.setContentPane(mainPanel);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private MenuBar provideMenu() {
        MenuBar menu = new MenuBar();
        Menu helpMenu = new Menu("Help");
        menu.add(helpMenu);
        MenuItem aboutMenu = new MenuItem("About");
        helpMenu.add(aboutMenu);
        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainWindow, new MessageWithLink(
                                "Example application of NTree library usage.<br/>" +
                                        "Icons from: <a href=\"https://icons8.com/\">https://icons8.com</a>"),
                        "About",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });
        return menu;
    }

    public void onClick(String nodeName) {
        if (nodeName.equals("..")) {
            model.selectExitNode();
        } else {
            model.selectNode(nodeName);
        }
    }

    public void start() {
        mainWindow.setVisible(true);
    }

    public void setModel(NTreeUiExample model) {
        this.model = model;
    }

    public void clearList() {
        listModel.clear();
    }

    public void addNode(String node) {
        listModel.addElement(node);
    }

    public void addNode(Element element) {
        listModel.addElement(element);
    }

    public static void main(String[] args) {
        NTreeUiExample ntreeUiExample = new NTreeUiExample(new NTreeUiExampleView());
        ntreeUiExample.start();
    }

}
