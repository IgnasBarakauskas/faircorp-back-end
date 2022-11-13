package com.emse.spring.faircorp.dto;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.BuildingStatus;

public class BuildingDTO {
    private Long id;
    private String name;
    private Integer amountOfFloors;
    private BuildingStatus buildingStatus;

    public BuildingDTO() {
    }

    public BuildingDTO(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        this.amountOfFloors = building.getAmountOfFloors();
        this.buildingStatus = building.getBuildingStatus();
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

    public BuildingStatus getBuildingStatus() {
        return this.buildingStatus;
    }

    public void setBuildingStatus(BuildingStatus status) {
        this.buildingStatus = status;
    }
}
