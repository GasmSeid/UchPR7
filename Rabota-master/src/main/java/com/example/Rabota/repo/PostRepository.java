package com.example.Rabota.repo;

import com.example.Rabota.Models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    Post findByPostname(String postname);
}
