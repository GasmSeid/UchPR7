package com.example.Rabota.Controller;

import com.example.Rabota.Models.Engine;

import com.example.Rabota.Models.Manager;
import com.example.Rabota.repo.ManagerRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;
//    @Autowired
//    private PassportRepository passportRepository;
@PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Manager")
    public String GetRab(Model model)
    {
        Iterable<Manager> manager = managerRepository.findAll();
        model.addAttribute("Manager", manager);
        return "MainManager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/Add/Manager")
    public String GetAddVin(Manager manager)
    {
        return "Add-Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Add/Manager")
    public String blogPostAdd(@Valid Manager manager, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Manager";}
        managerRepository.save(manager);
        return "redirect:/Manager";
    }

    /////
//    @GetMapping("/Manager")
//    public String MainGet(Model model){
//        Iterable<Passport> passports = passportRepository.findAll();
//        model.addAttribute("passports", passports);
//        return "MainManager";
//    }
//
//    @GetMapping("Add-Manager")
//    public String newManager(Manager manager,Model model)
//    {
//        return "MainManager";
//    }
//
//

//    @GetMapping("/Add/Manager")
//    public String managerGetAdd(@ModelAttribute("passport") Passport passport, Model model)
//    {
//        Iterable<Passport> passports = passportRepository.findAll();
//        ArrayList<Passport> passportArrayList = new ArrayList<>();
//        for (Passport passport1: passports) {
//            if(passport1.getOwner()==null) {
//                passportArrayList.add(passport1);
//            }
//        }
//        model.addAttribute("passports",passportRepository);
//        return "redirect:/Manager";
//    }

//    @PostMapping("/Add/Manager")
//    public String blogPostAdd(@ModelAttribute("passports") @Valid Manager manager, BindingResult bindingResult, @RequestParam String surname, Model model)
//    {
//        manager.setPassport(passportRepository.findByNumber(surname));
//        if(bindingResult.hasErrors()){return "Add-Passport";}
//        managerRepository.save(manager);
//        return "redirect:/Manager";
//    }

//    @PostMapping("/Add/Manager")
//    public String blogPostAdd(@RequestParam String surname, @RequestParam String number, Model model)
//    {
//        System.out.println(surname);
//        Passport passport = passportRepository.findByNumber(number);
//        System.out.println(passport.getId());
//        Manager manager = new Manager(surname, passport);
//        managerRepository.save(manager);
//        return "MainManager";
//    }
    ////


//    @PostMapping("/Passport")
//    public String managerPostAdd(@ModelAttribute("passport") @Valid Passport passport, BindingResult bindingResult,
//                            Model model)
//    {
//        if(bindingResult.hasErrors()) return "Add-Passport";
//        passportRepository.save(passport);
//        return "redirect:/";
//    }
//    @GetMapping("/Add-Passport")
//    public String AddVy(Model model)
//    {
//
//        Iterable<Passport> passport = passportRepository.findAll();
//        model.addAttribute("passport", passport);
//        return "Add-Passport";
//    }
//    @GetMapping("/Passport")
//    public String newPassport(Passport passport)
//    {
//        return "Add-Passport";
//    }


    /////


    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping( path = "/Search/Manager")
    public String blogFilter(Manager manager)
    {
        return "Search-Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Search/Manager-result")
    public String blogResult(@RequestParam String surname, Model model)
    {
        List<Manager> manager = managerRepository.findBySurname(surname);
        model.addAttribute("result", manager);
        return "Search-Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/Search/Manager")
    public String simpleSearch(@RequestParam String surname, Model model)
    {
        List<Manager> manager = managerRepository.findBySurnameContains(surname);
        model.addAttribute("result", manager);
        return "Search-Manager";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/blog/Manager/{id}/Edit")
    public String blogEdit(@PathVariable(value = "id") Long id, Model model){
        Manager manager = managerRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid car Id" + id));
        model.addAttribute("Manager",manager);
        return "Edit-manager";

    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/blog/Manager/{id}/Edit")
    public String GameUpdate(@ModelAttribute("Manager") @Valid Manager manager, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-manager";    managerRepository.save(manager);    return "redirect:/Manager";}

    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @PostMapping("/blog/Manager/{id}/Remove")
    public String blogManagerDelete(
            @PathVariable(value = "id") Long id,
            Model model) {
        Manager manager = managerRepository.findById(id).orElseThrow();
        managerRepository.delete(manager);
        return "redirect:/Manager";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'KADROVIK')")
    @GetMapping("/blog/Manager/{id}")
    public String CarDetails(@PathVariable(value = "id") Long id, Model model) {
        Optional<Manager> manager = managerRepository.findById(id);
        ArrayList<Manager> lis = new ArrayList<>();
        manager.ifPresent(lis::add);
        model.addAttribute("Manager", lis);
        return "blog-ManagerDetails";
    }
}
