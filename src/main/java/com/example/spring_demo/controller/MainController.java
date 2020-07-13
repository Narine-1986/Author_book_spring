package com.example.spring_demo.controller;

import com.example.spring_demo.model.Author;
import com.example.spring_demo.model.Book;
import com.example.spring_demo.repository.AuthorRepository;
import com.example.spring_demo.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("${file.upload.dir}")
    private String uploadDir;


    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/")
    public String homePage(Model modelMap, @RequestParam(name = "msg", required = false) String msg) {
        List<Author> authors = authorRepository.findAll();
        List<Book> books = bookRepository.findAll();
        modelMap.addAttribute("authors", authors);
        modelMap.addAttribute("books", books);
        modelMap.addAttribute("msg", msg);
        return "home";
    }

    @PostMapping("/addAuthor")
    public String addUser(@ModelAttribute Author author, @RequestParam("image") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(uploadDir, name);
        file.transferTo(image);
        author.setProfilePic(name);
        String msg = author.getId() > 0 ? "Author was updated" : "Author was added";
        authorRepository.save(author);
        return "redirect:/?msg="+msg;
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImage(@RequestParam("name") String imageName) throws IOException {
        InputStream in = new FileInputStream(uploadDir + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }

//    @PostMapping("/saveBook")
//    public String add(@ModelAttribute Book book) {
//        String msg = book.getId() > 0 ? "Book was updated" : "Book was added";
//        bookRepository.save(book);
//        return "redirect:/?msg=" + msg;
//    }





}