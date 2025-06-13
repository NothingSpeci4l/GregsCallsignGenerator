package fr.gregwll.callsigngen.generator;

import java.util.Random;

public class TypeOneGenerator {

    public static String generate(int numofnb) {
        Random random = new Random();
        StringBuilder total = new StringBuilder();
        int i = 0;
        while(i != numofnb) {
            i++;
            total.append(random.nextInt(10));
        }
        total.append((char) ('a' + random.nextInt(26)));
        total.append((char) ('a' + random.nextInt(26)));

        return (total.toString());
    }
}
