package com.example.Rabota.repo;

import com.example.Rabota.Models.Cars;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CarsRepository extends CrudRepository<Cars,Long> {

    List<Cars> findByCarsmodel(String carsmodel);
    List<Cars> findByCarsmodelContains (String carsmodel);
}
