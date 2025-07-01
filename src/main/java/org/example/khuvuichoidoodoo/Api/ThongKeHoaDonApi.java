package org.example.khuvuichoidoodoo.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

@RestController
public class ThongKeHoaDonApi {

    @GetMapping("/api/thong-ke-hoa-don")
    public List<Map<String, Object>> layDuLieuThongKe() {
        List<Map<String, Object>> danhSach = new ArrayList<>();
        File goc = new File("exports/hoa-don");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Pattern pattern = Pattern.compile("Tổng tiền: ([\\d,.]+)");

        if (goc.exists() && goc.isDirectory()) {
            for (File ngayFolder : Objects.requireNonNull(goc.listFiles())) {
                if (ngayFolder.isDirectory()) {
                    String ngay = ngayFolder.getName();
                    File[] pdfFiles = ngayFolder.listFiles((dir, name) -> name.endsWith(".pdf"));
                    int soHoaDon = 0;
                    long tongTien = 0;

                    if (pdfFiles != null) {
                        for (File pdf : pdfFiles) {
                            try (PDDocument doc = PDDocument.load(pdf)) {
                                String text = new PDFTextStripper().getText(doc);
                                Matcher matcher = pattern.matcher(text);
                                if (matcher.find()) {
                                    String tienStr = matcher.group(1).replace(",", "").replace(".", "");
                                    tongTien += Long.parseLong(tienStr);
                                    soHoaDon++;
                                }
                            } catch (Exception ignored) {}
                        }
                    }

                    Map<String, Object> row = new HashMap<>();
                    row.put("ngay", ngay);
                    row.put("soHoaDon", soHoaDon);
                    row.put("tongTien", tongTien);
                    danhSach.add(row);
                }
            }
        }

        danhSach.sort(Comparator.comparing(item -> LocalDate.parse((String) item.get("ngay"), dtf)));
        return danhSach;
    }
}
