package com.example.spring_demo.controller;

import com.example.spring_demo.model.Author;
import com.example.spring_demo.model.Book;
import com.example.spring_demo.repository.AuthorRepository;
import com.example.spring_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MainController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute Author author){
        authorRepository.save(author);
        return "redirect:/";
    }

    @GetMapping("/")
    public String homePage(ModelMap modelMap){
        List<Author> authors=authorRepository.findAll();
        modelMap.addAttribute("authors",authors);
        return "home";
    }


    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/";
    }



}