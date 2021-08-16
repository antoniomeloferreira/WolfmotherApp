package org.academiadecodigo.shellmurais.wolfmother.services;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CreateProfileService {

    private BufferedWriter writer;

    public void openWriterStream() {
        try {
            writer = new BufferedWriter(new FileWriter("/Users/codecadet/Desktop/tainadas-hackaton/src/main/resources/credentials.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriterStream() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String mail, String password) {


        openWriterStream();

        String user = mail + ": " + password;


        try {
                writer.write(user, 0, user.length());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        closeWriterStream();
    }

}
