package fr.gregwll.callsigngen.frames;

import javax.swing.*;
import java.awt.*;

public class ResultFrame {

    private static JFrame frame;
    private static JPanel panel;

    public static void display(String result) {
        frame = new JFrame("Callsign Generator - gregwll - Results");
        panel = (JPanel) frame.getContentPane();

        frame.setVisible(true);
        frame.setSize(400, 200);

        JTextField resultF = new JTextField(30);
        resultF.setSize(200, 50);
        resultF.setLocation(10,100);
        resultF.setEditable(false);
        resultF.setForeground(Color.BLUE);
        panel.add(resultF);

        resultF.setText(result.toUpperCase());

        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    public static void hide() {
        frame.setVisible(false);
    }
}
