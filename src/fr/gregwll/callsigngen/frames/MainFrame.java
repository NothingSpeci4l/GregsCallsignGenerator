package fr.gregwll.callsigngen.frames;

import fr.gregwll.callsigngen.Main;
import fr.gregwll.callsigngen.generator.TypeOneGenerator;
import fr.gregwll.callsigngen.generator.TypeTwoGenerator;
import fr.gregwll.callsigngen.utils.Logger;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {

    static Logger logger = Main.getLogger();

    private static JFrame frame;
    private static JPanel panel;

    public static void init() {
        frame = new JFrame("Callsign Generator - gregwll");
        panel = (JPanel) frame.getContentPane();

        frame.setSize(700, 700);
        frame.setContentPane(panel);
        panel.setLayout(null);

        addElements(panel);

        frame.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        logger.sendInfo("Main Frame init");
    }

    public static void display() {
        frame.setVisible(true);

        logger.sendInfo("Main frame displayed");
    }

    public static void hide() {
        frame.setVisible(false);

        logger.sendInfo("Main frame hided");
    }

    private static void addElements(JPanel panel) {
        JLabel title = new JLabel("Callsign Generator");
        title.setSize(300, 50);
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setLocation(245, 20);
        panel.add(title);

        JLabel oacilabel = new JLabel("OACI (Ex: Ryr, Afr)");
        oacilabel.setSize(200, 20);
        oacilabel.setLocation(5, 120);
        panel.add(oacilabel);

        JTextField oaciField = new JTextField( 20);
        oaciField.setSize(100,20);
        oaciField.setLocation(5, 140);
        panel.add(oaciField);

        JCheckBox typeOneCheck = new JCheckBox("Type 1 (Ex: AFR54KJ)");
        typeOneCheck.setSize(200, 20);
        typeOneCheck.setLocation(5, 180);
        panel.add(typeOneCheck);

        JCheckBox typeTwoCheck = new JCheckBox("Type 2 (Ex: ENT3630)");
        typeTwoCheck.setSize(200, 20);
        typeTwoCheck.setLocation(5, 200);
        panel.add(typeTwoCheck);

        JLabel sliderLabel = new JLabel("How many number ?");
        sliderLabel.setSize(150,20);
        sliderLabel.setLocation(5, 248);
        panel.add(sliderLabel);

        JSlider slider = new JSlider(1, 4);
        slider.setSize(200, 100);
        slider.setLocation(5, 250);
        slider.setMajorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(!typeTwoCheck.isSelected() && !typeOneCheck.isSelected()) {
                    logger.sendDialogError("Please firstly choose a type", frame);
                } else {
                    if(typeOneCheck.isSelected() && slider.getValue() > 2) {
                        slider.setValue(1);
                        logger.sendDialogError("With type one, you can't set more than 2 numbers !", frame);
                    }
                }
            }
        });

        typeOneCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                slider.setValue(2);
                if(typeTwoCheck.isSelected()) {
                    typeTwoCheck.setSelected(false);
                }
            }
        });

        typeTwoCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(typeOneCheck.isSelected()) {
                    typeOneCheck.setSelected(false);
                }
            }
        });

        JButton generateButton = new JButton("Generate");
        generateButton.setSize(200, 50);
        generateButton.setLocation(5, 500);
        panel.add(generateButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!oaciField.getText().isEmpty()) {
                    if(typeTwoCheck.isSelected() || typeOneCheck.isSelected()) {


                        if(typeTwoCheck.isSelected()) {
                            ResultFrame.display(oaciField.getText().toUpperCase() + TypeTwoGenerator.generate(slider.getValue()));
                        } else {
                            ResultFrame.display(oaciField.getText().toUpperCase() + TypeOneGenerator.generate(slider.getValue()));
                        }

                    } else {
                        logger.sendDialogError("You didn't choose type ! ", frame);
                    }
                } else {
                    logger.sendDialogError( "You didn't enter OACI !", frame);
                }
            }
        });

        JLabel credit = new JLabel("Made by gregwll with ❤");
        credit.setSize(200, 20);
        credit.setLocation(5, 650);
        panel.add(credit);

        // JLabel OaciLabel = new JLabel("OACI");
    }
}
