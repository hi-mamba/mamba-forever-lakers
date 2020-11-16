package space.mamba.qr.controller;

import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import space.mamba.qr.util.QRBarCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author pankui
 * @date 2020/11/16
 * <pre>
 *
 * </pre>
 */

@RestController("/qrcode")
public class QRCodeController {
    /**
     * 获取二维码
     * text 必须用UTF8编码格式，x内容出现 & 符号时，请用 %26 代替,换行符使用 %0A
     */
    @GetMapping("/image")
    public void qrCodeimage(HttpServletRequest request, HttpServletResponse response, String text)
            throws IOException, WriterException {

        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        byte[] imageByte = QRBarCodeUtil.getQRCodeImage(text, 250, 250);
        InputStream inputStream = new ByteArrayInputStream(imageByte);
        BufferedImage image = ImageIO.read(inputStream);
        ImageIO.write(image, "png", response.getOutputStream());
    }

    @GetMapping("/hi")
    public String hi() {
        return "hi";
    }

}
