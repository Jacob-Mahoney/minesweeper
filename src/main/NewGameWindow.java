package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

class NewGameWindow extends BaseWindowFrame {

    private ButtonGroup difficultyButtonGroup;
    private CustomRadioButton beginnerButton, intermediateButton, advancedButton;
    private IntegerTextField widthTextField, heightTextField, minesTextField;
    private CustomButton startButton;

    NewGameWindow() {
        renderFrame();
    }

    JPanel mainContent() {

        beginnerButton = new CustomRadioButton(ResourceHandler.beginner, ResourceHandler.beginnerH, ResourceHandler.beginnerI, 150, 50);
        intermediateButton = new CustomRadioButton(ResourceHandler.intermediate, ResourceHandler.intermediateH, ResourceHandler.intermediateI, 150, 50);
        advancedButton = new CustomRadioButton(ResourceHandler.advanced, ResourceHandler.advancedH, ResourceHandler.advancedI, 150, 50);

        difficultyButtonGroup = new ButtonGroup();
        difficultyButtonGroup.add(beginnerButton);
        difficultyButtonGroup.add(intermediateButton);
        difficultyButtonGroup.add(advancedButton);

        beginnerButton.setSelected(true);

        widthTextField = new IntegerTextField(100, 30, 0, 16);
        heightTextField = new IntegerTextField(100, 30, 0, 30);
        minesTextField = new IntegerTextField(100, 30, 0, 99);

        widthTextField.setText("9");
        heightTextField.setText("9");
        minesTextField.setText("10");

        LinkedList<JLabel> labels = new LinkedList<JLabel>();
        labels.add(new JLabel("width"));
        labels.add(new JLabel("height"));
        labels.add(new JLabel("mines"));

        for (JLabel label : labels) {
            label.setForeground(Color.WHITE);
            label = Utility.setComponentSize(label, 100, 30);
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }

        startButton = new CustomButton(ResourceHandler.start, 450, 50);
        startButton.setHoverIcon(ResourceHandler.startH);

        setupListeners();

        JPanel mainContent = new JPanel();
        GroupLayout layout = new GroupLayout(mainContent);
        mainContent.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup()
            .addGroup(layout.createSequentialGroup()
                .addComponent(beginnerButton)
                .addComponent(intermediateButton)
                .addComponent(advancedButton))
            .addGroup(layout.createSequentialGroup()
                .addGap(175)
                .addGroup(layout.createParallelGroup()
                    .addComponent(labels.get(0))
                    .addComponent(widthTextField)))
            .addGroup(layout.createSequentialGroup()
                .addGap(175)
                .addGroup(layout.createParallelGroup()
                    .addComponent(labels.get(1))
                    .addComponent(heightTextField)))
            .addGroup(layout.createSequentialGroup()
                .addGap(175)
                .addGroup(layout.createParallelGroup()
                    .addComponent(labels.get(2))
                    .addComponent(minesTextField)))
            .addComponent(startButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup()
                .addComponent(beginnerButton)
                .addComponent(intermediateButton)
                .addComponent(advancedButton))
            .addGap(10)
            .addComponent(labels.get(0))
            .addComponent(widthTextField)
            .addGap(10)
            .addComponent(labels.get(1))
            .addComponent(heightTextField)
            .addGap(10)
            .addComponent(labels.get(2))
            .addComponent(minesTextField)
            .addGap(30)
            .addComponent(startButton)
        );

        return mainContent;

    }

    private void setupListeners() {

        widthTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }
            @Override
            public void focusLost(FocusEvent e) {
                textFieldMatchCheck();
            }
        });

        heightTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }
            @Override
            public void focusLost(FocusEvent e) {
                textFieldMatchCheck();
            }
        });

        minesTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) { }
            @Override
            public void focusLost(FocusEvent e) {
                textFieldMatchCheck();
            }
        });

        beginnerButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                widthTextField.setText("9");
                heightTextField.setText("9");
                minesTextField.setText("10");
            }
        });

        intermediateButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                widthTextField.setText("16");
                heightTextField.setText("16");
                minesTextField.setText("40");
            }
        });

        advancedButton.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                widthTextField.setText("16");
                heightTextField.setText("30");
                minesTextField.setText("99");
            }
        });

        startButton.addActionListener((ActionEvent e) -> {
            int width = Integer.parseInt(widthTextField.getText());
            int height = Integer.parseInt(heightTextField.getText());
            int mines = Integer.parseInt(minesTextField.getText());
            WindowHandler.newGame(width, height, mines);
        });

    }

    private void textFieldMatchCheck() {
        if (widthTextField.getText().equals("9") && heightTextField.getText().equals("9") && minesTextField.getText().equals("10")) {
            beginnerButton.setSelected(true);
        } else if (widthTextField.getText().equals("16") && heightTextField.getText().equals("16") && minesTextField.getText().equals("40")) {
            intermediateButton.setSelected(true);
        } else if (widthTextField.getText().equals("16") && heightTextField.getText().equals("30") && minesTextField.getText().equals("99")) {
            advancedButton.setSelected(true);
        } else {
            difficultyButtonGroup.clearSelection();
        }
    }

}