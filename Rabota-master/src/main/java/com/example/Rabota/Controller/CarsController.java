package com.example.Rabota.Controller;
import com.example.Rabota.Models.Cars;
import com.example.Rabota.Models.Engine;
import com.example.Rabota.repo.CarsRepository;
import com.example.Rabota.repo.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Rabota.Models.Engine.*;

import java.util.ArrayList;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CarsController {
    @Autowired
    private CarsRepository carsRepository;
    @Autowired
    public EngineRepository engineRepository;

    ////

    @GetMapping("/")
    public String GetRab(Model model)
    {
        Iterable<Engine> engines = engineRepository.findAll();
        Iterable<Cars> cars = carsRepository.findAll();
        model.addAttribute("cars",cars);
        model.addAttribute("engine", engines);
        return "Main";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/Cars")
    public String GetAddCar(Cars cars, Model model)
    {
        Iterable<Engine> engines=engineRepository.findAll();
        ArrayList<Engine> engineArrayList=new ArrayList<>();
        for (Engine engine: engines)
        {
            if (engine.getEngineCapacity()==null)
            {
                engineArrayList.add(engine);
            }
        }
        model.addAttribute("engine", engines);
        return "Add-Cars";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Add/Cars")
    public String blogPostAdd(@ModelAttribute("cars")@Valid Cars cars, BindingResult bindingResult, @RequestParam String horsepower,  Model model)
    {

        cars.setEngine(engineRepository.findByHorsepower(horsepower));
        if(bindingResult.hasErrors())
        {
            return "Add-Cars";
        }
        carsRepository.save(cars);
        return "redirect:/";
    }
    ///


//    @GetMapping("/")
//    public String GetRab(Model model)
//    {
//        Iterable<Cars> cars = carsRepository.findAll();
//        model.addAttribute("Cars",cars);
//        return "Main";
//    }
//    @GetMapping("/Add/Cars")
//    public String GetAddCars(Cars cars, Model model)
//    {
//        return "Add-Cars";
//    }
//    @PostMapping("/Add/Cars")
//    public String blogPostAdd(@Valid Cars cars, BindingResult bindingResult)
//    {
//        if(bindingResult.hasErrors()){return "Add-Cars";}
//        carsRepository.save(cars);
//        return "redirect:/";
//    }

    ////
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping( path = "/Search/Cars")
    public String blogFilter(Cars cars)
    {
        return "Search-Cars";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Search/Cars-result")
    public String blogResult(@RequestParam String carsmodel, Model model)
    {
        List<Cars> cars = carsRepository.findByCarsmodel(carsmodel);
        model.addAttribute("result", cars);
        return "Search-Cars";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Search/Cars")
    public String simpleSearch(@RequestParam String carsmodel, Model model)
    {
        List<Cars> cars = carsRepository.findByCarsmodelContains(carsmodel);
        model.addAttribute("result", cars);
        return "Search-Manager";
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/blog/Cars/{id}/Edit")
    public String CarEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Cars cars = carsRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid car Id" + id));
        model.addAttribute("cars",cars);
        return "Edit-cars";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/blog/Cars/{id}/Edit")
    public String GameUpdate(@ModelAttribute("cars") @Valid Cars cars, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-cars";    carsRepository.save(cars);    return "redirect:/";}

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/blog/Cars/{id}/Remove")
    public String blogCarDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Cars cars = carsRepository.findById(id).orElseThrow();
        carsRepository.delete(cars);
        return "redirect:/";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/blog/Cars/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Cars> cars = carsRepository.findById(id);
        ArrayList<Cars> lis = new ArrayList<>();
        cars.ifPresent(lis::add);
        model.addAttribute("Cars", lis);
        return "blog-CarsDetails";
    }
}