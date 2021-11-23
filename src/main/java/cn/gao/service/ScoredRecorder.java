package cn.gao.service;

import java.io.*;
import java.util.Arrays;

public class ScoredRecorder {
    private static final String SCOREFILE= "src/main/resources/data/source";//�ɼ���¼�ļ�
    private static int scores[] =new int[3];//��ǰ�÷����ǰ����
    public static  void init(){
        File f = new File(SCOREFILE);
        if (!f.exists()) {
            try {
                f.createNewFile();//������¼�ļ�
            }catch (Exception e){
                e.printStackTrace();
            }
            return;//ֹͣ����
        }
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader =null;
        try {
            fileInputStream= new FileInputStream(f);
            inputStreamReader= new InputStreamReader(fileInputStream);
            bufferedReader= new BufferedReader(inputStreamReader);
            String value = bufferedReader.readLine();
            if(!(value==null||"".equals(value))){
                String[] strings = value.split(",");
                if(strings.length<3){
                    Arrays.fill(scores,0);//���������
                }else{
                    for (int i = 0; i < 3; i++) {
                        scores[i]=Integer.parseInt(strings[i]);
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void saveScope(){
        String value = scores[0]+","+scores[1]+","+scores[2];
        try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(SCOREFILE)))){
            bufferedWriter.write(value);
            bufferedWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static public void addNewScore(int score){
        int tmp[] = Arrays.copyOf(scores,4);//�ڵ÷��������֮�ϴ���һ������Ϊ4����ʱ����
        tmp[3]=score;//���·�����ֵ�����ĸ�Ԫ��
        Arrays.sort(tmp);//��ʱ��������
        scores= Arrays.copyOfRange(tmp,1,4);//����������ǰ����ֵ��ֵ���÷�����
    }
    static public int[]  getScores(){
        return scores;
    }
}
