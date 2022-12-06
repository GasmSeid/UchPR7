package com.example.Rabota.repo;

import com.example.Rabota.Models.Manager;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ManagerRepository extends CrudRepository<Manager,Long> {
    List<Manager> findBySurname( String surname);
    List<Manager> findBySurnameContains (String surname);

}
