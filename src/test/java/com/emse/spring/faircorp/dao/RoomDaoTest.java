package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;

    @Test
    public void shouldFindARoom() {
        Room room = roomDao.getReferenceById(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
        Assertions.assertThat(room.getCurrentTemperature()).isEqualTo(22.3);
    }
    @Test
    public void ShouldFindARoomByName(){
        List<Room> result = roomDao.findRoomByName("Room1");
        Assertions.assertThat(result).isNotEmpty();
    }
    @Test
    public void ShouldNotFindARoomByName(){
        List<Room> result = roomDao.findRoomByName("NOTARoom1");
        Assertions.assertThat(result).isEmpty();
    }
}