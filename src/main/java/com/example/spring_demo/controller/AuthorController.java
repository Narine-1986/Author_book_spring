package com.example.spring_demo.controller;

import com.example.spring_demo.model.Author;
import com.example.spring_demo.model.Book;
import com.example.spring_demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/allAuthors")
    public String getAllAuthors(ModelMap modelMap) {
        List<Author> authors = authorRepository.findAll();
        modelMap.addAttribute("authors", authors);
        return "authorHome";
    }

    @GetMapping("/deleteAuthor")
    public String deleteAuthor(@RequestParam("id") int id) {
        authorRepository.deleteById(id);
        return "redirect:/allAuthors";
    }

    @GetMapping("/updateAuthor")
    public String edit(@RequestParam("id") int id, Model model){
        Author author = authorRepository.getOne(id);
        model.addAttribute("author",author);
        return "updateAuthor";
    }

    @PostMapping("/updateAuthorSave")
    public String add(@ModelAttribute Author author) {
        authorRepository.save(author);
        return "redirect:/";
    }


}