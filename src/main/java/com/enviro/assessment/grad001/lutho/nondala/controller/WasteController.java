package com.enviro.assessment.grad001.lutho.nondala.controller;

import com.enviro.assessment.grad001.lutho.nondala.entity.Waste;
import com.enviro.assessment.grad001.lutho.nondala.service.WasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enviro/waste/")
public class WasteController {
    @Autowired
    private WasteService service;

    @PostMapping(value = "create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Waste> create(@RequestPart("Waste") Waste waste, @RequestPart("Category") long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.create(waste, id));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Waste> read(@PathVariable long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.read(id));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PutMapping("update")
    public ResponseEntity<Waste> update(@RequestBody Waste waste){
        try {
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

    @PutMapping(value = "updateCategory", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Waste> updateCategory(@RequestPart("Waste ID") long wasteId, @RequestPart("Category ID") long categoryId){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(this.service.updateCategory(wasteId, categoryId));
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
