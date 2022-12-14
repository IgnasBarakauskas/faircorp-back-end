package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WindowDaoCustomImpl implements WindowDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "SELECT w FROM Window w WHERE w.room.id = :id AND w.windowStatus = :status";
        return em.createQuery(jpql, Window.class).setParameter("id", id).setParameter("status",
                WindowStatus.OPEN).getResultList();
    }


}
