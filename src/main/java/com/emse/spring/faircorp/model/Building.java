package com.emse.spring.faircorp.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Building")
public class Building {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer amountOfFloors;
    @OneToMany(mappedBy = "building", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Room> rooms;
    @Enumerated(EnumType.STRING)
    private BuildingStatus buildingStatus;

    public Building() {
    }


    public Building(String name, Integer amountOfFloors, BuildingStatus status) {
        this.name = name;
        this.amountOfFloors = amountOfFloors;
        this.buildingStatus = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmountOfFloors() {
        return this.amountOfFloors;
    }

    public void setAmountOfFloors(Integer amountOfFloors) {
        this.amountOfFloors = amountOfFloors;
    }

    public Set<Room> getRooms() {
        return this.rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public BuildingStatus getBuildingStatus() {
        return this.buildingStatus;
    }

    public void setBuildingStatus(BuildingStatus status) {
        this.buildingStatus = status;
    }
}
