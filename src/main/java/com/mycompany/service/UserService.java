package com.mycompany.service;

import com.mycompany.model.User;

import java.io.PrintWriter;
import java.util.Scanner;

public class UserService {

    public static void writeUser(PrintWriter out, User user) {
        out.println(user.getLastName() + "|" + user.getFirstName() + "|" +
                user.getPatronymic() + "|" + user.getAge() + "|" + user.getSalary()
                + "|" + user.getEmail() + "|" + user.getJobPlace());
    }

    public static User userSearch(Scanner in, String lastName, String firstName) {
        User user = null;
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] tokens = line.split("\\|");

            String currLastName = tokens[0];
            String currFirstName = tokens[1];
            String currPatronymic = tokens[2];
            Integer currAge = Integer.parseInt(tokens[3]);
            Integer currSalary = Integer.parseInt(tokens[4]);
            String currEmail = tokens[5];
            String currJobPlace = tokens[6];

            if (lastName.equals(currLastName) && firstName.equals(currFirstName)) {
                user = new User(currLastName, currFirstName, currPatronymic, currAge, currSalary, currEmail, currJobPlace);
                break;
            }
        }
        return user;
    }

    public static User readUser(Scanner in) {
        User user = null;
        if (in.hasNextLine()) {
            String line = in.nextLine();
            String[] tokens = line.split("\\|");

            String currLastName = tokens[0];
            String currFirstName = tokens[1];
            String currPatronymic = tokens[2];
            Integer currAge = Integer.parseInt(tokens[3]);
            Integer currSalary = Integer.parseInt(tokens[4]);
            String currEmail = tokens[5];
            String currJobPlace = tokens[6];
            user = new User(currLastName, currFirstName, currPatronymic, currAge, currSalary, currEmail, currJobPlace);
        }
        return user;
    }
}
