package com.tw.pilotless_plane.models;


public class Signal {
    private String planeId;
    private Coordinate preCoordinate;
    private Offset offset;
    private Boolean invalid;


    public Signal(){
        this.invalid = false;
    }

    public Boolean isInvalid(){
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public String getPlaneId() {
        return planeId;
    }

    public void setPlaneId(String planeId) {
        this.planeId = planeId;
    }

    public Coordinate getPreCoordinate() {
        return preCoordinate;
    }

    public void setPreCoordinate(Coordinate preCoordinate) {
        this.preCoordinate = preCoordinate;
    }


    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public Coordinate calculateCurrCoordinate() {
        if(this.invalid ||this.offset == null){
            return this.preCoordinate;
        }else {
            return new Coordinate(this.preCoordinate.getX()+this.offset.getOffsetX(),
                    this.preCoordinate.getY()+this.offset.getOffsetY(),this.preCoordinate.getZ()+ this.offset.getOffsetZ());
        }
    }
}

