package ru.innotech.javapro.hw1.models;

public class Box {
    private int length;
    private int height;
    private int depth;

    public Box(int length, int height, int depth) {
        this.length = length;
        this.height = height;
        this.depth = depth;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int volume() {
        return length * height * depth;
    }

    public int square() {
        return 2*((length * height)+(length*depth)+(height*depth));
    }

    public int perimeter() {
        return 4*(length + height + depth);
    }
}
