package com.company.util.file;

import org.apache.hadoop.hbase.util.Bytes;

import java.io.*;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Author zhubin
 * @Date 2020/6/4 9:32
 * @Version 1.0
 */
public class FileUtil {

//    static byte[] AVRO_FILE_HEADER=new byte[]{0x4f,0x62,0x6a,0x01,0x02,0x16,0x61,0x76,0x72,0x6f,0x2e,0x73,0x63,0x68,0x65,0x6d};
    static byte[] AVRO_FILE_HEADER_1=new byte[]{0x4f};
    static byte[] AVRO_FILE_HEADER_2=new byte[]{0x62};
    static byte[] AVRO_FILE_HEADER_3=new byte[]{0x6a};
    static byte[] AVRO_FILE_HEADER_4=new byte[]{0x01};
    static byte[] AVRO_FILE_HEADER_5=new byte[]{0x02};
    static byte[] AVRO_FILE_HEADER_6=new byte[]{0x16};



    public static void main(String args[]) {
//        try {
//            FileReader read = new FileReader("D:\\ride\\testcase_clear\\FlumeData.1590977699683");
//            BufferedReader br = new BufferedReader(read);
//
//            String row;
//            int fileNo = 1;
//            FileWriter fw = new FileWriter("D:\\ride\\testcase_clear\\"+fileNo +".avro");
//            while ((row = br.readLine()) != null) {
//                if(row.contains("Obj")){
//                    if(fileNo == 1){
//                        fw.append(row + "\r\n");
//                    }
//                    fileNo ++ ;
//                }else {
//                    fw.append(row + "\r\n");
//                }
//                if(fileNo > 2 && row.contains("Obj")){
//                    fw.close();
//                    fw = new FileWriter("D:\\ride\\testcase_clear\\"+fileNo +".avro");
//                    fw.append(row + "\r\n");
//                }
//            }
//            fw.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        split("D:\\ride\\testcase_clear\\FlumeData.1596775612148");
    }


    public static void split(String src){
        File srcFile = new File(src);//源文件
        int fileNo = 1;
        InputStream in = null;//输入字节流
        BufferedInputStream bis = null;//输入缓冲流
        byte[] bytes = new byte[1];//每次读取文件的大小为1MB
        byte[] bufferBytes = new byte[6];
        int len = 0;//每次读取的长度值
        try {
            in = new FileInputStream(srcFile);
            bis = new BufferedInputStream(in) ;

//            OutputStream opt = null;
//            BufferedOutputStream outputStream = null;
            OutputStream opt = new FileOutputStream(new File("D:\\ride\\testcase_clear\\"+fileNo +".avro"));
            BufferedOutputStream outputStream = new BufferedOutputStream(opt);
            while(bis.read(bytes)!=-1){
                if(Bytes.compareTo(AVRO_FILE_HEADER_1,bytes) == 0 || Bytes.compareTo(AVRO_FILE_HEADER_3,bytes) == 0
                || Bytes.compareTo(AVRO_FILE_HEADER_2,bytes) == 0 || Bytes.compareTo(AVRO_FILE_HEADER_4,bytes) == 0
                || Bytes.compareTo(AVRO_FILE_HEADER_5,bytes) == 0 || Bytes.compareTo(AVRO_FILE_HEADER_6,bytes) == 0){
                    bufferBytes[len] = bytes[0];
                    len++;
                }else {
                    if(bufferBytes[0] != 0){
                        for (int i = 0; i < len; i++) {
                            outputStream.write(bufferBytes[i]);
                        }
                        bufferBytes = new byte[6];
                    }
                    if(fileNo==1 && len == 6){
                        fileNo++;
                    }
                    len = 0;
                    outputStream.write(bytes);
                }
                if(len==6 && fileNo != 1){

                    outputStream.flush();//刷新
                    opt.close();
                    outputStream.close();
                    fileNo++;
                    opt = new FileOutputStream(new File("D:\\ride\\testcase_clear\\"+fileNo +".avro"));
                    outputStream = new BufferedOutputStream(opt);
                    outputStream.write(bufferBytes);
                    bufferBytes = new byte[6];

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭流
            try {
                if(bis!=null)bis.close();
                if(in!=null)in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
