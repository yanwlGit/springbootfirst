package com.wlong.mywife.study.book;

import com.wlong.util.MyBookEnum;
import com.wlong.util.MyConstants;
import com.wlong.util.MyFileUtil;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class ReadPicture {

    private static Logger logger= LoggerFactory.getLogger(ReadPicture.class);
    public  static String SAVE_TXT_FILE_SUFFIX=".txt";

    public void creatPicText(List<MyBookEnum> pics){
        //Executors.newCachedThreadPool(); 定时执行
        //创建⼀个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若⽆可回收，则新建线程
        //图片转文字 ocr
        Tesseract instance=new Tesseract();
        //训练库的路径  tessdata
        instance.setDatapath("D:\\ocr\\install\\tessdata");
        //训练库路径下 解析图片使用的字集库 chi_sim 代表中文简体
        instance.setLanguage("chi_sim");

        //ExecutorService cachedThreadPool =  Executors.newFixedThreadPool(3);

        for(MyBookEnum book:pics){
            creatPicByMyBookEnum(book,instance);
            //使用时不执行读取图片，暂时未排查
            /*cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    creatPicByMyBookEnum(book,instance);
                }
            });*/

        }
    }

    public void creatPicByMyBookEnum(MyBookEnum bookPic,Tesseract instance){
        //图片路径
        File imageFile =new File(bookPic.getPath());
        File[] pics=imageFile.listFiles();
        System.out.println(imageFile.getPath()+"---->"+pics.length);
        for(File pic:pics){
            try {
                //解析后保存路径
                File textFile=new File(bookPic.getTextPath());
                if(!textFile.exists()){
                    textFile.mkdirs();
                }
                String fileName=pic.getName().substring(0,pic.getName().lastIndexOf("."));
                File anyTextFile=new File(textFile.getPath()+
                        File.separator+fileName+SAVE_TXT_FILE_SUFFIX);
                if(!anyTextFile.exists()){
                    String result = instance.doOCR(pic);
                    MyFileUtil.wirteFile(result,anyTextFile);
                }
            } catch (TesseractException e) {
                logger.error("读取图片错误---->"+pic.getPath());
            }
        }
        System.out.println(imageFile.getPath()+"---->完成");
    }

    public static void searchFileByTextKey(String key,List<MyBookEnum> bookEnums){
        //控制台输出显示不全，需要在写入到文件。这里暂不使用多线程
        //ExecutorService cachedThreadPool =  Executors.newFixedThreadPool(3);
        for(MyBookEnum textPat:bookEnums){
            searchKeyByFile(textPat,key);
            /*cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    searchKeyByFile(textPat,key);
                }
            });*/
        }
    }

    public static void searchKeyByFile(MyBookEnum textPat,String key){
        File searFile=new File(textPat.getTextPath());
        File[] chirld=searFile.listFiles();
        if(chirld==null){
            System.out.println("错误文件路径---->"+textPat.getTextPath());
        }
        for(File any:chirld){
            StringBuffer sb=new StringBuffer();
            FileReader fr=null;
            BufferedReader br=null;
            boolean check=false;
            try{
                fr=new FileReader(any.getPath());
                br=new BufferedReader(fr);
                String line="";
                while ((line=br.readLine())!=null) {
                    line=line.replace(" ","");
                    sb.append(line).append("\n");
                    if(line.indexOf(key)>-1){
                        check=true;
                    }
                }
                if(check){
                    System.out.println(sb.toString());
                    System.out.println("--------------------匹配到的图片_"+any.getPath()+"--------------------");
                }
            }catch (Exception e){
                logger.error("读取失败",e);
            }finally{
                try{
                    if(null!=br){
                        br.close();
                    }
                    if(null!=fr){
                        fr.close();
                    }
                }catch (Exception e){
                    logger.error("关闭文件流错误",e);
                }
            }

        }
    }


    @Test
    public void testLogin(){
        try {
            List<MyBookEnum> bookEnums=MyBookEnum.getChsjAllBooks();
            //creatPicText(bookEnums);
            searchFileByTextKey("商业插画",bookEnums);
        } catch (Exception e) {
            logger.error("测试失败",e);
        }
    }

}
