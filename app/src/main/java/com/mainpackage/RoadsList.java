package com.mainpackage;

public class RoadsList {

    private String startLat, startLon, endLat, endLon;
    private int roadQuality;

    public RoadsList(){

    }

    public RoadsList(String startLat, String startLon, String endLat, String endLon, int roadQuality) {
        this.startLat = startLat;
        this.startLon = startLon;
        this.endLat = endLat;
        this.endLon = endLon;
        this.roadQuality = roadQuality;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartLon() {
        return startLon;
    }

    public void setStartLon(String startLon) {
        this.startLon = startLon;
    }

    public String getEndLat() {
        return endLat;
    }

    public void setEndLat(String endLat) {
        this.endLat = endLat;
    }

    public String getEndLon() {
        return endLon;
    }

    public void setEndLon(String endLon) {
        this.endLon = endLon;
    }

    public int getRoadQuality() {
        return roadQuality;
    }

    public void setRoadQuality(int roadQuality) {
        this.roadQuality = roadQuality;
    }

    public void print(){
        System.out.println("Starting Latitude: " + startLat +
                "\n Starting Longitude: " + startLon +
                "\n End Latitude: " + endLat +
                "\n End Longitude: " + endLon +
                "\n Road Quality: " + String.valueOf(roadQuality));
    }
}
