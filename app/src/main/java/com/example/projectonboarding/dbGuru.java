package com.example.projectonboarding;

public class dbGuru {
    private String foto,nama,matpel,rating,jenjang1,jenjang2;

    public dbGuru(String foto, String nama, String matpel, String rating, String jenjang1, String jenjang2) {
        this.foto = foto;
        this.nama = nama;
        this.matpel = matpel;
        this.rating = rating;
        this.jenjang1 = jenjang1;
        this.jenjang2 = jenjang2;
    }
    public dbGuru(){}

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public String getMatpel() {
        return matpel;
    }

    public void setMatpel(String matpel) {
        this.matpel = matpel;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getJenjang1() {
        return jenjang1;
    }

    public void setJenjang1(String jenjang1) {
        this.jenjang1 = jenjang1;
    }

    public String getJenjang2() {
        return jenjang2;
    }

    public void setJenjang2(String jenjang2) {
        this.jenjang2 = jenjang2;
    }
}
