package org.example.khuvuichoidoodoo.Controller;

import org.example.khuvuichoidoodoo.Entity.DonHangDTO;
import org.example.khuvuichoidoodoo.Entity.SanPham;
import org.example.khuvuichoidoodoo.Service.SanPhamService;
import org.example.khuvuichoidoodoo.Util.HoaDonPDFUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping("/don-hang")
public class DonHangController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/dat-hang")
    public String datHang(Model model) {
        List<SanPham> dsSanPham = sanPhamService.layTatCa();
        model.addAttribute("dsSanPham", dsSanPham);
        return "dat_hang";
    }

    @PostMapping("/thanh-toan")
    @ResponseBody
    public String thanhToan(@RequestBody List<DonHangDTO> gioHang) {
        double tongTien = 0;
        StringBuilder thongBao = new StringBuilder();

        // ✅ Bước 1: Kiểm tra toàn bộ giỏ hàng có đủ số lượng không
        for (DonHangDTO item : gioHang) {
            Optional<SanPham> optionalSP = sanPhamService.timTheoTen(item.getTen());

            if (optionalSP.isPresent()) {
                SanPham sp = optionalSP.get();
                if (sp.getSoLuongTon() < item.getSoLuong()) {
                    thongBao.append("❌ Không đủ hàng: ").append(sp.getTenSanPham())
                            .append(" (chỉ còn ").append(sp.getSoLuongTon()).append(")\n");
                }
            } else {
                thongBao.append("❌ Không tìm thấy sản phẩm: ").append(item.getTen()).append("\n");
            }
        }

        // Nếu có lỗi, không cho thanh toán
        if (thongBao.length() > 0) {
            return thongBao.toString(); // ❌ Trả lỗi về client
        }

        // ✅ Bước 2: Trừ tồn kho và tính tổng tiền vì tất cả đều hợp lệ
        for (DonHangDTO item : gioHang) {
            SanPham sp = sanPhamService.timTheoTen(item.getTen()).get();
            sp.setSoLuongTon(sp.getSoLuongTon() - item.getSoLuong());
            sanPhamService.capNhat(sp);
            tongTien += item.getSoLuong() * item.getDonGia();
        }

        // ✅ Bước 3: Tạo hóa đơn PDF
        HoaDonPDFUtil.taoHoaDonPDF(gioHang, tongTien);

        return "✅ Đặt hàng thành công. Tổng tiền: " + String.format("%,.0f đ", tongTien) + "\n➡️ Hóa đơn đã được lưu!";
    }
}
