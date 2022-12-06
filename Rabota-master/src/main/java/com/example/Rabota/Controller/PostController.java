package com.example.Rabota.Controller;

import com.example.Rabota.Models.Post;
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
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @GetMapping("/Post")
    public String GetRab(Model model)
    {
        Iterable<Post> post = postRepository.findAll();
        model.addAttribute("Post", post);
        return "MainPost";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @GetMapping("/Add/Post")
    public String GetAddVin(Post post)
    {
        return "Add-Post";
    }

    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @PostMapping("/Add/Post")
    public String blogPostAdd(@Valid Post post, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){return "Add-Post";}
        postRepository.save(post);
        return "redirect:/Post";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @GetMapping("/Post/{id}/Edit")
    public String PostEdit(@PathVariable(value = "id") Long id, Model model)
    {
        Post post = postRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid post Id" + id));
        model.addAttribute("post",post);
        return "Edit-post";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @PostMapping("/Post/{id}/Edit")
    public String GameUpdate(@ModelAttribute("post") @Valid Post post, BindingResult bindingResult)
    {if (bindingResult.hasErrors()) return "Edit-post";    postRepository.save(post);    return "redirect:/Post";}
    @PreAuthorize("hasAnyAuthority('MANAGER', 'KADROVIK')")
    @PostMapping("/Post/{id}/Remove")
    public String blogPostDelete(
            @PathVariable(value = "id") Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/Post";
    }
}
