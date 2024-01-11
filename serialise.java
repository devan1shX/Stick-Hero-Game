package com.example.stickhero;

import java.io.*;


public class serialise {
    public int serialize() throws IOException {

        int result = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("/com/example/stickhero/ScoreCounter"))) {
            String line = reader.readLine();
            if (line != null) {
                result = Integer.parseInt(line.trim());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            // Handle the exception based on your application's requirements
        }
        return result;
    }

    public static void deserialize() throws IOException, ClassNotFoundException, IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("out.txt"));
            Character character = (Character) in.readObject();
            // Do something with the deserialized character object, if needed
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

}