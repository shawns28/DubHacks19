package com.example.binnahacks19;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Item {
    private String name;
    private int quantity;
    private String notes;
    private Date expirationDate;

    public Item(String name, int quantity, String notes, Date expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.notes = notes;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes() {
        this.notes = notes;
    }

    public Date getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return(this.name + "\t" + Integer.toString(this.quantity));
    }

    public String serialized() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(expirationDate);
        return (this.name + "\t" + Integer.toString(this.quantity) + "\t" + notes + "\t" + strDate);
    }
}
