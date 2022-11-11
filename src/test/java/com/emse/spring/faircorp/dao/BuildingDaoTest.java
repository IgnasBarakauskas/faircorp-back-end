package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BuildingDaoTest {
    @Autowired
    private BuildingDao buildingDao;

    @Test
    public void ShouldFindABuilding() {
        {
            Building building = buildingDao.getReferenceById(-10L);
            Assertions.assertThat(building.getId()).isEqualTo(-10L);
            Assertions.assertThat(building.getName()).isEqualTo("Espace Fauriel");
            Assertions.assertThat(building.getAmountOfFloors()).isEqualTo(5);
            Assertions.assertThat(building.getRooms().size()).isEqualTo(2);
        }
    }

    @Test
    public void shouldFindAllBuilding() {
        List<Building> buildings = buildingDao.findAll();
        Assertions.assertThat(buildings.size()).isEqualTo(1);

    }

    @Test
    public void ShouldCreateBuilding() {
        Building newBuilding = new Building();
        newBuilding.setName("Test");
        newBuilding.setAmountOfFloors(3);
        Building building = buildingDao.save(newBuilding);
        Assertions.assertThat(newBuilding.getName()).isEqualTo(building.getName());
        Assertions.assertThat(newBuilding.getAmountOfFloors()).isEqualTo(building.getAmountOfFloors());
    }
}
