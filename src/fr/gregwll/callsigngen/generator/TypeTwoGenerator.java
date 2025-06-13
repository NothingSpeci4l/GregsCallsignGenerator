package fr.gregwll.callsigngen.generator;

import java.util.Random;

public class TypeTwoGenerator {
    // xxx 4212

    public static String generate(int numofnb) {
        Random random = new Random();
        StringBuilder total = new StringBuilder();
        int i = 0;
        while(i != numofnb) {
            i++;
            total.append(random.nextInt(10));
        }
        return (total.toString());
    }
}
