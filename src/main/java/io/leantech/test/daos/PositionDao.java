package io.leantech.test.daos;

import io.leantech.test.entities.Position;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionDao extends JpaRepository<Position, Long> {
    List<Position> findByName(String name);
}
