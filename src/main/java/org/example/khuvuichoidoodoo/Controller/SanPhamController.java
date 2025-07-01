package org.example.khuvuichoidoodoo.Controller;

import org.example.khuvuichoidoodoo.Entity.DonHangDTO;
import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.example.khuvuichoidoodoo.Repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamRepository sanPhamRepository;
    private List<SanPham> dsSanPham = new ArrayList<>();
    // Giao diện chính gồm form + bảng
    @GetMapping("")
    public String hienThiGiaoDien(Model model) {
        model.addAttribute("dsSanPham", sanPhamRepository.findAll());
        model.addAttribute("sanPham", new SanPham()); // Thêm trống để form xử lý cả thêm/sửa
        return "them-san-pham";
    }

    // Xử lý thêm hoặc cập nhật sản phẩm
    @PostMapping("/luu")
    public String luuSanPham(@ModelAttribute SanPham sanPham) {
        sanPhamRepository.save(sanPham); // save sẽ tự động cập nhật nếu có ID
        return "redirect:/san-pham";
    }

    // Xử lý click nút "Sửa"
    @GetMapping("/sua/{id}")
    public String suaSanPham(@PathVariable Long id, Model model) {
        SanPham sp = sanPhamRepository.findById(id).orElse(null);
        model.addAttribute("sanPham", sp); // Gửi sản phẩm đang sửa về form
        model.addAttribute("dsSanPham", sanPhamRepository.findAll()); // Gửi danh sách cho bảng
        return "them-san-pham";
    }

    // Xoá sản phẩm
    @GetMapping("/xoa/{id}")
    public String xoa(@PathVariable Long id) {
        sanPhamRepository.deleteById(id);
        return "redirect:/san-pham";
    }
    @GetMapping("/dat-hang")
    public String datHang(Model model) {
        model.addAttribute("dsSanPham", dsSanPham);
        return "dat_hang"; // Thymeleaf view
    }

    @PostMapping("/thanh-toan")
    @ResponseBody
    public String thanhToan(@RequestBody List<DonHangDTO> gioHang) {
        double tongTien = 0;
        for (DonHangDTO item : gioHang) {
            tongTien += item.getSoLuong() * item.getDonGia();
        }
        return "✅ Đặt hàng thành công. Tổng tiền: " + tongTien + " đ";
    }
}
