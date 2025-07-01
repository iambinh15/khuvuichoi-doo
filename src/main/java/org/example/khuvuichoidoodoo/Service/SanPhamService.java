package org.example.khuvuichoidoodoo.Service;

import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.example.khuvuichoidoodoo.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanPhamService {

    @Autowired
    private SanPhamRepository sanPhamRepository;

    // Thêm sản phẩm mới
    public SanPham themSanPham(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    // Lấy tất cả sản phẩm
    public List<SanPham> layTatCa() {
        return sanPhamRepository.findAll();
    }

    // Tìm sản phẩm theo tên
    public Optional<SanPham> timTheoTen(String ten) {
        return sanPhamRepository.findByTenSanPham(ten);
    }

    // Cập nhật lại sản phẩm (ví dụ trừ số lượng)
    public SanPham capNhat(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }
}
