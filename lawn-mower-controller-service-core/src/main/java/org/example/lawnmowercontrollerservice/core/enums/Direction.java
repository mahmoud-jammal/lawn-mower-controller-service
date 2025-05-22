package org.example.lawnmowercontrollerservice.core.enums;

import lombok.Getter;
import org.example.lawnmowercontrollerservice.ports.exceptions.WrongDirectionCharacterException;

@Getter
public enum Direction {
    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0);

    private final int dx;
    private final int dy;

    private static final Direction[] VALUES = values();
    private static final int SIZE = VALUES.length;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction fromString(String directionValue) {
        return switch (directionValue.toUpperCase()) {
            case "N" -> N;
            case "E" -> E;
            case "S" -> S;
            case "W" -> W;
            default -> throw new WrongDirectionCharacterException();
        };
    }

    public Direction next() {
        return VALUES[(this.ordinal() + 1) % SIZE];
    }

    public Direction previous() {
        return VALUES[(this.ordinal() - 1 + SIZE) % SIZE];
    }
}
