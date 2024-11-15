package com.example.notebook.notebook;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteBookController {
    @RequestMapping("/notebook")
    public String test() {
        System.out.println("hello notebook");
        return "test";
    }
}
