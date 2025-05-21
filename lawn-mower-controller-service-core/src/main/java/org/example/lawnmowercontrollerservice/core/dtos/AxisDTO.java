package org.example.lawnmowercontrollerservice.core.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AxisDTO {
    private int x;
    private int y;
}
