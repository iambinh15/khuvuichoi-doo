package org.example.khuvuichoidoodoo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "san_pham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ten_san_pham", nullable = false)
    private String tenSanPham;

    @Column(name = "don_gia", nullable = false)
    private Double donGia;

    @Column(name = "loai_san_pham", nullable = false)
    private String loaiSanPham;

    @Column(name = "so_luong_ton", nullable = false)
    private Integer soLuongTon = 0;

    public SanPham() {
    }

    public SanPham(Long id, String tenSanPham, Double donGia, String loaiSanPham, Integer soLuongTon) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.donGia = donGia;
        this.loaiSanPham = loaiSanPham;
        this.soLuongTon = soLuongTon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
}
