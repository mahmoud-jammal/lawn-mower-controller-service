package org.example.lawnmowercontrollerservice.core.models;

import lombok.Builder;
import lombok.Data;
import org.example.lawnmowercontrollerservice.core.dtos.AxisDTO;
import org.example.lawnmowercontrollerservice.core.dtos.CoordinatesDTO;
import org.example.lawnmowercontrollerservice.core.enums.Direction;

@Data
@Builder
public class MowerModel {

    private CoordinatesDTO coordinatesDTO;
    private String instructions;

    private static final String UPDATED_COORDINATES_RESPONSE_FORMAT = "%d %d %s";

    public String executeInstructions(AxisDTO upperRightAxis) {
        for (char c : this.instructions.toCharArray()) {
            switch (c) {
                case 'L' -> this.coordinatesDTO.setDirection(this.coordinatesDTO.getDirection().left());
                case 'R' -> this.coordinatesDTO.setDirection(this.coordinatesDTO.getDirection().right());
                case 'F' -> moveForward(upperRightAxis);
                default -> throw new RuntimeException();
            }
        }
        return getCoordinatesAsString();
    }

    private String getCoordinatesAsString() {
        AxisDTO axisDTO = this.coordinatesDTO.getAxisDTO();
        return String.format(UPDATED_COORDINATES_RESPONSE_FORMAT, axisDTO.getX(), axisDTO.getY(),
                             this.coordinatesDTO.getDirection());
    }

    private void moveForward(AxisDTO upperRight) {
        AxisDTO currentAxis = this.coordinatesDTO.getAxisDTO();
        Direction currentDirection = this.coordinatesDTO.getDirection();
        int updatedX = currentAxis.getX() + currentDirection.getDx();
        int updatedY = currentAxis.getY() + currentDirection.getDy();

        if (updatedX > -1 || updatedX < upperRight.getX() + 1 || updatedY > -1 || updatedY < upperRight.getY() + 1) {
            this.coordinatesDTO.setAxisDTO(AxisDTO.builder()
                                                  .x(updatedX)
                                                  .y(updatedY)
                                                  .build());
        }
    }
}
