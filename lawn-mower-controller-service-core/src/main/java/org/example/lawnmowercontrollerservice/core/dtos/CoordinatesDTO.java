package org.example.lawnmowercontrollerservice.core.dtos;

import lombok.Builder;
import lombok.Data;
import org.example.lawnmowercontrollerservice.core.enums.Direction;

@Data
@Builder
public class CoordinatesDTO {
    private AxisDTO axisDTO;
    private Direction direction;
}
