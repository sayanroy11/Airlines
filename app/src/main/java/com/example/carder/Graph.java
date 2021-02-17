package com.example.carder;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;

class Graph implements Parcelable {

    static private ArrayList<Flight> listOfFlight = new ArrayList<Flight>();
    static private ArrayList<Airport> listOfAirport = new ArrayList<Airport>();

    public Graph(){
        //
    }

    protected Graph(Parcel in) {
//        this.listOfAirport
    }

    public static final Creator<Graph> CREATOR = new Creator<Graph>() {
        @Override
        public Graph createFromParcel(Parcel in) {
            return new Graph(in);
        }

        @Override
        public Graph[] newArray(int size) {
            return new Graph[size];
        }
    };

    public Airport searchAirport(String name){
        for(Airport a: listOfAirport){
            if(a.getAirportName().equals(name))
                return a;
        }
        return null;
    }

    public void addAirport(Airport a){
        if(a!=null) {
            if (searchAirport(a.getAirportName()) == null) {
                listOfAirport.add(a);
            }
        }
    }

    public void addFlight(Flight f){
        if(searchFlight(f.getId()) == null){
            f.getFrom().addOutgoingFlight(f);
            f.getTo().addIncomingFlight(f);
            listOfFlight.add(f);
        }
    }

    public void removeAirport(String id){
        for(int i=0;i<listOfAirport.size();i++){
            Airport a=listOfAirport.get(i);
            if(a.getAirportId().equals(id)){
                for(Flight f : listOfFlight){
                    if(f.getTo().getAirportId().equals(id)){
                        f.getTo().delIncomingFlight(f);
                        listOfFlight.remove(f);
                    }
                    if(f.getFrom().getAirportId().equals(id)){
                        f.getFrom().delOutgoingFlight(f);
                        listOfFlight.remove(f);
                    }
                    listOfAirport.remove(i);
                }
            }
        }
    }


    public void removeFlight(String id){
        for (int i=0;i<=listOfFlight.size();i++){
            Flight f=listOfFlight.get(i);
            if(f.getId().equals(id)){
                f.getTo().delIncomingFlight(f);
                f.getFrom().delOutgoingFlight(f);
                listOfFlight.remove(i);
            }
        }
    }

    public void modifyAirportName(String id,String name) {
        for(Airport ad: listOfAirport){
            if(ad.getAirportId().equals(id)){
                ad.setAirportName(name);
            }
        }
    }

    public Flight searchFlight(String flightID){
        for(Flight f : listOfFlight ){
            if(f.getId().equals(flightID))
                return f;
        }
        return null;
    }

    public Flight searchFlightbyName (String flightName){
        for(Flight f : listOfFlight ){
            if(f.getName().equals(flightName))
                return f;
        }
        return null;
    }

    public void modifyFlight(String flightId,Airport from, Airport to,
                             String name, double cost, String date,
                             double duration,String time){
        Flight flight = searchFlight(flightId);
        flight.setCost(cost);
        flight.setDate(date);
        flight.setDuration(duration);
        flight.setFrom(from);
        flight.setTo(to);
        flight.setName(name);
        flight.setTime(time);
    }

    public ArrayList<Flight> showFlights(String a,String b){
        ArrayList<Flight> arr = new ArrayList<>();

        for(Flight f: listOfFlight){
            if((f.getFrom().getAirportName().equals(a))&&(f.getTo().getAirportName().equals(b))){
                arr.add(f);
            }
            Collections.sort(arr,Flight.TimeComparator);
        }
        return arr;
    }

    public ArrayList<Flight> showFlights(String a,String b,String dat){
        ArrayList<Flight> arr = new ArrayList<>();

        for(Flight f: listOfFlight){
            if((f.getFrom().getAirportName().equals(a))&&(f.getTo().getAirportName().equals(b))&&f.getFrom().getAirportName().equals(dat)){
                arr.add(f);
            }
        }
        return arr;
    }

    public ArrayList<Airport> airportlist(){
        return listOfAirport;
    }

    public ArrayList<Flight> Flightlist(){
        return listOfFlight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
