package com.example.Rabota.Controller;

import com.example.Rabota.Models.Manager;
import com.example.Rabota.Models.Post;
import com.example.Rabota.repo.PostRepository;
import com.example.Rabota.repo.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DogovorController {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @GetMapping("/dogovor")
    private String Main(Model model){
        Iterable<Manager> managers = managerRepository.findAll();
        model.addAttribute("managers", managers);
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "dogovor";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @PostMapping("/dogovor/add")
    public String blogPostAdd(@RequestParam Long manager, @RequestParam Long post, Model model)
    {
        Manager manager2 = managerRepository.findById(manager).orElseThrow();
        Post post2 = postRepository.findById(post).orElseThrow();
        manager2.getPosts().add(post2);
        //university2.getStudents().add(student2);
        managerRepository.save(manager2);
        return "redirect:/dogovor";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @PostMapping("pst/delMan/{id_mangr}/{id_pst}")
    public String blogPostDell(@PathVariable Long id_mangr, @PathVariable Long id_pst, Model model)
    {
        Manager manager2 = managerRepository.findById(id_mangr).orElseThrow();
        Post post2 = postRepository.findById(id_pst).orElseThrow();
        manager2.getPosts().remove(post2);
        managerRepository.save(manager2);
        return "redirect:/dogovor";
    }

}
