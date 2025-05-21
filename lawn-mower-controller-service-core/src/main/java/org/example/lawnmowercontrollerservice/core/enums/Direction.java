package org.example.lawnmowercontrollerservice.core.enums;

import lombok.Getter;

@Getter
public enum Direction {
    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0);

    private final int dx;
    private final int dy;

    private static final Direction[] DIRECTIONS = values();
    private static final int SIZE = DIRECTIONS.length;

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
            default -> throw new RuntimeException();
        };
    }

    public Direction right() {
        return DIRECTIONS[(this.ordinal() + 1) % SIZE];
    }

    public Direction left() {
        return DIRECTIONS[(this.ordinal() - 1 + SIZE) % SIZE];
    }
}
