package com.guotion.material.web.constant;

/**
 * Created by xing on 2015-07-01.
 */


    import java.awt.Graphics;
    import java.awt.Image;
    import java.awt.image.BufferedImage;
    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.IOException;

    import javax.imageio.ImageIO;


    /**
     * 图片基本操作
     */
    public  class ConvertToPng {

        private static ConvertToPng instance;

        private ConvertToPng() {
            instance = this;
        }

        public static ConvertToPng getInstance() {
            if (instance == null) {
                instance = new ConvertToPng();
            }
            return instance;
        }

        /**
         * 缩小并转换格式
         *
         * @return
         */
        public  Boolean narrowAndFormateTransfer(  BufferedImage src , String formate,String destFile) {
            boolean flag = false;
            try {

                Image image = src.getScaledInstance(src.getWidth(),src.getHeight() , Image.SCALE_DEFAULT);
                BufferedImage tag = new BufferedImage(src.getWidth(),src.getHeight() ,  BufferedImage.TYPE_INT_ARGB);
                Graphics g = tag.getGraphics();
                g.drawImage(image, 0, 0, null);
                g.dispose();
                flag = ImageIO.write(tag, formate, new FileOutputStream(destFile));// 输出到文件流
            } catch (IOException e) {
                e.printStackTrace();
            }
            return flag;
        }



    }

