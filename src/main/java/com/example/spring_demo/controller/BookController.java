package com.example.spring_demo.controller;
import com.example.spring_demo.model.Author;
import com.example.spring_demo.model.Book;
import com.example.spring_demo.repository.AuthorRepository;
import com.example.spring_demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        List<Book> books = bookRepository.findAll();
        modelMap.addAttribute("books", books);
        return "allBooks";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/allBooks";
    }

    @GetMapping("/editBook")
    public String edit(@RequestParam("id") int id,Model model){
        Book book = bookRepository.getOne(id);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors",authors);
        model.addAttribute("book",book);
        return "editBook";
    }

    @PostMapping("/saveBook")
    public String add(@ModelAttribute Book book) {
        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
        String ctrlName = book.getId() > 0 ? "allBooks" : "/";
        bookRepository.save(book);
        return "redirect:"+ctrlName+"?msg"+msg;
    }







}
