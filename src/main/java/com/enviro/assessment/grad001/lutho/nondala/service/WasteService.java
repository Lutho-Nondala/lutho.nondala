package com.enviro.assessment.grad001.lutho.nondala.service;

import com.enviro.assessment.grad001.lutho.nondala.entity.Category;
import com.enviro.assessment.grad001.lutho.nondala.entity.Waste;
import com.enviro.assessment.grad001.lutho.nondala.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WasteService {
    @Autowired
    private WasteRepository wasteRepository;
    @Autowired
    private CategoryService categoryService;

    public Waste create(Waste waste){
        return wasteRepository.save(waste);
    }

    public Waste read(long id){
        return wasteRepository.findById(id).get();
    }

    public Waste update(Waste waste){
        Waste updateWaste = new Waste.Builder().copy(this.read(waste.getId()))
                .setDescription(waste.getDescription())
                .setDisposalGuidelines(waste.getDisposalGuidelines())
                .setRecyclingTips(waste.getRecyclingTips())
                .build();
        return wasteRepository.save(updateWaste);
    }

    public void delete(long id){
        wasteRepository.deleteById(id);
    }

    public List<Waste> getAll(){
        return wasteRepository.findAll();
    }

    public Waste updateCategory(long wasteId, long categoryId){
        Waste waste = this.read(wasteId);
        Waste updateWaste = new Waste.Builder()
                .copy(waste)
                .setCategory(this.categoryService.read(categoryId))
                .build();

        return wasteRepository.save(updateWaste);
    }
}
