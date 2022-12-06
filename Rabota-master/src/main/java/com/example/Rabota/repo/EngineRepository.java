package com.example.Rabota.repo;

import com.example.Rabota.Models.Engine;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface EngineRepository extends CrudRepository<Engine, Long>  {

    Engine findByHorsepower(String horsepower);
}
