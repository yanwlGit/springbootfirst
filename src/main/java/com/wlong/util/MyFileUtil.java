package com.wlong.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyFileUtil {

    private static Logger logger= LoggerFactory.getLogger(MyFileUtil.class);

    public static File createFile(String fileStr){
        try{
            File file=new File(fileStr);
            File parentFile=file.getParentFile();
            List<File> chilredFiles=getFileSort(parentFile.getPath(),false);
            if(chilredFiles.size()>=65535){
                chilredFiles.get(0).delete();
            }
            if(!file.exists()){
                file.createNewFile();
            }
            return file;
        }catch (Exception e){
            logger.error("创建文件错误",e);
        }
        return null;

    }

    /**
     * 获取目录下所有文件(按时间排序)
     *
     * @param path
     * @return
     */
    public static List<File> getFileSort(String path,boolean isContainChildrenDir) {

        List<File> list = getFiles(path, new ArrayList<>(),isContainChildrenDir);

        if (list != null && list.size() > 0) {

            Collections.sort(list, new Comparator<File>() {
                public int compare(File file, File newFile) {
                    if (file.lastModified() < newFile.lastModified()) {
                        return 1;
                    } else if (file.lastModified() == newFile.lastModified()) {
                        return 0;
                    } else {
                        return -1;
                    }

                }
            });

        }

        return list;
    }

    /**
     *
     * 获取目录下所有文件
     *
     * @param realpath
     * @param files
     * @return
     */
    public static List<File> getFiles(String realpath, List<File> files,boolean isContainChildrenDir) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory() && isContainChildrenDir==true) {
                    getFiles(file.getAbsolutePath(), files,isContainChildrenDir);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }

    public static void wirteFile(String content,File file){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(file))){
            pw.print(content);
        } catch (FileNotFoundException e) {
            logger.error("写入失败",e);
        }
    }
}
