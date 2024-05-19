package com.enviro.assessment.grad001.lutho.nondala.controller;

import com.enviro.assessment.grad001.lutho.nondala.entity.Category;
import com.enviro.assessment.grad001.lutho.nondala.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enviro/category/")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("create")
    public ResponseEntity<Category> create(@RequestBody Category category){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.create(category));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Category> read(@PathVariable long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.read(id));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("update")
    public ResponseEntity<Category> update(@RequestBody Category category){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.update(category));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable long id){
        try {
            this.service.delete(id);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Category>> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
