package com.enviro.assessment.grad001.lutho.nondala.controller;

import com.enviro.assessment.grad001.lutho.nondala.entity.Category;
import com.enviro.assessment.grad001.lutho.nondala.entity.Waste;
import com.enviro.assessment.grad001.lutho.nondala.service.CategoryService;
import com.enviro.assessment.grad001.lutho.nondala.service.WasteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(CategoryController.class)
@Slf4j
class CategoryControllerTest {
    @MockBean
    private CategoryService categoryService;
    @Autowired
    private MockMvc mockMvc;
    private Category category;

    @BeforeEach
    void setUp(){

    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() throws Exception {
        this.category = new Category.Builder().setCategory("Plastic").build();
        Mockito.when(this.categoryService.create(this.category)).thenReturn(this.category);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/enviro/category/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.category)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void read() throws Exception {
        this.category = new Category.Builder().setCategory("Plastic").build();

        when(this.categoryService.read(1)).thenReturn(this.category);

        mockMvc.perform(MockMvcRequestBuilders.get("/enviro/category/read/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() throws Exception {
        this.category = new Category.Builder().setCategory("Glass").build();
        Mockito.when(this.categoryService.update(this.category)).thenReturn(this.category);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/enviro/category/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(this.category)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void delete() throws Exception {
        this.category = new Category.Builder().setCategory("Plastic").build();

        Mockito.doNothing().when(this.categoryService).delete(Mockito.any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/enviro/category/delete/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() throws Exception {
        this.category = new Category.Builder().setCategory("Plastic").build();
        List<Category> categories = Arrays.asList(this.category);

        when(this.categoryService.getAll()).thenReturn(categories);

        mockMvc.perform(MockMvcRequestBuilders.get("/enviro/category/getAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}