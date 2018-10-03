package main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

class NewGameWindow extends JFrame {

    private Point test;

    NewGameWindow() {
        super();
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {


        CustomRadioButton beginnerButton = new CustomRadioButton(ResourceHandler.beginner, ResourceHandler.beginnerH, ResourceHandler.beginnerI, 150, 50);
        CustomRadioButton intermediateButton = new CustomRadioButton(ResourceHandler.intermediate, ResourceHandler.intermediateH, ResourceHandler.intermediateI, 150, 50);
        CustomRadioButton advancedButton = new CustomRadioButton(ResourceHandler.advanced, ResourceHandler.advancedH, ResourceHandler.advancedI, 150, 50);

        ButtonGroup group = new ButtonGroup();
        group.add(beginnerButton);
        group.add(intermediateButton);
        group.add(advancedButton);

        beginnerButton.setSelected(true);

        Color bg = new Color(36, 34, 38);

        JPanel top = new JPanel();
        BoxLayout boxLayout = new BoxLayout(top, BoxLayout.X_AXIS);
        top.setLayout(boxLayout);

        JLabel title = new JLabel("Minesweeper");
        title.setForeground(Color.WHITE);
        title.setBorder(new EmptyBorder(0, 10, 0, 0));
        top.add(title);

        top.setMinimumSize(new Dimension(450, 30));
        top.setPreferredSize(new Dimension(450, 30));
        top.setMaximumSize(new Dimension(450, 30));
        top.setBackground(bg);

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

        LinkedList<JTextField> textFields = new LinkedList<JTextField>();
        textFields.add(new JTextField());
        textFields.add(new JTextField());
        textFields.add(new JTextField());

        for (JTextField textField : textFields) {
            textField.setBorder(null);
            textField.setMinimumSize(new Dimension(50, 30));
            textField.setPreferredSize(new Dimension(50, 30));
            textField.setMaximumSize(new Dimension(50, 30));
            textField.setSize(new Dimension(50, 30));
            textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(8, 8, 8, 8)));
            textField.setBackground(new Color(60, 57, 64));
            textField.setForeground(Color.WHITE);
            textField.setCaretColor(Color.WHITE);
        }

        textFields.get(0).setText("9");
        textFields.get(1).setText("9");
        textFields.get(2).setText("10");

        beginnerButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                textFields.get(0).setText("9");
                textFields.get(1).setText("9");
                textFields.get(2).setText("10");
            }
        });

        intermediateButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                textFields.get(0).setText("16");
                textFields.get(1).setText("16");
                textFields.get(2).setText("40");
            }
        });

        advancedButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                textFields.get(0).setText("16");
                textFields.get(1).setText("30");
                textFields.get(2).setText("99");
            }
        });

        LinkedList<JLabel> labels = new LinkedList<JLabel>();
        labels.add(new JLabel("width"));
        labels.add(new JLabel("height"));
        labels.add(new JLabel("mines"));

        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label.setMinimumSize(new Dimension(50, 30));
            label.setPreferredSize(new Dimension(50, 30));
            label.setMaximumSize(new Dimension(50, 30));
            label.setSize(new Dimension(50, 30));
        }

        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // end program when this frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setBackground(bg);

        // hand-coded grouplayout stuff
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addComponent(top)
            .addGroup(layout.createSequentialGroup()
                .addComponent(beginnerButton)
                .addComponent(intermediateButton)
                .addComponent(advancedButton))
            .addGroup(layout.createSequentialGroup()
                .addGap(15)
                .addComponent(labels.get(0))
                .addGap(5)
                .addComponent(textFields.get(0)))
            .addGroup(layout.createSequentialGroup()
                .addGap(15)
                .addComponent(labels.get(1))
                .addGap(5)
                .addComponent(textFields.get(1)))
            .addGroup(layout.createSequentialGroup()
                .addGap(15)
                .addComponent(labels.get(2))
                .addGap(5)
                .addComponent(textFields.get(2)))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(top)
            .addGroup(layout.createParallelGroup()
                .addComponent(beginnerButton)
                .addComponent(intermediateButton)
                .addComponent(advancedButton))
            .addGap(15)
            .addGroup(layout.createParallelGroup()
                .addComponent(labels.get(0))
                .addComponent(textFields.get(0)))
            .addGap(5)
            .addGroup(layout.createParallelGroup()
                .addComponent(labels.get(1))
                .addComponent(textFields.get(1)))
            .addGap(5)
            .addGroup(layout.createParallelGroup()
                .addComponent(labels.get(2))
                .addComponent(textFields.get(2)))
            .addGap(75)
        );

        pack();

    }

    private void pressed(MouseEvent e) {
        test = e.getPoint();
    }

    private void dragged(MouseEvent e) {
        Point current = e.getLocationOnScreen();
        int x = current.x - test.x;
        int y = current.y - test.y;
        setLocation(x, y);
    }

}