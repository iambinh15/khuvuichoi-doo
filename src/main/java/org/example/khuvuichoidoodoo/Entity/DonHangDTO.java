package org.example.khuvuichoidoodoo.Entity;

public class DonHangDTO {
    private String ten;
    private int soLuong;
    private double donGia;

    public DonHangDTO() {}

    public DonHangDTO(String ten, int soLuong, double donGia) {
        this.ten = ten;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
}