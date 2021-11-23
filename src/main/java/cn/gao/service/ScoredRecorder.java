package cn.gao.service;

import java.io.*;
import java.util.Arrays;

public class ScoredRecorder {
    private static final String SCOREFILE= "src/main/resources/data/source";//成绩记录文件
    private static int scores[] =new int[3];//当前得分最高前三名
    public static  void init(){
        File f = new File(SCOREFILE);
        if (!f.exists()) {
            try {
                f.createNewFile();//创建记录文件
            }catch (Exception e){
                e.printStackTrace();
            }
            return;//停止方法
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
                    Arrays.fill(scores,0);//数组填充零
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
        int tmp[] = Arrays.copyOf(scores,4);//在得分数组基础之上创建一个长度为4的零时数组
        tmp[3]=score;//将新分数赋值给第四个元素
        Arrays.sort(tmp);//零时数组排序
        scores= Arrays.copyOfRange(tmp,1,4);//将排完序后的前三个值赋值个得分数组
    }
    static public int[]  getScores(){
        return scores;
    }
}
