package com.example.Rabota.Controller;

import com.example.Rabota.Models.Engine;
import com.example.Rabota.Models.Post;
import com.example.Rabota.repo.EngineRepository;
import com.example.Rabota.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class EngineController {
    @Autowired
    private EngineRepository engineRepository;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Engine")
    public String GetRab(Model model)
    {
        Iterable<Engine> engine = engineRepository.findAll();
        model.addAttribute("Engine", engine);
        return "MainEngine";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Add/Engine")
    public String GetAddVin(Engine engine)
    {
        return "Add-Engine";
    }

    @PostMapping("/Add/Engine")
    public String blogPostAdd(@Valid Engine engine, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Engine";}
        engineRepository.save(engine);
        return "redirect:/Engine";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @GetMapping("/Engine/{id}/Edit")
    public String PostEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Engine engine = engineRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid engine Id" + id));
        model.addAttribute("engine",engine);
        return "Edit-engine";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Engine/{id}/Edit")
    public String GameUpdate(@ModelAttribute("engine") @Valid Engine engine, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-engine";    engineRepository.save(engine);    return "redirect:/Engine";}
    @PreAuthorize("hasAnyAuthority('MANAGER', 'ADMIN')")
    @PostMapping("/Engine/{id}/Remove")
    public String blogPostDelete(
            @PathVariable(value = "id") Long id, Model model) {
        Engine engine = engineRepository.findById(id).orElseThrow();
        engineRepository.delete(engine);
        return "redirect:/Engine";
    }
}
