package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

abstract class BaseWindowFrame extends JFrame {

    private Point dragPoint;

    BaseWindowFrame() {
        dragPoint = null;
    }

    void renderFrame() {

        Color bg = new Color(36, 34, 38);

        JPanel mainContent = mainContent();
        mainContent.setBackground(bg);

        JPanel top = initTopPanel(mainContent.getPreferredSize().width);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setBackground(bg);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(top)
            .addComponent(mainContent)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(top)
            .addComponent(mainContent)
        );

        pack();

        setResizable(false);
        setVisible(true);

    }

    abstract JPanel mainContent();

    private JPanel initTopPanel(int frameWidth) {

        JPanel top = new JPanel();
        GroupLayout layout = new GroupLayout(top);
        top.setLayout(layout);

        top = Utility.setComponentSize(top, frameWidth, 30);
        top.setBackground(new Color(25, 24, 26));

        JLabel title = new JLabel("Minesweeper");
        title = Utility.setComponentSize(title, 77, 30);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.WHITE);

        int blah = frameWidth - 30;
        int gap = blah - 87;

        CustomButton xButton = new CustomButton(ResourceHandler.xButton, 30, 30);
        xButton.setHoverIcon(ResourceHandler.xButtonHover);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGap(10)
            .addComponent(title)
            .addGap(gap)
            .addComponent(xButton)
        );
        layout.setVerticalGroup(layout.createParallelGroup()
            .addComponent(title)
            .addComponent(xButton)
        );

        top.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed(e);
            }
        });
        top.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragged(e);
            }
        });

        return top;

    }

    private void pressed(MouseEvent e) {
        dragPoint = e.getPoint();
    }

    private void dragged(MouseEvent e) {
        if (dragPoint != null) {
            Point current = e.getLocationOnScreen();
            int x = current.x - dragPoint.x;
            int y = current.y - dragPoint.y;
            setLocation(x, y);
        }
    }

}