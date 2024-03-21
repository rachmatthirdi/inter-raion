package com.example.projectonboarding;

public class dbBerita {
    private String tanggal,judul,author;

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public dbBerita(String tanggal, String judul, String author) {
        this.tanggal = tanggal;
        this.judul = judul;
        this.author = author;
    }
    public dbBerita(){}

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
