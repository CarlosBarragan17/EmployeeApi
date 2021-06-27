package io.leantech.test.services;

import io.leantech.test.daos.PositionDao;
import io.leantech.test.entities.Position;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PositionService {

    @Autowired
    private PositionDao positionDao;

    private final Logger logger = LoggerFactory.getLogger(PositionService.class);

    @Transactional(readOnly = true)
    public List<Position> getPositions() {
        logger.info("[Lean Tech]--- PositionService:getPositions ---");
        return positionDao.findAll();
    }
}
