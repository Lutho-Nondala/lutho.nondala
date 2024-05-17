package com.enviro.assessment.grad001.lutho.nondala.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class Waste {
    @Id
    @GeneratedValue
    private long id;
    private String description;
    private String disposalGuidelines;
    private String recyclingTips;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    public Waste(){}

    public Waste(Builder builder){
        this.id = builder.id;
        this.description = builder.description;
        this.category = builder.category;
        this.disposalGuidelines = builder.disposalGuidelines;
        this.recyclingTips = builder.recyclingTips;
    }

    public static class Builder{
        private long id;
        private String description;
        private Category category;
        private String disposalGuidelines;
        private String recyclingTips;

        public Builder setId(long id){
            this.id = id;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setCategory(Category category){
            this.category = category;
            return this;
        }

        public Builder setDisposalGuidelines(String disposalGuidelines){
            this.disposalGuidelines = disposalGuidelines;
            return this;
        }

        public Builder setRecyclingTips(String recyclingTips){
            this.recyclingTips = recyclingTips;
            return this;
        }

        public Builder copy(Waste waste){
            this.id = waste.id;
            this.description = waste.description;
            this.category = waste.category;
            this.disposalGuidelines = waste.disposalGuidelines;
            this.recyclingTips = waste.recyclingTips;
            return this;
        }

        public Waste build(){
            return new Waste(this);
        }
    }
}
