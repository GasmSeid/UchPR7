package com.example.Rabota.Models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Table(name = "avto")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Заполните поле Марка")
    @Pattern(regexp ="^[а-яА-ЯёЁa-zA-Z0-9]+$", message = " В марке используйте английские, русские буквы и цифры")
    private String carbrand;
    @NotEmpty(message = "Заполните поле Модель")
    private String carsmodel;
    @NotEmpty(message = "Заполните поле Год выпуска")
    @Pattern(regexp = "^[0-9]+$", message = "Год выпуска может состоять только из цифр")
    @Min(value = 1885, message = "Минимальный год выпуска - 1885")
    @Max(value = 2022, message = "Максимальный год выпуска - 2022")
    private String year_release;
    @NotEmpty(message = "Заполните поле Цвет")
    private String color;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Engine engine;



    public Cars(String carbrand, Engine engine1) {
    }

    public Cars() {

    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }


    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;}

    public String getCarbrand() {return carbrand;}

    public void setCarbrand(String carbrand) {this.carbrand = carbrand;}

    public String getCarsmodel() {return carsmodel;}

    public void setCarsmodel(String carsmodel) {this.carsmodel = carsmodel;}

    public String getYear_release() {return year_release;}

    public void setYear_release(String year_release) {this.year_release = year_release;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public Cars(String carbrand, String carsmodel, String year_release, String color, Engine engine) {
        this.carbrand = carbrand;
        this.carsmodel = carsmodel;
        this.year_release = year_release;
        this.color = color;
        this.engine = engine;
    }
}
