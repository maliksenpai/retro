package com.example.retro;

public class Model {
    String isim,kisi,rank;

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getKisi() {
        return kisi;
    }

    public void setKisi(String kisi) {
        this.kisi = kisi;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public Model(String isim,String kisi,String rank){
        this.isim=isim;
        this.kisi=kisi;
        this.rank=rank;
    }
}
