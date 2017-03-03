package sample.Read;

import sample.Controller;

import java.io.*;
import java.util.Map;

/**
 * Created by Askew on 3/3/2017.
 */
public class ReadData {
    private static final String FILENAME = "data.txt";

    public static void readFile(Map<String, String> englishMap, Map<String, String> lithuanianMap) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(FILENAME)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split("/");

                if (values.length == 2) {
                    englishMap.put(values[0], values[1]);
                    lithuanianMap.put(values[1], values[0]);
                }

            }
        } catch (IOException e) {
            //TODO display Alert file unable to be read.

        }
    }

}
