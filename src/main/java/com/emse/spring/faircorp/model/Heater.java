package com.emse.spring.faircorp.model;

import javax.persistence.*;

@Entity
@Table(name = "Heater")
public class Heater {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String name;
    private Long power;
    @ManyToOne
    private Room room;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HeaterStatus heaterStatus;

    public Heater() {
    }

    public Heater(String name, Long power, HeaterStatus status, Room room) {
        this.heaterStatus = status;
        this.power = power;
        this.name = name;
        this.room = room;
    }

    public Heater(String name, HeaterStatus status, Room room) {
        this.heaterStatus = status;
        this.name = name;
        this.room = room;
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

    public Long getPower() {
        return this.power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    public Room getRoom() {
        return this.room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public HeaterStatus getHeaterStatus() {
        return this.heaterStatus;
    }

    public void setHeaterStatus(HeaterStatus heaterStatus) {
        this.heaterStatus = heaterStatus;
    }
}
