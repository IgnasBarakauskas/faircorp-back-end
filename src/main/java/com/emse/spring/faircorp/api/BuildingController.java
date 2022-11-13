package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dto.BuildingDTO;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/building")
@Transactional
public class BuildingController {
    private final BuildingDao buildingDao;

    public BuildingController(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<BuildingDTO> findAll() {
        return buildingDao.findAll().stream().map(BuildingDTO::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public BuildingDTO findById(@PathVariable Long id) {
        return buildingDao.findById(id).map(BuildingDTO::new).orElse(null);
    }

    @PostMapping
    public BuildingDTO create(@RequestBody BuildingDTO dto) {
        Building building = null;
        if (dto.getId() == null) {
            building = buildingDao.save(new Building(dto.getName(), dto.getAmountOfFloors(), dto.getBuildingStatus()));
        } else {
            building = buildingDao.getReferenceById(dto.getId());
        }
        return new BuildingDTO(building);
    }

    @PutMapping(path = "/{id}/switch")
    public BuildingDTO switchStatus(@PathVariable Long id) {
        Building building = buildingDao.findById(id).orElseThrow(IllegalArgumentException::new);
        building.setBuildingStatus(building.getBuildingStatus() == BuildingStatus.LOCKED ? BuildingStatus.UNLOCKED : BuildingStatus.LOCKED);
        return new BuildingDTO(building);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        buildingDao.deleteById(id);
    }

}
