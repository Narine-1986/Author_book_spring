package com.example.spring_demo.controller;
import com.example.spring_demo.model.Author;
import com.example.spring_demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/allAuthors")
    public String getAllAuthors(ModelMap modelMap){
        List<Author> authors=authorRepository.findAll();
        modelMap.addAttribute("authors",authors);
        return "authorHome";
    }

        @GetMapping("/deleteAuthor")
    public String deleteAuthors(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return ("redirect:/allAuthors");
    }

    @RequestMapping("/updateAuthors")
    public String UpdateAuthors(ModelMap modelMap, int id) {
        Author one = authorRepository.getOne(id);
        modelMap.addAttribute("authors", one);
        return ("updateAuthors");
    }

    @PostMapping("/authorUpdate")
    public String authorUpdate(@ModelAttribute Author author) {
        authorRepository.save(author);
        return ("redirect:/");
    }


}