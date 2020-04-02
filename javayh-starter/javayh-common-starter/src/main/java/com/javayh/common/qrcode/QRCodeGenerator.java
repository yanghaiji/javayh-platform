package com.javayh.common.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Hashtable;
import java.util.Random;

/**
 * <p>
 *      二维码生成器
 * </p>
 *
 * @author Dylan-haiji
 * @version 1.0.0
 * @since 2020-04-01 23:24
 */
public class QRCodeGenerator {
    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    /**
     * <p>
     *       创建二维码
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param content           文本内容
     * @param imgPath           log地址
     * @param needCompress      是否压缩
     * @return java.awt.image.BufferedImage
     */
    private static BufferedImage createImage(String content, String imgPath,
                                             boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeGenerator.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * <p>
     *       插入LOGO
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param source        二维码图片
     * @param imgPath       LOGO图片地址
     * @param needCompress  是否压缩
     * @return void
     */
    private static void insertImage(BufferedImage source, String imgPath,
                                    boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println(""+imgPath+"   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (needCompress) {
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            // 绘制缩小后的图
            g.drawImage(image, 0, 0, null);
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * <p>
     *       生成二维码(内嵌LOGO)
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param content       内容
     * @param imgPath       LOGO地址
     * @param destPath      存放目录
     * @param needCompress   是否压缩LOGO
     * @return java.lang.String
     */
    private static String encode(String content, String imgPath, String destPath,
                                 boolean needCompress) throws Exception {
        BufferedImage image = QRCodeGenerator.createImage(content, imgPath,
                needCompress);
        mkdirs(destPath);
        String file = new Random().nextInt(99999999)+".jpg";
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        return file;
    }

    /**
     * <p>
     *       当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param destPath   存放目录
     * @return void
     */
    private static void mkdirs(String destPath) {
        File file =new File(destPath);
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * <p>
     *       生成二维码(内嵌LOGO)
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param content       内容
     * @param imgPath       LOGO地址
     * @param destPath      存储地址
     * @return void
     */
    public static void encode(String content, String imgPath, String destPath)
            throws Exception {
        QRCodeGenerator.encode(content, imgPath, destPath, false);
    }

    /**
     * <p>
     *       解析二维码
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param file      二维码图片
     * @return java.lang.String
     */
    private static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
                image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * <p>
     *        解析二维码
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param path       二维码图片地址
     * @return java.lang.String
     */
    public static String decode(String path) throws Exception {
        return QRCodeGenerator.decode(new File(path));
    }

    /**
     * <p>
     *       图片渲染到页面
     * </p>
     * @version 1.0.0
     * @author Dylan-haiji
     * @since 2020/4/2
     * @param text
     * @param logoPath
     * @param response
     * @return void
     */
    public static void outputQRCode(String text,String logoPath,HttpServletResponse response) throws Exception {
        BufferedImage image = QRCodeGenerator.createImage(text, logoPath, true);
        ImageIO.write(image,"png",response.getOutputStream());
    }


/*    public static void main(String[] args) throws Exception {
        String text = "https://blog.csdn.net/weixin_38937840";  //这里设置自定义网站url
        String logoPath = "C:\\Users\\haiyang\\Desktop\\MyQRCode.png";
        String destPath = "C:\\Users\\haiyang\\Desktop\\";
        System.out.println(QRCodeGenerator.encode(text, logoPath, destPath, true));
    }*/
}
