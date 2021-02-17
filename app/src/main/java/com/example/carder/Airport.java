package com.example.carder;


import java.util.ArrayList;

class Airport {
    private String AirportName;
    private String AirportId;
    private ArrayList<Flight> listOfArrival = new ArrayList<Flight>();
    private ArrayList<Flight> listOfDeparture = new ArrayList<Flight>();

    public Airport(String airportName, String airportId) {
        AirportName = airportName;
        AirportId = airportId;
    }

    public String getAirportName() {
        return AirportName;
    }

    public void setAirportName(String airportName) {
        AirportName = airportName;
    }

    public String getAirportId() {
        return AirportId;
    }

    public void setAirportId(String airportId) {
        AirportId = airportId;
    }

    public void addIncomingFlight(Flight f){
        listOfArrival.add(f);
    }

    public void addOutgoingFlight(Flight f){
        listOfDeparture.add(f);
    }

    public void delIncomingFlight(Flight f){
        for(int i = 0; i<listOfArrival.size();i++){
            if(listOfArrival.get(i).getId().equals(f.getId())){
                listOfArrival.remove(i);
            }
        }
    }
    public void delOutgoingFlight(Flight f){listOfDeparture.remove(f);
        for(int i = 0; i<listOfDeparture.size();i++){
            if(listOfDeparture.get(i).getId().equals(f.getId())){
                listOfDeparture.remove(i);
            }
        }
    }
}