package org.example.khuvuichoidoodoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.*;

@Controller
public class DanhSachHoaDonController {

    @GetMapping("/hoa-don")
    public String danhSachHoaDon(Model model) {
        File goc = new File("exports/hoa-don");
        List<Map<String, String>> danhSach = new ArrayList<>();

        if (goc.exists() && goc.isDirectory()) {
            for (File ngayFolder : Objects.requireNonNull(goc.listFiles())) {
                if (ngayFolder.isDirectory()) {
                    String ngay = ngayFolder.getName();
                    for (File pdf : Objects.requireNonNull(ngayFolder.listFiles((dir, name) -> name.endsWith(".pdf")))) {
                        Map<String, String> item = new HashMap<>();
                        item.put("tenFile", pdf.getName());
                        item.put("duongDan", "/hoa-don-pdf/" + ngay + "/" + pdf.getName());
                        item.put("ngay", ngay);
                        danhSach.add(item);
                    }
                }
            }
        }

        // Sắp xếp theo ngày gần nhất
        danhSach.sort((a, b) -> b.get("ngay").compareTo(a.get("ngay")));
        model.addAttribute("hoaDonList", danhSach);
        return "hoa_don_list";
    }
}
