package com.zetcode.controller;

import com.zetcode.model.Post;
import com.zetcode.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@Controller
public class MyController {

    @Autowired
    private IPostService postService;

    @GetMapping(value="/posts")
    public ResponseEntity<Set<Post>> all() {
        return ok().body(postService.all());
    }

    // ruleid: rest-DeleteMapping
    @DeleteMapping(value = "/posts/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable Long id) {

        var isRemoved = postService.delete(id);

        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
