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
public class BookController {

    @Autowired
   private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/allBooks")
    public String getAllBooks(ModelMap modelMap) {
        List<Book> allBooks = bookRepository.findAll();
        modelMap.addAttribute("books", allBooks);
        return "allBooks";
    }

    @GetMapping("/deleteBooks")
    public String deleteBooks(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return ("redirect:/allBooks");
    }

    @RequestMapping("/updateBooks")
    public String UpdateBooks(ModelMap modelMap, int id) {
        Book one = bookRepository.getOne(id);
        List<Author> all = authorRepository.findAll();
        modelMap.addAttribute("authors", all);
        modelMap.addAttribute("books", one);
        return ("updateBooks");
    }

    @PostMapping("/bookUpdate")
    public String bookUpdate(@ModelAttribute Book book) {
        bookRepository.save(book);
        return ("redirect:/");
    }



}
