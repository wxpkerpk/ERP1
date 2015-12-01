package com.guotion.material.service.constant;

/**
 * Created by xing on 2015-06-10.
 */
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
public class Thumbnail {
    /**
     * 生成缩略图
     * fromFileStr:原图片路径
     * saveToFileStr:缩略图路径
     * width:缩略图的宽
     * height:缩略图的高
     */
    public static void saveImageAsJpg(String fromFileStr, String saveToFileStr,
                                      int width, int height, boolean equalProportion) throws Exception {
        BufferedImage srcImage;
        String imgType = "JPEG";
        if (fromFileStr.toLowerCase().endsWith(".png")) {
            imgType = "PNG";
        }
        File fromFile = new File(fromFileStr);
        File saveFile = new File(saveToFileStr);
        srcImage = ImageIO.read(fromFile);
        if (width > 0 || height > 0) {
            srcImage = resize(srcImage, width, height, equalProportion);
        }
        ImageIO.write(srcImage, imgType, saveFile);
    }


    public static BufferedImage resize(BufferedImage source, int targetW, int targetH, boolean equalProportion) {
        int type = source.getType();
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();

        if (equalProportion) {
            if (sx > sy) {
                sx = sy;
                targetW = (int) (sx * source.getWidth());
            } else {
                sy = sx;
                targetH = (int) (sx * source.getHeight());
            }
        }
        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetH);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
            Graphics2D g = target.createGraphics();
            g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
            g.dispose();
        }
        return target;
    }
}
