package com.enviro.assessment.grad001.lutho.nondala.controller;

import com.enviro.assessment.grad001.lutho.nondala.entity.Category;
import com.enviro.assessment.grad001.lutho.nondala.service.CategoryService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("enviro/category/")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("create")
    public ResponseEntity<Category> create(@Valid @RequestBody Category category){
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
    public ResponseEntity<Category> update(@Valid @RequestBody Category category){
        try {
            if (category.getId() == 0 || String.valueOf(category.getId()) == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(this.service.update(category));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        try {
            if (id == 0 || String.valueOf(id) == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            this.service.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Successful!");
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
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
