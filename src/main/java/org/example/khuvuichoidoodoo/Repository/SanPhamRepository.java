package org.example.khuvuichoidoodoo.Repository;

import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    // Bạn có thể khai báo thêm các phương thức tìm kiếm tùy chỉnh ở đây nếu muốn, ví dụ:
    // List<SanPham> findByLoaiSanPham(String loaiSanPham);
    Optional<SanPham> findByTenSanPham(String tenSanPham);
    Optional<SanPham> findByTenSanPhamIgnoreCase(String tenSanPham);


}
