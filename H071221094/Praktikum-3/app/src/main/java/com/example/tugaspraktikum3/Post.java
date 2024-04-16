package com.example.tugaspraktikum3;

import android.os.Parcel;
import android.os.Parcelable;



public class Post implements Parcelable {

    private Integer profil;
    private Integer fotopost;
    private String namaAkun;
    private String deskrip;
    private Integer storypost;
    private Integer folower;
    private Integer folowing;

    public Post(Integer profil, Integer fotopost, String namaAkun, String deskrip, Integer storypost, Integer folower, Integer folowing) {
        this.profil = profil;
        this.fotopost = fotopost;
        this.namaAkun = namaAkun;
        this.deskrip = deskrip;
        this.storypost = storypost;
        this.folower = folower;
        this.folowing = folowing;
    }

    protected Post(Parcel in) {
        if (in != null) {
            this.profil = in.readInt();
            this.fotopost = in.readInt();
            this.namaAkun = in.readString();
            this.deskrip = in.readString();
            this.storypost = in.readInt();
            this.folower = in.readInt();
            this.folowing = in.readInt();
        }
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public Integer getProfil() {
        return profil;
    }

    public Integer getFotopost() {
        return fotopost;
    }

    public String getNamaAkun() {
        return namaAkun;
    }

    public String getDeskrip() {
        return deskrip;
    }

    public Integer getStorypost() {
        return storypost;
    }

    public Integer getFolower() {
        return folower;
    }
    public Integer getFolowing(){
        return folowing;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(profil);
        dest.writeInt(fotopost);
        dest.writeString(namaAkun);
        dest.writeString(deskrip);
        dest.writeInt(storypost);
        dest.writeInt(folower);
        dest.writeInt(folowing);
    }
}
