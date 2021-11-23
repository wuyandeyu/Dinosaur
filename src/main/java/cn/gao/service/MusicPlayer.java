package cn.gao.service;


import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MusicPlayer implements Runnable{
    File soundFile;//音乐文件
    Thread thread;//父线程
    boolean circulate;//是否循环播放

    /*
    *
    * 构造方法
    * */
    public MusicPlayer(String filePath, boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile=new File(filePath);
        if(!soundFile.exists()){
            throw new FileNotFoundException(filePath+"路径没有找到");
        }
    }

    /*
    *
    * 循环读取音乐文件
    * */
    @Override
    public void run() {
        byte[] buffer = new byte[1024 * 128];//128K缓冲区
        do {
            AudioInputStream audioInputStream = null; //音频输入流对象
            SourceDataLine auline=null;  //混频器源数据行
            try (AudioInputStream inputStream = audioInputStream = AudioSystem.getAudioInputStream(soundFile)) {
                AudioFormat format = audioInputStream.getFormat();//获取音频格式
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);//按照源数据行类型和指定音频格式创建数据行对象
                auline=(SourceDataLine) AudioSystem.getLine(info);//利用音频系统类获得与指定Line.Info对象中的描述匹配的行对象
                auline.open(format);//按照指定格式打开源数据行
                auline.start();//源数据行开启读写活动
                int byteCount=0;//记录音频输入流读到的字节数
                while (byteCount!=-1){//如果音频输入流中读取的字节数不为-1
                    byteCount=audioInputStream.read(buffer,0,buffer.length);//从音频数据流中读取128K的数据
                    if(byteCount>=0){//如果有就读取有效数据
                        auline.write(buffer,0,byteCount);//将有效数据写入数据行中
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                auline.drain();//清空数据行
                auline.close();//关闭数据行
            }
        }while (circulate);//循环标记判断是否循环播放
    }
    public void play(){
        thread = new Thread(this);
        thread.start();//
    }
    public void stop(){
        thread.stop();//需要换个方法
    }
}
