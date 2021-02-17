package com.example.carder;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int mData;
    static String username="";
    static String password="";
    static String history="";
    static boolean adminFlag=false;

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void addHistory(String history) {
        this.history += history;
    }


    public User()
    {
        //
    }
    String getUsername(){
        return this.username;
    }
    String getPassword(){
        return this.password;
    }

    /* everything below here is for implementing Parcelable */

    // 99.9% of the time you can just ignore this
    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private User(Parcel in) {
        mData = in.readInt();
    }
}