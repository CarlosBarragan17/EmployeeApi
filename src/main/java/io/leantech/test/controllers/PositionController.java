package io.leantech.test.controllers;

import io.leantech.test.entities.Position;
import io.leantech.test.services.PositionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    @Autowired
    private PositionService positionService;

    @GetMapping(value = "/positions")
    public ResponseEntity<?> getPosition() {
        List<Position> positions = positionService.getPositions();
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }
}
