package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UsersController {
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact_form";
    }

    @PostMapping("/contact")
    public String submitContact(HttpServletRequest request) {
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("formhandlingapp@yandex.ru");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
        return "sent_success";
    }

    @PostMapping("/")
    public String getUserData(@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, HttpServletRequest request, Model model) {
        User user = UserService.checkUser(lastName, firstName);
        if (user != null) {
            String userAgent = request.getHeader("user-agent");
            Date currentDate = new Date();
            SimpleDateFormat timeFormat = new SimpleDateFormat("dd.MM.yy HH:mm");
            model.addAttribute("user", user);
            model.addAttribute("userAgent", userAgent);
            model.addAttribute("time", timeFormat.format(currentDate));
            return "user_data";
        } else {
            return "error";
        }
    }

    @GetMapping("/register")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("uploadedUser", new User());
        return "register_form";
    }

    @PostMapping("/register")
    public String submitForm(@Valid @ModelAttribute("user") User user,
                             BindingResult bindingResult,
                             @RequestParam("file") MultipartFile multipartFile,
                             Model model) {
        if (!multipartFile.isEmpty()) {
            User uploadedUser = UserService.ReadFromMultipartFile(multipartFile);
            model.addAttribute("uploadedUser", uploadedUser);
            return "uploaded_success";
        } else {
            if (bindingResult.hasErrors()) {
                return "register_form";
            } else {
                UserService.handleFromData(user);
                return "register_success";
            }
        }
    }
}
