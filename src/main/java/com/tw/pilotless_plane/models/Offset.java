package com.tw.pilotless_plane.models;

public class Offset {
    private int offsetX;
    private int offsetY;
    private int offsetZ;

    public Offset(int offsetX, int offsetY, int offsetZ){
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.offsetZ=offsetZ;
    }

    @Override
    public String toString() {
        return offsetX + " " + offsetY + " " + offsetZ;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        offsetY = offsetY;
    }

    public int getOffsetZ() {
        return offsetZ;
    }

    public void setOffsetZ(int offsetZ) {
        offsetZ = offsetZ;
    }
}
