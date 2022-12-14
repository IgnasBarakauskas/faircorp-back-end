package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dto.HeaterDTO;
import com.emse.spring.faircorp.dto.RoomDTO;
import com.emse.spring.faircorp.dto.WindowDTO;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;
    private final BuildingDao buildingDao;

    public RoomController(RoomDao roomDao, BuildingDao buildingDao) {
        this.roomDao = roomDao;
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<RoomDTO> findAll() {
        return roomDao.findAll().stream().map(RoomDTO::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public RoomDTO findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDTO::new).orElse(null);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        roomDao.deleteById(id);
    }

    @PostMapping
    public RoomDTO create(@RequestBody RoomDTO dto) {
        Building building = buildingDao.getReferenceById(dto.getBuildingId());
        Room room = null;
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getFloor(), dto.getName(), dto.getCurrentTemperature(), dto.getTargetTemperature(), building));
        } else {
            room = roomDao.getReferenceById(dto.getId());
        }
        return new RoomDTO(room);
    }

    @PutMapping(path = "/{id}/switch-window")
    public List<WindowDTO> switchWindow(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Window> windows = room.getWindows();
        List<WindowDTO> result = new ArrayList<>();
        for (Window window : windows) {
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED : WindowStatus.OPEN);
            result.add(new WindowDTO(window));
        }
        return result;
    }

    @PutMapping(path = "/{id}/switch-heater")
    public List<HeaterDTO> switchHeater(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Heater> heaters = room.getHeaters();
        List<HeaterDTO> result = new ArrayList<>();
        for (Heater heater : heaters) {
            heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF : HeaterStatus.ON);
            result.add(new HeaterDTO(heater));
        }
        return result;
    }
}
