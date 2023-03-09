package com.codecool.bookclub.forum.controller;

import com.codecool.bookclub.forum.model.Theme;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ThemeController {

    @GetMapping("/books/{book_id}/themes")
    public List<Theme> getThemesForBook(@PathVariable("book_id") long id){
        return new ArrayList<>();
    }

    @PostMapping("/books/{book_id}/themes")
    public boolean createTheme(@PathVariable("book_id") long id, @RequestBody Theme theme){
        return false;
    }

    @PutMapping("/books/{book_id}/themes")
    public boolean updateTheme(@PathVariable("book_id") long id, @RequestBody Theme updatedTheme) {
        return false;
    }

    @DeleteMapping("/books/{book_id}/themes")
    public boolean deleteTheme(@PathVariable("book_id") long id, @RequestBody Theme theme){
        return false;
    }



}
