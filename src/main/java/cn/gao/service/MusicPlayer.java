package cn.gao.service;


import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MusicPlayer implements Runnable{
    File soundFile;//�����ļ�
    Thread thread;//���߳�
    boolean circulate;//�Ƿ�ѭ������

    /*
    *
    * ���췽��
    * */
    public MusicPlayer(String filePath, boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile=new File(filePath);
        if(!soundFile.exists()){
            throw new FileNotFoundException(filePath+"·��û���ҵ�");
        }
    }

    /*
    *
    * ѭ����ȡ�����ļ�
    * */
    @Override
    public void run() {
        byte[] buffer = new byte[1024 * 128];//128K������
        do {
            AudioInputStream audioInputStream = null; //��Ƶ����������
            SourceDataLine auline=null;  //��Ƶ��Դ������
            try (AudioInputStream inputStream = audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
                AudioFormat format = audioInputStream.getFormat();//��ȡ��Ƶ��ʽ
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);//����Դ���������ͺ�ָ����Ƶ��ʽ���������ж���
                auline=(SourceDataLine) AudioSystem.getLine(info);//������Ƶϵͳ������ָ��Line.Info�����е�����ƥ����ж���
                auline.open(format);//����ָ����ʽ��Դ������
                auline.start();//Դ�����п�����д�
                int byteCount=0;//��¼��Ƶ�������������ֽ���
                while (byteCount!=-1){//�����Ƶ�������ж�ȡ���ֽ�����Ϊ-1
                    byteCount=audioInputStream.read(buffer,0,buffer.length);//����Ƶ�������ж�ȡ128K������
                    if(byteCount>=0){//����оͶ�ȡ��Ч����
                        auline.write(buffer,0,byteCount);//����Ч����д����������
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                auline.drain();//���������
                auline.close();//�ر�������
            }
        }while (circulate);//ѭ������ж��Ƿ�ѭ������
    }
    public void play(){
        thread = new Thread(this);
        thread.start();//
    }
    public void stop(){
        thread.stop();//��Ҫ��������
    }
}
