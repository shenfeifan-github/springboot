/*package com.springboot.Util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class QRCodeGeneratorUtil {
    private static final int QR_CODE_SIZE = 200;

    public static void main(String[] args) throws WriterException, IOException {
        String text = "https://shenfeifan.oss-cn-guangzhou.aliyuncs.com/2024-08-11/183940.jpeg";
        String utf8Text = new String(text.getBytes("UTF-8"), "ISO-8859-1");
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(utf8Text, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE);
        Path path = FileSystems.getDefault().getPath("qrcode.png");
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        System.out.println("二维码已生成：" + path);
    }


}*/
package  com.springboot.Util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import io.netty.handler.codec.base64.Base64;

/**
 * 生成二维码
 */
public class QrCodeUtil {

    private static final String DEFAULT_CHAR_SET = "UTF-8";

    private static final String DEFAULT_FORMAT_NAME = "JPG";


    // 二维码宽度
    private static final int DEFAULT_QR_CODE_WIDTH = 300;
    // 二维码高度
    private static final int DEFAULT_QR_CODE_HEIGHT = 300;

    /**
     * 创建BitMatrix比特矩阵
     * @Date 2023/09/24 22:29
     * @Param contents 二维码里的内容
     * @Param width 二维码宽度
     * @param height 二维码高度
     * @return com.google.zxing.common.BitMatrix
     */
    public static  BitMatrix createBitMatrix(String contents , int width , int height) throws WriterException, IOException {

            width = DEFAULT_QR_CODE_WIDTH;

            height = DEFAULT_QR_CODE_HEIGHT;


        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // 纠错等级L,M,Q,H
        hints.put(EncodeHintType.CHARACTER_SET, DEFAULT_CHAR_SET);// 编码utf-8
        hints.put(EncodeHintType.MARGIN, 1);  // 边距

        // 创建比特矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                BarcodeFormat.QR_CODE, width, height, hints);
        return bitMatrix;

    }

    /**
     * 创建二维码，返回字节数组
     * @Date 2023/09/24 22:30
     * @Param contents 二维码里的内容
     * @Param imageFormat 图片后缀名
     * @Param width 二维码宽度
     * @param height 二维码高度
     * @return byte[]
     */
    public static byte[] createQrCode(String contents , String imageFormat , int width , int height) throws WriterException, IOException {

            imageFormat = DEFAULT_FORMAT_NAME;

        BitMatrix bitMatrix = createBitMatrix(contents , width, height);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, os);
        return os.toByteArray();
    }



    /**
     * 转换为BufferedImage
     * @Date 2023/09/24 22:32
     * @Param [bitMatrix]
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage toBufferedImage(BitMatrix bitMatrix) throws IOException, WriterException {
        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
        return bufferedImage;
    }

    /**
     * 给二维码添加logo
     * @Date 2023/09/24 22:33
     * @Param [bufferedImage, logoFile]
     * @return java.awt.image.BufferedImage
     */
    public static BufferedImage addQrCodeLogo(BufferedImage bufferedImage, File logoFile) throws IOException {
        Graphics2D graphics = bufferedImage.createGraphics();
        int matrixWidth = bufferedImage.getWidth();
        int matrixHeigh = bufferedImage.getHeight();

        // 读取logo图片文件
        BufferedImage logo = ImageIO.read(logoFile);
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        //  计算logo放置位置
        int x = bufferedImage.getWidth()  / 5*2;
        int y = bufferedImage.getHeight() / 5*2;
        int width = matrixWidth / 5;
        int height = matrixHeigh / 5;

        // 开始绘制图片
        graphics.drawImage(logo, x, y, width, height, null);
        graphics.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
        graphics.setStroke(new BasicStroke(5.0F, 1, 1));
        graphics.setColor(Color.white);
        graphics.drawRect(x, y, logoWidth, logoHeight);

        graphics.dispose();
        bufferedImage.flush();
        return bufferedImage;
    }

    public static void main(String[] args) throws Exception {
        BufferedImage bufferedImage = toBufferedImage(createBitMatrix("http://www.sfflwj.com", 300, 300));
        ImageIO.write(bufferedImage, "png", new File("D:/qrcode.jpg"));


        BufferedImage logoQrCode = addQrCodeLogo(bufferedImage, new File("D://22.jpg"));
        ImageIO.write(logoQrCode, "png", new File("D:/logo.jpg"));
    }

}