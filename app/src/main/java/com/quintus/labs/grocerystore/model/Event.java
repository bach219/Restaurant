package com.quintus.labs.grocerystore.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.quintus.labs.grocerystore.util.ColorUtils;

import java.util.Calendar;

/**
 * Created by Hugo Andrade on 25/03/2018.
 */

public class Event implements Parcelable {

    private String mID;
    private String mTitle ;
    private Calendar mDate;
    private int mColor = ColorUtils.mColors[0];
    private String checkin;
    private String checkout;
    private int finish;
    private boolean isCompleted;

    public Event(String id, String title, Calendar date, int color, boolean isCompleted) {
        mID = id;
        mTitle = title;
        mDate = date;
        mColor = color;
        this.isCompleted = isCompleted;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
        if(finish == 0)
            this.isCompleted = false;
        this.isCompleted = true;
    }

    public String getID() {
        return mID;
    }

    public String getTitle() {
        return mTitle;
    }

    public Calendar getDate() {
        return mDate;
    }

    public int getColor() {
        return mColor;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setID(String mID) {
        this.mID = mID;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDate(Calendar mDate) {
        this.mDate = mDate;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    protected Event(Parcel in) {
        mID = in.readString();
        mTitle = in.readString();
        mColor = in.readInt();
        mDate = (Calendar) in.readSerializable();
        isCompleted = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mID);
        dest.writeString(mTitle);
        dest.writeInt(mColor);
        dest.writeSerializable(mDate);
        dest.writeByte((byte) (isCompleted ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
