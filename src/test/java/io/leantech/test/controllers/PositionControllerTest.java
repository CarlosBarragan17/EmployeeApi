package io.leantech.test.controllers;

import io.leantech.test.entities.Position;
import io.leantech.test.services.PositionService;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PositionControllerTest {

    @InjectMocks
    private PositionController positionController;

    @Mock
    private PositionService positionService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getPositionTest() {
        List<Position> positions = Collections.singletonList(new Position());
        Mockito.when(positionService.getPositions()).thenReturn(positions);
        ResponseEntity<?> position = positionController.getPosition();

        Assert.assertEquals(position.getStatusCode(), HttpStatus.OK);
    }
}
