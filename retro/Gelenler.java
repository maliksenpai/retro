
package com.example.retro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gelenler {

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

}
