package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;

public class BuildingDTO {
    private Long id;
    private String name;
    private Integer amountOfFloors;

    public BuildingDTO() {
    }

    public BuildingDTO(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.amountOfFloors = building.getAmountOfFloors();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfFloors() {
        return amountOfFloors;
    }

    public void setAmountOfFloors(Integer amountOfFloors) {
        this.amountOfFloors = amountOfFloors;
    }
}
