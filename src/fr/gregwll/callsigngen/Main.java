package fr.gregwll.callsigngen;

import fr.gregwll.callsigngen.frames.MainFrame;
import fr.gregwll.callsigngen.utils.Logger;

public class Main {
    static final Logger logger = new Logger("[Callsigngen]");

    public static void main(String[] args) {
        MainFrame.init();
        MainFrame.display();
    }

    public static Logger getLogger() {
        return logger;
    }
}
