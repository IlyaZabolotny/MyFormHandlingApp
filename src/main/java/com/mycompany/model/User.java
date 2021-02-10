package com.mycompany.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class User {

    @Size(min = 2, max = 30, message = "Размер поля должен находиться в диапазоне от 2 до 30 символов")
    private String lastName;
    @Size(min = 2, max = 30, message = "Размер поля должен находиться в диапазоне от 2 до 30 символов")
    private String firstName;
    @Size(min = 2, max = 30, message = "Размер поля должен находиться в диапазоне от 2 до 30 символов")
    private String patronymic;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(value = 18, message = "Возраст пользователя должен быть не меньше 18 лет")
    private Integer age;
    @Min(value = 12_130, message = "Уровень зарплаты должен быть больше 12130 руб")
    @NotNull(message = "Поле не должно быть пустым")
    private Integer salary;
    @Email(message = "Введите корректный адрес электронной почты")
    @NotBlank(message = "Поле не должно быть пустым")
    private String email;
    @NotBlank(message = "Поле не должно быть пустым")
    private String jobPlace;

    public User() {
    }

    public User(String lastName,
                String firstName,
                String patronymic,
                Integer age,
                Integer salary,
                String email,
                String jobPlace) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.jobPlace = jobPlace;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobPlace() {
        return jobPlace;
    }

    public void setJobPlace(String jobPlace) {
        this.jobPlace = jobPlace;
    }


    @Override
    public String toString() {
        return "User{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", jobPlace='" + jobPlace + '\'' +
                '}';
    }
}
