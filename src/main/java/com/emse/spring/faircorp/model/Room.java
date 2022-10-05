package com.emse.spring.faircorp.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Room")
public class Room {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private Integer floor;
    @Column(nullable = false)
    private String name;
    private Double currentTemperature;
    private Double targetTemperature;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Heater> heaters;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Window> windows;

    public Room() {

    }

    public Room(Integer floor, String name,Set<Window> windows, Set<Heater> heaters) {
        this.floor = floor;
        this.name = name;
        this.windows = windows;
        this.heaters = heaters;
    }

    public Room(Integer floor, String name, Double currentTemperature, Double targetTemperature, Set<Window> windows, Set<Heater> heaters) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.windows = windows;
        this.heaters = heaters;
    }
    public Room(Integer floor, String name, Double currentTemperature, Double targetTemperature) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return this.floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCurrentTemperature() {
        return this.currentTemperature;
    }

    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Double getTargetTemperature() {
        return this.targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.currentTemperature = targetTemperature;
    }

    public Set<Heater> getHeaters() {
        return this.heaters;
    }

    public void setHeaters(Set<Heater> heaters) {
        this.heaters = heaters;
    }

    public Set<Window> getWindows() {
        return this.windows;
    }

    public void setWindows(Set<Window> windows) {
        this.windows = windows;
    }
}
