package com.tw.pilotless_plane.models;

import java.util.ArrayList;
import java.util.List;

public class Plane {
    private  String id;
    private  List<Signal> signalList ;
    private  Boolean atFault;

    public Plane(){
        this.signalList=new ArrayList<Signal>();
        this.atFault=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Signal> getSignalList() {
        return signalList;
    }

    public void setSignalList(List<Signal> signalList) {
        this.signalList = signalList;
    }

    public Boolean getAtFault() {
        return atFault;
    }
    
    public boolean isAtFault() {
        return atFault;
    }

    public void setAtFault(boolean atFault) {
        this.atFault = atFault;
    }

    public Coordinate getCurrCoordinate() {
        if(signalList.size()<1 || this.atFault){
            return null;
        }else {
            return  signalList.get(signalList.size() - 1).calculateCurrCoordinate();
        }
    }

    public void addSignal(Signal signal) {
        signalList.add(signal);
    }
}
