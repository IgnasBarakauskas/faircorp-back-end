package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeaterDao extends JpaRepository<Heater, Long> {
    Heater getReferenceById(Long id);
    void deleteByRoomId(Long Id);
}
