package com.mycompany.service;

import com.mycompany.model.User;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.Scanner;
import java.util.UUID;

public class UserService {

    private static String uploadPath = "E:/JavaProjects/uploads";

    public static User checkUser(String lastName, String firstName) {
        User user = null;
        try (Scanner in = new Scanner(
                new FileInputStream("users.txt"), "UTF-8")) {
            user = UserService.userSearch(in, lastName, firstName);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User ReadFromMultipartFile(MultipartFile multipartFile) {
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uploadPath + "/" + uuidFile + " " + multipartFile.getOriginalFilename();
        File file = new File(resultFileName);
        try {
            multipartFile.transferTo(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        User uploadedUser = new User();
        try (Scanner in = new Scanner(
                new FileInputStream(file), "UTF-8")) {
            uploadedUser = UserService.readUser(in);
            try (PrintWriter out = new PrintWriter(new FileWriter("users.txt", true))) {
                UserService.writeUser(out, uploadedUser);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return uploadedUser;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return uploadedUser;
    }

    public static void handleFromData(User user) {
        try (PrintWriter out = new PrintWriter(new FileWriter("users.txt", true))) {
            UserService.writeUser(out, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
