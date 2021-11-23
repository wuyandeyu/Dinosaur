package cn.gao.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundImage {
    public BufferedImage image;//背景图
    private BufferedImage image1,image2;//滚动的背景图
    private Graphics2D g;//绘图对象
    private int x1,x2;//绘制滚动图片坐标
    public static final int SPEED =4;//滚动速度
    public BackgroundImage(){
        try{
            image1= ImageIO.read(new File("src/main/resources/image/背景.png"));
            image2= ImageIO.read(new File("src/main/resources/image/背景.png"));

        }catch (Exception e){
            e.printStackTrace();
        }
        image = new BufferedImage(800,300,BufferedImage.TYPE_INT_RGB);
        g=image.createGraphics();
        x1=0;x2=800;
        g.drawImage(image1,x1,0,null);
    }
    public void roll(){
        x1-=SPEED;//图一左移
        x2-=SPEED;//图二左移
        if(x1<=-800){
            x1=800;//回到右侧
        }
        if(x2<=-800){
            x2=800;//回到右侧
        }
        g.drawImage(image1,x1,0,null);
        g.drawImage(image2,x2,0,null);
    }
}
