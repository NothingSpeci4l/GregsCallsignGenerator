package fr.gregwll.callsigngen.utils;

import javax.swing.*;

public class Logger {

    String prefix;

    public Logger(String prefix) {
        this.prefix = prefix;
    }

    public void sendInfo(String content) {
        System.out.println(prefix + " " + content);
    }

    public void sendError(String content) {
        System.err.println(prefix + "-error : " + content);
    }

    public void sendDialogError(String content, JFrame frame) {
        JOptionPane.showMessageDialog(frame, content, "Error", JOptionPane.ERROR_MESSAGE);
    }


}
