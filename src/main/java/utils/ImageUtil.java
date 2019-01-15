package utils;


import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;
import java.util.Iterator;

/**
 * @author daiyihua
 * @create 2019-01-03 2:57 PM
 * @desc  图片处理类
 **/

public class ImageUtil {


    /**
     * 对图片裁剪，并把裁剪完蛋新图片保存 。
     *
     * @param srcpath
     *            源图片路径
     * @param subpath
     *            剪切图片存放路径
     * @param x
     *            剪切点x坐标
     * @param y
     *            剪切点y坐标
     * @param width
     *            剪切宽度
     * @param height
     *            剪切高度
     * @throws IOException
     */
    public static void abscut(String srcpath, String subpath, int x, int y,
                              int width, int height) {

        FileInputStream is = null;
        ImageInputStream iis = null;

        try {
            // 读取图片文件
            is = new FileInputStream(srcpath);

            /**//*
             * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
             * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
             */
            String suffix = srcpath.substring(srcpath.lastIndexOf(".") + 1);

            Iterator<ImageReader> it = ImageIO
                    .getImageReadersByFormatName(suffix);
            ImageReader reader = it.next();
            // 获取图片流
            iis = ImageIO.createImageInputStream(is);

            /**//*
             * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
             * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
             * 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
             */
            reader.setInput(iis, true);

            /**//*
             * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
             * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
             * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
             */
            ImageReadParam param = reader.getDefaultReadParam();

            /**//*
             * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
             * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
             */
            Rectangle rect = new Rectangle(x, y, width, height);

            // 提供一个 BufferedImage，将其用作解码像素数据的目标。
            param.setSourceRegion(rect);

            /**//*
             * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
             * BufferedImage 返回。
             */
            BufferedImage bi = reader.read(0, param);

            // 保存新图片
            ImageIO.write(bi, "jpg", new File(subpath));
        } catch (Exception e) {
            // TODO: handle exception
        }

        finally {
            try {
                if (is != null)
                    is.close();
                if (iis != null)
                    iis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    /**
     * 缩放图像
     *
     * @param srcImageFile
     *            源图像文件地址
     * @param result
     *            缩放后的图像地址
     * @param scale
     *            缩放比例
     * @param flag
     *            缩放选择:true 放大; false 缩小;
     */
    public static void scale(String srcImageFile, String result, int scale,
                             boolean flag) {
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长
            if (flag) {
                // 放大
                width = width * scale;
                height = height * scale;
            } else {
                // 缩小
                width = width / scale;
                height = height / scale;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重新生成按指定宽度和高度的图像
     *
     * @param srcImageFile
     *            源图像文件地址
     * @param result
     *            新的图像地址
     * @param _width
     *            设置新的图像宽度
     * @param _height
     *            设置新的图像高度
     */
    public static void scale(String srcImageFile, String result, int _width,
                             int _height) {
        scale(srcImageFile, result, _width, _height, 0, 0);
    }

    public static void scale(String srcImageFile, String result, int _width,
                             int _height, int x, int y) {
        try {

            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件

            int width = src.getWidth(); // 得到源图宽
            int height = src.getHeight(); // 得到源图长

            if (width > _width) {
                width = _width;
            }
            if (height > _height) {
                height = _height;
            }
            Image image = src.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, x, y, null); // 绘制缩小后的图
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(result));// 输出到文件流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 图像类型转换 GIF->JPG GIF->PNG PNG->JPG PNG->GIF(X)
     */
    public static void convert(String source, String result) {
        try {
            File f = new File(source);
            f.canRead();
            f.canWrite();
            BufferedImage src = ImageIO.read(f);
            ImageIO.write(src, "JPG", new File(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 彩色转为黑白
     *
     * @param source
     * @param result
     */
    public static void gray(String source, String result) {
        try {
            BufferedImage src = ImageIO.read(new File(source));
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);
            src = op.filter(src, null);
            ImageIO.write(src, "JPEG", new File(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void cutImageByProportion(String srcImageFile,String dirImageFile, float x, float y, float destWidth,
                                            float destHeight){
        try {
            BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
            float width = src.getWidth(); // 得到源图宽
            float height = src.getHeight(); // 得到源图长
            int newx=Math.round(x*width);
            int newy=Math.round(y*height);
            int newdestWidth=Math.round(destWidth*width);
            int newdestHeight=Math.round(destHeight*height);
            abscut(srcImageFile,dirImageFile,newx,newy,newdestWidth,newdestHeight);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    /**
     * 缩放图片方法
     *
     * @param srcImageFile 要缩放的图片路径
     * @param result 缩放后的图片路径
     * @param height 目标高度像素
     * @param width  目标宽度像素
     * @param bb     是否补白
     */
    public final static void scale(String srcImageFile, String result, int height, int width, boolean bb) {
        try {
            double ratio = 0.0; // 缩放比例
            File f = new File(srcImageFile);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                double   ratioHeight = (new Integer(height)).doubleValue()/ bi.getHeight();
                double   ratioWhidth = (new Integer(width)).doubleValue()/ bi.getWidth();
                if(ratioHeight>ratioWhidth){
                    ratio= ratioHeight;
                }else{
                    ratio= ratioWhidth;
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
                        .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
                itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
                Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                g.setColor(Color.white);//控制颜色
                g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            ImageIO.write((BufferedImage) itemp, "JPEG", new File(result));      //输出压缩图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 缩放图片方法（字节流转换）
     *
     * @param inputSrc 输入的图片字节流
     * @param bb     是否补白
     */
    public final static byte[] scaleBuffer(byte[] inputSrc,  boolean bb) {
        try {
            // byte[] 转 image
            InputStream inputStream = new ByteArrayInputStream(inputSrc);
            double ratio = 0.0; // 缩放比例
            BufferedImage bi = ImageIO.read(inputStream);
            int height = 400, width = 300;
            int biHeight = bi.getHeight(), biWidth = bi.getWidth();
            if (biWidth/ biHeight > 0.8){
                width = 400;
            }

            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);//bi.SCALE_SMOOTH  选择图像平滑度比缩放速度具有更高优先级的图像缩放算法。

            // 计算比例
            if ((biHeight > height) || (biWidth > width)) {
                double   ratioHeight = (new Integer(height)).doubleValue()/ bi.getHeight();
                double   ratioWhidth = (new Integer(width)).doubleValue()/ bi.getWidth();
                if(ratioHeight>ratioWhidth){
                    ratio= ratioHeight;
                }else{
                    ratio= ratioWhidth;
                }
                AffineTransformOp op = new AffineTransformOp(AffineTransform//仿射转换
                        .getScaleInstance(ratio, ratio), null);//返回表示剪切变换的变换
                itemp = op.filter(bi, null);//转换源 BufferedImage 并将结果存储在目标 BufferedImage 中。
            }
            if (bb) {//补白
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);//构造一个类型为预定义图像类型之一的 BufferedImage。
                Graphics2D g = image.createGraphics();//创建一个 Graphics2D，可以将它绘制到此 BufferedImage 中。
                g.setColor(Color.white);//控制颜色
                g.fillRect(0, 0, width, height);// 使用 Graphics2D 上下文的设置，填充 Shape 的内部区域。
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            // image 转 byte[]
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write((BufferedImage)itemp,"JPEG",bos);
            return bos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 裁剪图片方法
     * @param bufferedImage 图像源
     * @param startX 裁剪开始x坐标
     * @param startY 裁剪开始y坐标
     * @param endX 裁剪结束x坐标
     * @param endY 裁剪结束y坐标
     * @return
     */
    public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {
            startX = 0;
        }
        if (startY == -1) {
            startY = 0;
        }
        if (endX == -1) {
            endX = width - 1;
        }
        if (endY == -1) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }

    /*
    public static void main(String[] args) throws IOException {
        String path = "/Users/daiyihua/Downloads/1122.jpg";    //输入图片  测试要在C盘放一张图片1.jpg
        ImgUtils.scale("/Users/daiyihua/Downloads/1122.jpg", "/Users/daiyihua/Downloads/2233.jpg", 180, 240, true);//等比例缩放  输出缩放图片

        File newfile = new File("/Users/daiyihua/Downloads/2233.jpg");
        BufferedImage bufferedimage = ImageIO.read(newfile);
        int width = bufferedimage.getWidth();
        int height = bufferedimage.getHeight();
        //目标将图片裁剪成 宽240，高160
        if (width > 240) {
        //            开始x坐标              开始y坐标             结束x坐标                     结束y坐标
            bufferedimage = ImgUtils.cropImage(bufferedimage, (int) ((width - 240) / 2), 0, (int) (width - (width - 240) / 2), (int) (height)
            );
            if (height > 160) {
                bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((height - 160) / 2), 240, (int) (height - (height - 160) / 2)
                );
            }
        } else {
            if (height > 160) {
                bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((height - 160) / 2), (int) (width), (int) (height - (height - 160) / 2)
                );
            }
        }
        ImageIO.write(bufferedimage, "jpg", new File("C:/caijian.jpg"));    //输出裁剪图片
    }
    */
}
