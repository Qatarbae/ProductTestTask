package dev.task.producttesttask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @GetMapping()
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok().body("hola");
    }
}
