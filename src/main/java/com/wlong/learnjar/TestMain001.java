package com.wlong.learnjar;

/*
* 1、maven 项目可以直接在项目目录下 执行maven 命令。
*    terminal 进入 D:\mygitproject\springbootfirst 执行
*    1、mvn clean 将旧的class字节码文件删除。
*    2、mvn package 打包,动态web工程打war包，Java工程打jar 包。
*    3、mvn install 将项目生成jar包放在仓库中，然后打包。
* 2、进入D:\mygitproject\springbootfirst\target>目录，执行打包后的文件（jar）
*    java -jar springbootfirst001-0.0.1-SNAPSHOT.jar
* */
public class TestMain001 {
    public static void main(String[]args){
        System.out.println("---- this is testMain001 ----");
    }
}
