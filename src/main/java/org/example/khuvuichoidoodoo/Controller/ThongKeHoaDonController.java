package org.example.khuvuichoidoodoo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thong-ke")
public class ThongKeHoaDonController {

    @GetMapping
    public String hienThiThongKe() {
        return "thongke"; // trỏ tới file templates/thongke.html
    }
}
