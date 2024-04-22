package com.wlong.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum MyBookEnum {
    XJUGL_BOOK_3_1_1("设计学概论章节3-1书籍1",
            "1",
            MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\设计学概论\\3-1\\1",
            "https://pan-yz.chaoxing.com/screen/file_52dafe4daf1f9373580258fe9b62cb69?ext=%7B%22_from_%22%3A%22225060305_56138345_156078418_794e611a6579e3eb60692302c6812b0c%22%7D",
            MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\设计学概论\\3-1\\text\\1"
    ),
    XJUGL_BOOK_3_1_2("设计学概论章节3-1书籍2",
            "2",
            MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\设计学概论\\3-1\\2",
            "https://pan-yz.chaoxing.com/screen/file_a753c2ca0b1ce2f2fc0feff854b67ae0?ext=%7B%22_from_%22%3A%22225060305_56138345_156078418_794e611a6579e3eb60692302c6812b0c%22%7D",
            MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\设计学概论\\3-1\\text\\2"
    ),
    XJUGL_BOOK_3_1_3("设计学概论章节3-1书籍3",
            "3",
            MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\设计学概论\\3-1\\3",
            "https://pan-yz.chaoxing.com/screen/file_76189cd58f36a137fc99f20df09831d9?ext=%7B%22_from_%22%3A%22225060305_56138345_156078418_794e611a6579e3eb60692302c6812b0c%22%7D",
            MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\设计学概论\\3-1\\text\\3"
    ),
    CHSJ_BOOK_1_1("插画设计章节1-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-1",
            "https://pan-yz.chaoxing.com/screen/file_a85d7f8f1480ce698b3724ae5fe1a252?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-1\\text\\1"
    ),
    CHSJ_BOOK_1_2("插画设计章节1-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-2",
            "https://pan-yz.chaoxing.com/screen/file_cccb09319744c292bba72de4090f282a?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-2\\text\\1"
    ),
    CHSJ_BOOK_1_3("插画设计章节1-3书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-3",
            "https://pan-yz.chaoxing.com/screen/file_e1e00186d17ab177298cfece9806a99e?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-3\\text\\1"
    ),
    CHSJ_BOOK_1_4("插画设计章节1-4书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-4",
            "https://pan-yz.chaoxing.com/screen/file_662f3df4c2d2eff5c75c1ee5d7919f59?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\1-4\\text\\1"
    ),
    CHSJ_BOOK_2_1("插画设计章节2-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-1",
            "https://pan-yz.chaoxing.com/screen/file_57ef604de17d8c944a21a1878005e13b?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-1\\text\\1"
    ),
    CHSJ_BOOK_2_2("插画设计章节2-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-2",
            "https://pan-yz.chaoxing.com/screen/file_76f78828962ab4ead21450d3aefb1587?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-2\\text\\1"
    ),
    CHSJ_BOOK_2_3("插画设计章节2-3书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-3",
            "https://pan-yz.chaoxing.com/screen/file_f0bb069fd11f49c8cc6827d227298e09?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-3\\text\\1"
    ),
    CHSJ_BOOK_2_4("插画设计章节2-4书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-4",
            "https://pan-yz.chaoxing.com/screen/file_1023f0d0d61424388032ac322e94473b?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\2-4\\text\\1"
    ),
    CHSJ_BOOK_3_1("插画设计章节3-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-1",
            "https://pan-yz.chaoxing.com/screen/file_a1b57a6d2806503dc4901f468765580c?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-1\\text\\1"
    ),
    CHSJ_BOOK_3_2("插画设计章节3-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-2",
            "https://pan-yz.chaoxing.com/screen/file_4ea867e9ab406b582a3a9f432325ae8f?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-2\\text\\1"
    ),
    CHSJ_BOOK_3_3("插画设计章节3-3书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-3",
            "https://pan-yz.chaoxing.com/screen/file_f8966e8388b95b72f7bc0c7fc93c8963?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-3\\text\\1"
    ),
    CHSJ_BOOK_3_4("插画设计章节3-4书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-4",
            "https://pan-yz.chaoxing.com/screen/file_fb3f7b5f67b2c4ccfc8b7df2bf05dec8?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\3-4\\text\\1"
    ),
    CHSJ_BOOK_4_1("插画设计章节4-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-1",
            "https://pan-yz.chaoxing.com/screen/file_7d7fb25edce45f42630899bf682231c5?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-1\\text\\1"
    ),
    CHSJ_BOOK_4_2("插画设计章节4-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-2",
            "https://pan-yz.chaoxing.com/screen/file_40839affef327bcb2eeaebd37de39d54?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-2\\text\\1"
    ),
    CHSJ_BOOK_4_3("插画设计章节4-3书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-3",
            "https://pan-yz.chaoxing.com/screen/file_36ebc0cf2c05c4f644d8b9fb4f857144?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-3\\text\\1"
    ),
    CHSJ_BOOK_4_4("插画设计章节4-4书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-4",
            "https://pan-yz.chaoxing.com/screen/file_ec5b27b687f7d420d0a1d95c25ecff55?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-4\\text\\1"
    ),
    CHSJ_BOOK_4_5("插画设计章节4-5书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-5",
            "https://pan-yz.chaoxing.com/screen/file_c395d48b615a60eac2053bda95f3e740?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\4-5\\text\\1"
    ),
    CHSJ_BOOK_5_1("插画设计章节5-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\5-1",
            "https://pan-yz.chaoxing.com/screen/file_aa52dc9c8305968c139b505fd1fe0f13?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\5-1\\text\\1"
    ),
    CHSJ_BOOK_5_2("插画设计章节5-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\5-2",
            "https://pan-yz.chaoxing.com/screen/file_7e20858e607a38311cc70d32e035a533?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\5-2\\text\\1"
    ),
    CHSJ_BOOK_5_3("插画设计章节5-3书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\5-3",
            "https://pan-yz.chaoxing.com/screen/file_a6d8320134c9cd0a8d79958c9b34a397?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\5-3\\text\\1"
    ),
    CHSJ_BOOK_6_1("插画设计章节6-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\6-1",
            "https://pan-yz.chaoxing.com/screen/file_60b9f9798c658800c0847a96db4dd6dc?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\6-1\\text\\1"
    ),
    CHSJ_BOOK_6_2("插画设计章节6-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\6-2",
            "https://pan-yz.chaoxing.com/screen/file_9a3c4ecf64e8f0e2a01cfb2a9d6d179a?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\6-2\\text\\1"
    ),
    CHSJ_BOOK_6_3("插画设计章节6-3书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\6-3",
            "https://pan-yz.chaoxing.com/screen/file_e2682f7cbcbe66488d6bccf28127036b?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\6-3\\text\\1"
    ),
    CHSJ_BOOK_7_1("插画设计章节7-1书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\7-1",
            "https://pan-yz.chaoxing.com/screen/file_3d943aa676ee95de74c2623f3ec7e3d5?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\7-1\\text\\1"
    ),
    CHSJ_BOOK_7_2("插画设计章节7-2书籍1",
            "1",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\7-2",
            "https://pan-yz.chaoxing.com/screen/file_c309e92978a8769daacee461059329de?ext=%7B%22_from_%22%3A%22227181842_60171675_156078418_bd0da41ae9676dcc43dd05ebb8747850%22%7D",
            MyConstants.XUE_XI_TONG_BOOK_PATH+"\\插画设计\\7-2\\text\\1"
    )
    ;
    private String name;
    private String index;
    private String path;
    private String remoteUrl;
    private String textPath;

    private MyBookEnum(String name,String index,String path,String remoteUrl,String textPath){
        this.name=name;
        this.index=index;
        this.path=path;
        this.remoteUrl=remoteUrl;
        this.textPath=textPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public String getTextPath() {
        return textPath;
    }

    public void setTextPath(String textPath) {
        this.textPath = textPath;
    }

    /**设计学概论章节3-1包含的书籍*/
    public static  List<MyBookEnum> getSjxgl3_1Books(){
        List<MyBookEnum> list=new ArrayList<>();
        addBookByEnum(MyBookEnum.XJUGL_BOOK_3_1_1,list);
        addBookByEnum(MyBookEnum.XJUGL_BOOK_3_1_2,list);
        addBookByEnum(MyBookEnum.XJUGL_BOOK_3_1_3,list);
        return  list;
    }

    /**插画设计所有章节书籍*/
    public static  List<MyBookEnum> getChsjAllBooks(){
        List<MyBookEnum> list=new ArrayList<>();
        addBookByEnum(MyBookEnum.CHSJ_BOOK_1_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_1_2,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_1_3,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_1_4,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_2_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_2_2,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_2_3,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_2_4,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_3_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_3_2,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_3_3,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_3_4,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_4_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_4_2,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_4_3,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_4_4,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_4_5,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_5_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_5_2,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_5_3,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_6_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_6_2,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_6_3,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_7_1,list);
        addBookByEnum(MyBookEnum.CHSJ_BOOK_7_2,list);
        return  list;
    }

    private static void addBookByEnum(MyBookEnum book,List<MyBookEnum> list){
        File file=new File(book.getPath());
        if(!file.exists()){
            file.mkdirs();
        }
        list.add(book);
    }
}
