package com.javayh.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.javayh.common.util.log.Log;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * <p>
 * 二维码
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-01 21:41
 */
public class QRCodeGenerator {

    /**
     * <p>
     *       将二维码保存到指定位置
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/1
     * @param qrCode
     * @return void
     */
    public static void generateQRCodeImage(QRCode qrCode) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(qrCode.getText(), BarcodeFormat.QR_CODE, qrCode.getWidth(), qrCode.getHeight());
            Path path = FileSystems.getDefault().getPath(qrCode.getFilePath());
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
            Log.error("二维码生成错误",e.getStackTrace());
        }

    }

    /**
     * <p>
     *       二维码渲染
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/1
     * @param qrCode
     * @return byte[]
     */
    public static ResponseEntity<byte[]> renderQrCodeImage(QRCode qrCode) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream pngOutputStream= null;
        try {
            bitMatrix = qrCodeWriter.encode(qrCode.getText(), BarcodeFormat.QR_CODE, qrCode.getWidth(), qrCode.getHeight());
            pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        } catch (WriterException | IOException e) {
            Log.error("二维码渲染",e.getStackTrace());
        }
        byte[] pngData = pngOutputStream.toByteArray();
        return new ResponseEntity<byte[]>(pngData, headers, HttpStatus.CREATED);
    }

//    public static void main(String[] args) throws IOException, WriterException {
//        QRCode qrCode = QRCode.builder().height(500).width(500).text("Java有货").filePath("C:\\Users\\haiyang\\Desktop\\MyQRCode.png").build();
//        QRCodeGenerator.generateQRCodeImage(qrCode);
//    }
}
