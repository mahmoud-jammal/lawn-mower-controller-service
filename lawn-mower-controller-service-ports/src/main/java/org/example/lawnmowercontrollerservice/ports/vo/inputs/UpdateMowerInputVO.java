package org.example.lawnmowercontrollerservice.ports.vo.inputs;

import java.util.List;

public record UpdateMowerInputVO(AxisInputVO upperRightAxisInputVO, List<MowerInputVo> mowerInputVos) {

    public static record MowerInputVo(CoordinatesInputVO coordinatesInputVO, String instructions) {
        public static record CoordinatesInputVO(AxisInputVO axisInputVO, String direction) {
        }
    }

    public static record AxisInputVO(int x, int y) {
    }
}
