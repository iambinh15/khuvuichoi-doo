package org.example.khuvuichoidoodoo.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.example.khuvuichoidoodoo.Entity.DonHangDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class HoaDonPDFUtil {

    public static void taoHoaDonPDF(List<DonHangDTO> gioHang, double tongTien) {
        Document document = new Document();

        try {
            // Lấy ngày hiện tại để làm tên thư mục và file
            LocalDateTime now = LocalDateTime.now();
            String ngay = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String thoiGian = now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            // Tạo thư mục exports/hoa-don/yyyy-MM-dd nếu chưa tồn tại
            File thuMuc = new File("exports/hoa-don/" + ngay);
            if (!thuMuc.exists()) {
                thuMuc.mkdirs();
            }

            String tenFile = "exports/hoa-don/" + ngay + "/hoa_don_" + thoiGian + ".pdf";

            // Font hỗ trợ tiếng Việt (dùng Arial)
            BaseFont baseFont = BaseFont.createFont(
                    "C:/Windows/Fonts/arial.ttf",
                    BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED
            );
            Font font = new Font(baseFont, 12);
            Font titleFont = new Font(baseFont, 18, Font.BOLD);
            Font totalFont = new Font(baseFont, 14, Font.BOLD);

            // Format VNĐ
            NumberFormat vnFormat = NumberFormat.getInstance(new Locale("vi", "VN"));

            // Ghi file PDF
            PdfWriter.getInstance(document, new FileOutputStream(tenFile));
            document.open();

            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph("Ngày: " +
                    now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), font));
            document.add(new Paragraph("-----------------------------------------------------", font));

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);

            table.addCell(new PdfPCell(new Phrase("Tên sản phẩm", font)));
            table.addCell(new PdfPCell(new Phrase("Đơn giá", font)));
            table.addCell(new PdfPCell(new Phrase("Số lượng", font)));
            table.addCell(new PdfPCell(new Phrase("Thành tiền", font)));

            for (DonHangDTO sp : gioHang) {
                String donGiaStr = vnFormat.format(sp.getDonGia()) + " đ";
                String thanhTienStr = vnFormat.format(sp.getDonGia() * sp.getSoLuong()) + " đ";

                table.addCell(new Phrase(sp.getTen(), font));
                table.addCell(new Phrase(donGiaStr, font));
                table.addCell(new Phrase(String.valueOf(sp.getSoLuong()), font));
                table.addCell(new Phrase(thanhTienStr, font));
            }

            document.add(table);

            String tongTienStr = vnFormat.format(tongTien) + " đ";
            Paragraph total = new Paragraph("Tổng tiền: " + tongTienStr, totalFont);
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setSpacingBefore(20f);
            document.add(total);

            document.add(new Paragraph("Cảm ơn quý khách đã mua hàng!", font));
            document.add(new Paragraph("KHU VUI CHƠI DOO DOO RẤT HÂN HẠNH ĐƯỢC PHỤC VỤ QUÝ KHÁCH ", font));
            System.out.println("✅ Hóa đơn đã tạo tại: " + new File(tenFile).getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close(); // Rất quan trọng!
        }
    }
}
