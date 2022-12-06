//package com.example.Rabota.Models;
//
//
//import javax.persistence.*;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name = "passports")
//public class Passport {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    @Size(min=4, max=4, message ="Серия паспорта - 4 символа")
//    @Pattern(regexp = "^[0-9]+$", message = "Серия паспорта может состоять только из цифр")
//    private String series;
//    @Size(min=6, max=6, message ="Номер паспорта - 6 символов")
//    @Pattern(regexp = "^[0-9]+$", message = "Номер паспорта может состоять только из цифр")
//    private String number;
//
//    @OneToOne(optional = true, mappedBy = "passport", cascade = CascadeType.ALL)
//    private Manager owner;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getSeries() {
//        return series;
//    }
//
//    public void setSeries(String series) {
//        this.series = series;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public Passport(String series, String number, Manager owner) {
//        this.series = series;
//        this.number = number;
//        this.owner = owner;
//    }
//
//    public Passport() {
//    }
//
//    public Manager getOwner() {
//        return owner;
//    }
//
//    public void setOwner(Manager owner) {
//        this.owner = owner;
//    }
//}
//
