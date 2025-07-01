package org.example.khuvuichoidoodoo.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {

    @GetMapping("/")
    public String hienThiTrangChu() {
        return "index"; // trỏ tới file templates/index.html
    }
}

