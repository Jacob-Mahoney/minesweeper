package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// first frame that is shown on start
public class GameWindow extends JFrame {

    private JPanel grid;
    private JLabel timer;
    private GameGrid gameGrid;

    public GameWindow(GameGrid gameGrid) {
        super("Poop");
        this.gameGrid = gameGrid;
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    private void initComponents() {

        grid = new JPanel();
        timer = new JLabel("timer");

        GridLayout gridLayout = new GridLayout(9,9);

        ImageIcon squareIcon = getImageIconFromResource("../resources/square.jpg");
        ImageIcon squareIconHovered = getImageIconFromResource("../resources/square-hovered.jpg");

        grid.setLayout(gridLayout);
        gridLayout.setHgap(2);
        gridLayout.setVgap(2);
        grid.setBackground(Color.WHITE);
        for (int i = 0; i < 81; i++) {

            JButton button = new JButton();

            if (squareIcon != null && squareIconHovered != null) {

                button.setIcon(squareIcon);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(false);
                button.setFocusPainted(false);

                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        button.setIcon(squareIconHovered);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        button.setIcon(squareIcon);
                    }
                });

                button.addActionListener(this::onButtonClick);

            }

            button.setMinimumSize(new Dimension(24, 24));
            button.setPreferredSize(new Dimension(24, 24));
            button.setMaximumSize(new Dimension(24, 24));
            grid.add(button);

        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // end program when this frame is closed

        getContentPane().setBackground(Color.WHITE);

        // hand-coded grouplayout stuff
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(25)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(timer)
                                .addGap(15)
                                .addComponent(grid))
                        .addGap(25))
        );
        layout.setVerticalGroup(layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(timer)
                        .addGap(15)
                        .addComponent(grid)
                        .addGap(25))
        );

        pack();

    }

    ImageIcon getImageIconFromResource(String resource) {
        try {
            Image image = ImageIO.read(getClass().getResource(resource));
            return new ImageIcon(image);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    private void onButtonClick(ActionEvent e) {
        Object obj = e.getSource();
        if (obj instanceof JButton) {
            JButton button = (JButton) obj;
            System.out.print(button.getWidth());
        }
    }

}