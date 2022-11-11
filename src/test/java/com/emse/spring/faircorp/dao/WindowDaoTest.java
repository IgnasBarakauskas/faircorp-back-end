package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.*;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class WindowDaoTest {
    @Autowired
    private WindowDao windowDao;
    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindAWindow() {
        Window window = windowDao.getReferenceById(-10L);
        Assertions.assertThat(window.getId()).isEqualTo(-10L);
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getRoom().getId()).isEqualTo(-10L);
        Assertions.assertThat(window.getWindowStatus()).isEqualTo(WindowStatus.CLOSED);
    }

    @Test
    public void shouldNotFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-10L);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindRoomOpenWindows() {
        List<Window> result = windowDao.findRoomOpenWindows(-9L);
        Assertions.assertThat(result).isNotEmpty();
    }

    @Test
    public void shouldDeleteWindowsInRoom() {
        Room room = roomDao.getReferenceById(-10L);
        List<Long> windowIds = room.getWindows().stream().map(Window::getId).collect(Collectors.toList());
        Assertions.assertThat(windowIds.size()).isEqualTo(2);
        windowDao.deleteByRoomId(-10L);
        List<Window> result = windowDao.findAllById(windowIds);
        Assertions.assertThat(result).isEmpty();

    }

    @Test
    public void ShouldCreateWindow() {
        Room room = roomDao.getReferenceById(-10l);
        Window newWindow = new Window();
        newWindow.setName("Test");
        newWindow.setRoom(room);
        newWindow.setWindowStatus(WindowStatus.OPEN);
        Window window = windowDao.save(newWindow);
        Assertions.assertThat(window.getName()).isEqualTo(newWindow.getName());
        Assertions.assertThat(window.getRoom()).isEqualTo(newWindow.getRoom());
        Assertions.assertThat(window.getWindowStatus()).isEqualTo(newWindow.getWindowStatus());
    }
}