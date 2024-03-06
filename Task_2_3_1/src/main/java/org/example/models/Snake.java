package org.example.models;

import org.example.types.Direction;

import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakePart> body;
    private boolean isAlive;
    private Direction direction;

    private Field field;

    public Snake(int startPositionX, int startPositionY, Field field) {
        body = new LinkedList<>();
        body.addFirst(new SnakePart(startPositionX, startPositionY));
        this.field = field;
        isAlive = true;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    public void move(int xChange, int yChange) {

    }

    public void checkFieldCollision() {
        var xPos = this.body.getFirst().getPositionX();
        var yPos = this.body.getFirst().getPositionY();
        if (xPos < 0 || xPos >= field.getXSize() ||
                yPos < 0 || yPos >= field.getYSize()) {
            isAlive = false;
        }
    }

    public void checkBodyCollision() {
        var head = this.body.getFirst();
        for (var part : this.body) {
            if (part != head) {
                if (head.getPositionX() == part.getPositionX() &&
                head.getPositionY() == part.getPositionY()) {
                    isAlive = false;
                    break;
                }
            }
        }
    }
}