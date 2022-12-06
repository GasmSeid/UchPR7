package com.example.Rabota.Models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = "В цвете могут использоваться английские, русские буквы и цифры")
    private String color;

    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = "В виде покрытия могут использоваться английские, русские буквы и цифры")
    private String typeСoating;


    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTypeСoating() {return typeСoating;}

    public void seTypeСoating(String typeСoating) {
        this.typeСoating = typeСoating;
    }

    public Color() {
    }

    public Color(String color, String typeСoating) {
        this.color = color;
        this.typeСoating = typeСoating;
    }
}
