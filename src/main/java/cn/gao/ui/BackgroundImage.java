package cn.gao.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class BackgroundImage {
    public BufferedImage image;//����ͼ
    private BufferedImage image1,image2;//�����ı���ͼ
    private Graphics2D g;//��ͼ����
    private int x1,x2;//���ƹ���ͼƬ����
    public static final int SPEED =4;//�����ٶ�
    public BackgroundImage(){
        try{
            image1= ImageIO.read(new File("src/main/resources/image/����.png"));
            image2= ImageIO.read(new File("src/main/resources/image/����.png"));

        }catch (Exception e){
            e.printStackTrace();
        }
        image = new BufferedImage(800,300,BufferedImage.TYPE_INT_RGB);
        g=image.createGraphics();
        x1=0;x2=800;
        g.drawImage(image1,x1,0,null);
    }
    public void roll(){
        x1-=SPEED;//ͼһ����
        x2-=SPEED;//ͼ������
        if(x1<=-800){
            x1=800;//�ص��Ҳ�
        }
        if(x2<=-800){
            x2=800;//�ص��Ҳ�
        }
        g.drawImage(image1,x1,0,null);
        g.drawImage(image2,x2,0,null);
    }
}
