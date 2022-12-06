package com.example.Rabota.Models;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Max(value = 2028, message = "Максимальное значение - 2028")
    @Pattern(regexp = "^[0-9]+$", message = "Поле Л/С может содеражать только цифры")
    private String horsepower;

    @Size(min=1, max=4, message ="Поле Объем двигателя может содеражать до 4 символов")
    @Pattern(regexp = "^[0-9.]+$", message = "Объем двигателя может содержать только цифры и точку")
    private String engineCapacity;

    @OneToMany(mappedBy = "engine", fetch = FetchType.EAGER)
    private Collection<Cars> tenants;

    public Collection<Cars> getTenants() {
        return tenants;
    }
    public void setTenants(Collection<Cars> tenants) {
        this.tenants = tenants;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getHorsepower() {return horsepower;}

    public void setHorsepower(String horsepower) {this.horsepower = horsepower;}

    public String getEngineCapacity() {return engineCapacity;}

    public void setEngineCapacity(String engineCapacity) {this.engineCapacity = engineCapacity;}
    public Engine() {}

    public Engine(String horsepower, String engineCapacity) {
        this.horsepower = horsepower;
        this.engineCapacity = engineCapacity;
    }
}