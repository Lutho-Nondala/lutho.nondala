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
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@WebMvcTest(WasteController.class)
@Slf4j
class WasteControllerTest {
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private WasteService wasteService;
    @Autowired
    private MockMvc mockMvc;
    private Category category;
    private Waste waste;

    @BeforeEach
    void setUp(){


    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() throws Exception {

        this.category = new Category.Builder().setCategory("Plastic").build();
        this.waste = new Waste.Builder()
                .setDescription("Plastic Bottle")
                .setDisposalGuidelines("Put with other plastic items")
                .setRecyclingTips("Recycle into new materials")
                .build();

        when(this.categoryService.create(this.category)).thenReturn(this.category);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/enviro/category/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.category))
        );

        this.category.setId(1);
        this.waste.setCategory(this.category);

        when(this.wasteService.create(this.waste)).thenReturn(this.waste);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/enviro/waste/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(this.waste))
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void read() throws Exception {
        this.waste = new Waste.Builder()
                .setDescription("Plastic Bottle")
                .setDisposalGuidelines("Put with other plastic items")
                .setRecyclingTips("Recycle into new materials")
                .build();
        when(this.wasteService.read(1)).thenReturn(this.waste);

        mockMvc.perform(MockMvcRequestBuilders.get("/enviro/waste/read/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() throws Exception {
        this.waste = new Waste.Builder()
                .setDescription("Glass Bottle")
                .setDisposalGuidelines("Put with other glass items")
                .setRecyclingTips("Recycle into new materials")
                .build();
        Mockito.when(this.wasteService.update(this.waste)).thenReturn(this.waste);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/enviro/waste/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(this.category)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() throws Exception {
        this.waste = new Waste.Builder()
                .setDescription("Plastic Bottle")
                .setDisposalGuidelines("Put with other plastic items")
                .setRecyclingTips("Recycle into new materials")
                .build();
        List<Waste> wastes = Arrays.asList(this.waste);

        when(this.wasteService.getAll()).thenReturn(wastes);

        mockMvc.perform(MockMvcRequestBuilders.get("/enviro/waste/getAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void delete() throws Exception {
        this.waste = new Waste.Builder()
                .setDescription("Plastic Bottle")
                .setDisposalGuidelines("Put with other plastic items")
                .setRecyclingTips("Recycle into new materials")
                .build();

        Mockito.doNothing().when(this.wasteService).delete(Mockito.any(Long.class));

        mockMvc.perform(MockMvcRequestBuilders.delete("/enviro/waste/delete/{id}", 1).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}