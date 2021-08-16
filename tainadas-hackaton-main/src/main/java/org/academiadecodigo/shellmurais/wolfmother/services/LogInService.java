package org.academiadecodigo.shellmurais.wolfmother.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

@Service
public class LogInService {

    private HashMap<String, String> userDetails;
    private Set<String> emails;

    public LogInService(){
        userDetails = new HashMap<>();
        addUserDetails(userDetails);
        emails = userDetails.keySet();
    }

    public boolean start(String email, String password) {
        int tries = 0;

        if (!userDetails.containsKey(email)) {
            System.out.println("Mail not found. Create one first");
            tries++;
        }
        if(tries > 3){
            return false;
        }

        return checkIfPasswordMatch(email, password);
    }

    public void addUserDetails(HashMap<String, String> userDetails) {
        try {
            BufferedReader credentialsReader = new BufferedReader(new FileReader("/Users/codecadet/Desktop/tainadas-hackaton/src/main/resources/credentials.txt"));
            String line;
            String result = "";

            while (((line = credentialsReader.readLine()) != null)) {
                result += line + ": ";
            }

            String[] words = result.split(": ");

            String[] mails = new String[words.length / 2];
            String[] passwords = new String[words.length / 2];
            int j = 0;

            for (int i = 0; i < mails.length; i++) {
                mails[i] = words[j];
                j += 2;
            }

            j = 1;

            for (int i = 0; i < passwords.length; i++) {
                passwords[i] = words[j];
                j+=2;
            }

            for (int i = 0; i < mails.length; i++) {
                userDetails.put(mails[i], passwords[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfPasswordMatch(String mail, String credential) {
        int tries = 3;

        if (userDetails.get(mail) == null){
            return false;
        }

        while (!userDetails.get(mail).equals(credential) && tries > 0) {

            System.out.println("Invalid Password. You have " + tries + " more tries.");
            tries--;

            if (tries == 0) {
                System.out.println("Out of tries");
                return false;
            }
        }

        System.out.println("Login Successful! Welcome " + mail + ".");
        return true;

    }

}
