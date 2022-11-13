package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WindowDao extends JpaRepository<Window, Long>, WindowDaoCustom {
    Window getReferenceById(Long id);

    void deleteByRoomId(Long Id);
}
