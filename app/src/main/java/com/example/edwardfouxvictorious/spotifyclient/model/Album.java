package com.example.edwardfouxvictorious.spotifyclient.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Album implements Parcelable {
    private List<Image> images;
    private String name;

    public List<Image> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Album{" +
                "images=" + images +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.images);
        dest.writeString(this.name);
    }

    public Album() {
    }

    protected Album(Parcel in) {
        this.images = new ArrayList<Image>();
        in.readList(this.images, Image.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel source) {
            return new Album(source);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };
}
