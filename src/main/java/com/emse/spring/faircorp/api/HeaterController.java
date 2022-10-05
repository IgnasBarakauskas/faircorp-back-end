package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.dto.HeaterDTO;
import com.emse.spring.faircorp.dto.WindowDTO;
import com.emse.spring.faircorp.model.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {
    private final HeaterDao heaterDao;
    private final RoomDao roomDao;
    public HeaterController(HeaterDao heaterDao, RoomDao roomDao) { // (4)
        this.heaterDao = heaterDao;
        this.roomDao = roomDao;
    }
    @GetMapping
    public List<HeaterDTO> findAll() {
        return heaterDao.findAll().stream().map(HeaterDTO::new).collect(Collectors.toList());  // (6)
    }

    @PostMapping
    public HeaterDTO create(@RequestBody HeaterDTO dto) {
        Room room = roomDao.getReferenceById(dto.getRoomId());
        Heater heater = null;
        if (dto.getId() == null) {
            heater = heaterDao.save(new Heater( dto.getName(), dto.getPower(), dto.getHeaterStatus(), room));
        }
        else {
            heater = heaterDao.getReferenceById(dto.getId());
            heater.setHeaterStatus(dto.getHeaterStatus());
        }
        return new HeaterDTO(heater);
    }
    @GetMapping(path = "/{id}")
    public HeaterDTO findById(@PathVariable Long id) {
        return heaterDao.findById(id).map(HeaterDTO::new).orElse(null);
    }

    @PutMapping(path = "/{id}/switch")
    public HeaterDTO switchStatus(@PathVariable Long id) {
        Heater heater = heaterDao.findById(id).orElseThrow(IllegalArgumentException::new);
        heater.setHeaterStatus(heater.getHeaterStatus() == HeaterStatus.ON ? HeaterStatus.OFF: HeaterStatus.ON);
        return new HeaterDTO(heater);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        heaterDao.deleteById(id);
    }
}
