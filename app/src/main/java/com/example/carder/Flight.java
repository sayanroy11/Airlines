package com.example.carder;

import java.util.Comparator;

class Flight{
    private Airport from;
    private Airport to;
    private String name;
    private String id;
    private String time;//in 00:00 format
    private double cost;
    private String date;//int from 0-6
    private double duration;
    private int capacity = 30*6;
    private int totalPassenger = 0;
    private Integer[][] seatMatrix = new Integer[30][6];

    public Flight(Airport from, Airport to, String name, String id, double cost, String date, double duration, String time) {
        this.from = from;
        this.to = to;
        this.name = name;
        this.id = id;
        this.cost = cost;
        this.date = date;
        this.time = time;
        this.duration=duration;
        for(int i=0;i<30;i++){
            for(int j=0;j<6;j++){
                this.seatMatrix[i][j]=0;
            }
        }
    }

    public boolean emptyCap(){
        if(this.capacity == this.totalPassenger)
            return true;
        return false;
    }

    public int getCapacity(){
        return this.capacity;
    }
    public int totalPassenger(){
        return this.totalPassenger;
    }
    public void setTotal(int n){
        this.totalPassenger = n;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public void setSeatMatrix(Integer[][] seatMatrix) {
        this.seatMatrix = seatMatrix;
    }

    public String getId() {
        return id;
    }

    public double getCost() {
        return cost;
    }

    public Double getDuration() {
        return duration;
    }

    public Integer[][] getSeatMatrix() {
        return seatMatrix;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return this.date;
    }

    public static Comparator<Flight> TimeComparator=new Comparator<Flight>(){
        public int compare(Flight f1,Flight f2)
        {
            return (int)((f1.getTime()).compareTo(f2.getTime()));
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}