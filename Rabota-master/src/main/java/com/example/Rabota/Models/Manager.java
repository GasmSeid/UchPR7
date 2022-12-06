package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min=2, max=20, message ="Фамилия не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String surname;
    @Size(min=2, max=20, message ="Имя не может быть меньше 2 и не больше 20")
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String name;
    @Pattern(regexp = "^[а-яА-Я]+$", message = "Разрешены только буквы кириллицы")
    private String middlename;
    @Min(value = 1957, message = "Минимальная дата рождения - 1957 (65 лет)")
    @Max(value = 2006, message = "Минимальная дата рождения - 2006 (16 лет)")
    @Pattern(regexp = "^[0-9]+$", message = "Дата рождения может состоять только из цифр")
    private String bthday;

    @NotEmpty(message = "Заполните поле Адрес регистрации")
    private String AdresReg;
    @NotEmpty(message = "Заполните поле Email")
    @Email(message = "Электронная почта должна включать в себя символы @ и .")
    private String Email;

    @ManyToMany
    @JoinTable (name="manager_post",
            joinColumns=@JoinColumn (name="manager_id"),
            inverseJoinColumns=@JoinColumn(name="post_id"))
    private List<Post> posts;

//    @OneToOne(optional = true, cascade = CascadeType.ALL)
//    @JoinColumn(name="passport_id")
//    private Passport passport;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> universities) {
        this.posts = posts;
    }

//    public Passport getPassport() {
//        return passport;
//    }
//
//    public void setPassport(Passport passport) {
//        this.passport = passport;
//    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getBthday() {
        return bthday;
    }

    public void setBthday(String bthday) {
        this.bthday = bthday;
    }

    public String getAdresReg() {
        return AdresReg;
    }
    public void setAdresReg(String AdresReg) {
        this.AdresReg = AdresReg;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Manager(String surname) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.AdresReg = AdresReg;
        this.Email = Email;
//        this.passport = passport;

    }

    public Manager() {
    }
}
