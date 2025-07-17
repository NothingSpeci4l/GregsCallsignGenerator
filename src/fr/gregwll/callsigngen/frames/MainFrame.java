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

        frame.setSize(350, 465);
        frame.setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(15,15,15));

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
        title.setSize(325, 50);
        title.setFont(new Font("Arial", Font.BOLD, 35));
        title.setForeground(Color.WHITE);
        title.setLocation(frame.getWidth() / 2 - title.getWidth() /2 , 20);
        panel.add(title);

        JLabel oacilabel = new JLabel("OACI (Ex: Ryr, Afr)");
        oacilabel.setSize(115, 20);
        oacilabel.setLocation(frame.getWidth() /2 - oacilabel.getWidth() / 2, 120);
        oacilabel.setForeground(Color.WHITE);
        panel.add(oacilabel);

        JTextField oaciField = new JTextField( 40);
        oaciField.setSize(100,20);
        oaciField.setLocation(frame.getWidth() /2 - oaciField.getWidth() / 2, 140);
        oaciField.setBackground(new Color(25,25,25));
        oaciField.setForeground(Color.WHITE);
        panel.add(oaciField);

        JCheckBox typeOneCheck = new JCheckBox("Type 1 (Ex: AFR54KJ)");
        typeOneCheck.setSize(200, 20);
        typeOneCheck.setForeground(Color.WHITE);
        typeOneCheck.setBackground(new Color(15,15,15));
        typeOneCheck.setLocation(frame.getWidth() /2 - typeOneCheck.getWidth() / 2, 180);
        panel.add(typeOneCheck);

        JCheckBox typeTwoCheck = new JCheckBox("Type 2 (Ex: ENT3630)");
        typeTwoCheck.setSize(200, 20);
        typeTwoCheck.setForeground(Color.WHITE);
        typeTwoCheck.setBackground(new Color(15,15,15));
        typeTwoCheck.setLocation(frame.getWidth() /2 - typeTwoCheck.getWidth() / 2, 200);
        panel.add(typeTwoCheck);

        JLabel sliderLabel = new JLabel("How many number ?");
        sliderLabel.setSize(130,20);
        sliderLabel.setForeground(Color.WHITE);
        sliderLabel.setLocation(frame.getWidth() /2 - sliderLabel.getWidth() / 2, 235);
        panel.add(sliderLabel);

        JSlider slider = new JSlider(1, 4);
        slider.setSize(200, 50);
        slider.setLocation(frame.getWidth() /2 - slider.getWidth() / 2, 250);
        slider.setMajorTickSpacing(1);
        slider.setPaintTrack(true);
        slider.setForeground(Color.WHITE);
        slider.setBackground(new Color(15,15,15));
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        panel.add(slider);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(!typeTwoCheck.isSelected() && !typeOneCheck.isSelected()) {
                    logger.sendDialogError("Please firstly choose a type", frame);
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
        generateButton.setLocation(frame.getWidth() /2 - generateButton.getWidth() / 2, 320);
        generateButton.setSelected(false);
        generateButton.setBackground(new Color(25,25,25));
        generateButton.setForeground(Color.WHITE);
        panel.add(generateButton);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!oaciField.getText().isEmpty()) {
                    if(typeTwoCheck.isSelected() || typeOneCheck.isSelected()) {


                        if(typeTwoCheck.isSelected()) {
                            ResultFrame.display(oaciField.getText().toUpperCase() + TypeTwoGenerator.generate(slider.getValue()));
                        } else {
                            if(slider.getValue() > 2) {
                                logger.sendDialogError("With type one, you can't set more than 2 numbers !", frame);
                            } else {
                                ResultFrame.display(oaciField.getText().toUpperCase() + TypeOneGenerator.generate(slider.getValue()));
                            }

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
        credit.setSize(frame.getWidth() / 2 - credit.getWidth() / 2, 20);
        credit.setLocation(5, 400);
        credit.setForeground(Color.WHITE);
        panel.add(credit);

        // JLabel OaciLabel = new JLabel("OACI");
    }
}
