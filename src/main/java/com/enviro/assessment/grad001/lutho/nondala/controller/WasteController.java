package com.enviro.assessment.grad001.lutho.nondala.controller;

import com.enviro.assessment.grad001.lutho.nondala.entity.Waste;
import com.enviro.assessment.grad001.lutho.nondala.service.WasteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("enviro/waste/")
public class WasteController {
    @Autowired
    private WasteService service;

    @PostMapping("create")
    public ResponseEntity<Waste> create(@Valid @RequestBody Waste waste){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.create(waste));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Waste> read(@PathVariable long id){
        try {
            if (id == 0 || String.valueOf(id) == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(this.service.read(id));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("update")
    public ResponseEntity<Waste> update(@Valid @RequestBody Waste waste){
        try {
            if (waste.getId() == 0 || String.valueOf(waste.getId()) == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            return ResponseEntity.status(HttpStatus.OK).body(this.service.update(waste));
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
    public ResponseEntity<List<Waste>> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.getAll());
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
