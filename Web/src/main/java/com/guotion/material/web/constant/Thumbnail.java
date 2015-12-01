package com.guotion.material.web.constant;

/**
 * Created by xing on 2015-06-10.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class Thumbnail {

    public static boolean createThumbnail(BufferedImage Bi, String saveToFileStr, String sysimgfile, int width, int height, String suffix) throws Exception {
        // fileExtNmae是图片的格式 gif JPG 或png
        // String fileExtNmae="";
        double RatioX=0.0,RatioY = 0.0;

        File ThF = new File(saveToFileStr, sysimgfile + "." + suffix);
        Image Itemp = Bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        RatioY = (double) width / Bi.getHeight();
         RatioX = (double) height / Bi.getWidth();
        AffineTransformOp op = new AffineTransformOp(AffineTransform
                .getScaleInstance(RatioX, RatioY), null);
        Itemp = op.filter(Bi, null);
        try {
            ImageIO.write((BufferedImage) Itemp, suffix, ThF);
        } catch (Exception ex) {
            throw new Exception(" error：  "
                    + ex.getMessage());
        }
        return (true);
    }
}