package com.example.edwardfouxvictorious.spotifyclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Albums implements Parcelable {
    private int offset;
    private int total;
    private String next;
    private List<Album> items;

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }

    public String getNext() {
        return next;
    }

    public List<Album> getItems() {
        return items;
    }

    public void setItems(List<Album> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AlbumSearchResponse{" +
                "offset=" + offset +
                ", total=" + total +
                ", next='" + next + '\'' +
                ", items=" + items +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.offset);
        dest.writeInt(this.total);
        dest.writeString(this.next);
        dest.writeList(this.items);
    }

    public Albums() {
    }

    protected Albums(Parcel in) {
        this.offset = in.readInt();
        this.total = in.readInt();
        this.next = in.readString();
        this.items = new ArrayList<Album>();
        in.readList(this.items, Album.class.getClassLoader());
    }

    public static final Parcelable.Creator<Albums> CREATOR = new Parcelable.Creator<Albums>() {
        @Override
        public Albums createFromParcel(Parcel source) {
            return new Albums(source);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };
}
