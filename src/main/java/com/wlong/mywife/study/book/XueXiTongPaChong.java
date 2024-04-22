package com.wlong.mywife.study.book;




import com.alibaba.fastjson.JSON;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.wlong.dodata.HttpBackDo;
import com.wlong.dodata.HttpSendParamDo;
import com.wlong.util.MyBookEnum;
import com.wlong.util.MyConstants;
import com.wlong.util.MyURLUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.Duration;
import java.util.*;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class XueXiTongPaChong {

    private static Logger logger=LoggerFactory.getLogger(XueXiTongPaChong.class);
    private static final  String PASS_WORD="Yuelianwu1";
    private static final  String USER_NAME="15168644888";


    public HttpBackDo login(){
        File parentFile=new File(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH);
        if(!parentFile.exists() || !parentFile.isDirectory()){
            parentFile.mkdirs();
        }
        //获取登录首页
        HttpSendParamDo httpSendParamDo=new HttpSendParamDo();
        httpSendParamDo.setUrl("https://i.chaoxing.com:443/");
        httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\login.html");
        HttpBackDo httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);
        File htmlFile=new File(httpSendParamDo.getWirteFileStr());
        try {
            //登录 http://127.0.0.1:9091/qian/testJson
            httpSendParamDo.setUrl("https://passport2.chaoxing.com:443/fanyalogin");
            httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\loginSuccess.html");
            httpSendParamDo.setGetOrPost("POST");
            httpSendParamDo.setPassword(PASS_WORD);
            httpSendParamDo.setUserName(USER_NAME);
            httpSendParamDo.setIsReaderCookie("true");
            Document doc = Jsoup.parse(htmlFile,"UTF-8");
            Map<String,String> params=new HashMap<>();
            String validate=doc.getElementById("validate").attr("value");
            String t=doc.getElementById("t").attr("value");
            params.put("fid",doc.getElementById("fid").attr("value"));
            params.put("uname",httpSendParamDo.getUserName());
            params.put("password",httpSendParamDo.getPassword());
            params.put("refer",doc.getElementById("refer").attr("value"));
            params.put("forbidotherlogin",doc.getElementById("forbidotherlogin").attr("value"));
            if("validate".equals(validate) || null==validate || "null".equals(validate) || "NULL".equals(validate)){
                validate="";
            }
            if("true".equals(t)){
                //js $.base64.btoa(PASS_WORD,"UTF-8")
                httpSendParamDo.setPassword("WXVlbGlhbnd1MQ==");
            }
            params.put("validate",validate);
            httpSendParamDo.setOtherParames(params);
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);
            //成功后请求首页
            //js decodeURIComponent("https://i.chaoxing.com")
            String cookieStr=httpBackDo.getCookieStr();
            httpSendParamDo.setIsReaderCookie("");
            httpSendParamDo.setIsSetCookie("true");
            httpSendParamDo.setCookieStr(cookieStr);
            httpSendParamDo.setUrl("https://i.chaoxing.com");
            httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\index.html");
            httpSendParamDo.setGetOrPost("GET");
            httpSendParamDo.setOtherParames(null);
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);
        } catch (IOException e) {
            logger.error("解析html错误",e);
        }
        return httpBackDo;
    }

    //读取 设计学概论(视觉）书本信息
    public void readBook(String name,String enName){
        HttpBackDo httpBackDo= login();
        Document doc = null;
        try {
            //点击课程
            doc = Jsoup.parse(new File(httpBackDo.getWriteFileStr()),"UTF-8");
            Element bookElement=doc.getElementById("first2388775");
            String urlKc=bookElement.attr("dataurl");
            HttpSendParamDo httpSendParamDo=new HttpSendParamDo();
            httpSendParamDo.setUrl(urlKc);
            httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\kc.html");
            httpSendParamDo.setIsSetCookie("true");
            httpSendParamDo.setCookieStr(httpBackDo.getCookieStr());
            httpSendParamDo.setContentType("text/html;charset=UTF-8");
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);


            //点击我学的课
            Map<String,String>params=new HashMap<>();
            doc = Jsoup.parse(new File(httpBackDo.getWriteFileStr()),"UTF-8");
            params.put("courseFolderId",doc.getElementById("courseFolderId").attr("value"));
            //doc.getElementById("courseType").attr("value")
            params.put("courseType","1");//默认查询我学的课
            params.put("courseFolderSize",doc.getElementById("courseFolderSize").attr("value"));
            params.put("baseEducation",doc.getElementById("baseEducation").attr("value"));

            httpSendParamDo.setUrl("https://mooc1-1.chaoxing.com/visit/courselistdata");
            httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\wdkc.html");
            httpSendParamDo.setIsSetCookie("true");
            httpSendParamDo.setCookieStr(httpBackDo.getCookieStr());
            httpSendParamDo.setGetOrPost("POST");
            httpSendParamDo.setOtherParames(params);
            //模拟form表单的方式提交，使用爬虫不清楚服务器接收参数类型，需要测试

            /*  data里直接写入参数，不使用json,url连接后不拼接参数，使用类型
                application/x-www-form-urlencoded; charset=UTF-8
                $.ajax({
                    url : "/visit/courselistdata",
                    type : "post",
                    data : {
                        courseType : courseType,
                        courseFolderId : courseFolderId,
                        baseEducation : baseEducation,
                        courseFolderSize : courseFolderSize
                    },
                    dataType : "html",
                    success : function(data){

                    }
                });
            */
            httpSendParamDo.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);

            //点击某本书
            doc = Jsoup.parse(new File(httpBackDo.getWriteFileStr()),"UTF-8");
            Elements elements=doc.getElementsByAttributeValue("class","course-name overHidden2");
            String bookName=name.replace("(","").replace(")","")
                    .replace("（","").replace("）","");
            Element elBook=null;
            for(Element any:elements){
                String anyBookName=any.ownText();
                String nodeName=any.nodeName();
                if(null!=anyBookName){
                    anyBookName=anyBookName.replace("(","").replace(")","")
                            .replace("（","").replace("）","");
                }
                if("span".equals(nodeName) && (name.equals(any.ownText()) || bookName.equals(anyBookName))){
                    elBook=any;
                    break;
                }
            }
            Element parentBook=elBook.parent();
            String url=parentBook.attr("href");
            httpSendParamDo.setIsReaderCookie("");
            httpSendParamDo.setIsSetCookie("true");
            httpSendParamDo.setCookieStr(httpBackDo.getCookieStr());
            httpSendParamDo.setUrl(url);
            httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\"+enName+".html");
            httpSendParamDo.setGetOrPost("GET");
            httpSendParamDo.setOtherParames(null);
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);

            //点击章节 course-stu.js
            String zjUrl="https://mooc2-ans.chaoxing.com/mycourse/studentcourse?";
            doc = Jsoup.parse(new File(httpBackDo.getWriteFileStr()),"UTF-8");
            String courseid=doc.getElementById("courseid").attr("value");
            String clazzid=doc.getElementById("clazzid").attr("value");
            String cpi=doc.getElementById("cpi").attr("value");
            zjUrl=zjUrl+"courseid=" + courseid + "&clazzid=" + clazzid + "&cpi=" + cpi + "&ut=s";
            params.put("courseid",courseid);
            params.put("clazzid",clazzid);
            params.put("cpi",cpi);
            params.put("ut","s");
            httpSendParamDo.setUrl(zjUrl);
            httpSendParamDo.setWirteFileStr(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\"+enName+"-zj.html");
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);

            //点击章节下的小节
            int index=0;
            String enc=doc.getElementById("oldenc").attr("value");
            doc = Jsoup.parse(new File(httpBackDo.getWriteFileStr()),"UTF-8");
            elements=doc.getElementsByAttributeValue("class","chapter_item");
            Element anyDiv=null;
            for(Element any:elements){
                if(!"".equals(any.attr("onclick"))){
                    anyDiv=any;
                    index++;
                    break;
                }
            }
            File bookFile=new File(MyConstants.MY_TEST_XUE_XI_TONG_BOOK_PATH+"\\"+enName+"-zj-"+index);
            if(!bookFile.exists()){
                bookFile.mkdirs();
            }
            String onClicFunction=anyDiv.attr("onclick");
            String[] functionParams=replaceFunction(onClicFunction).split(",");
            courseid=functionParams[0];
            String knowledgeId=functionParams[1];
            clazzid=functionParams[2];
            String referUrl="https://mooc1.chaoxing.com/mycourse/studentstudy?";
            referUrl=referUrl+"chapterId=" + knowledgeId + "&courseId=" + courseid + "&clazzid="
                    + clazzid + "&enc="+enc+ "&mooc2=1";
            String transferUrl="https://mooc1.chaoxing.com/mycourse/transfer?";
            transferUrl=transferUrl+"moocId="+ courseid + "&clazzid=" + clazzid +
                    "&ut=s&refer=" + URLEncoder.encode(referUrl,"UTF-8");
            httpSendParamDo.setUrl(transferUrl);
            httpSendParamDo.setWirteFileStr(bookFile.getPath()+File.separator+"index.html");
            httpBackDo=MyURLUtil.sendUrl(httpSendParamDo);
        } catch (IOException e) {
            logger.error("读取文件错误",e);
        }



    }

    public static String replaceFunction(String fstr){
        int startFunc=fstr.indexOf("(");
        int endFunc=fstr.indexOf(")");
        if(startFunc<0){
            startFunc=fstr.indexOf("（");
        }
        if(endFunc<0){
            endFunc=fstr.indexOf("）");
        }
        fstr=fstr.substring(startFunc+1,endFunc).replace("'","")
                .replaceAll(",\\s+",",").trim();
        return fstr;
    }

    public static Object getScriptVal(){

        Object result = null;

        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            //ueditor.parse 和原生的html js 有区别 所以使用爬虫实现
            /*engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\jquery-1.7.2.min.js"));
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\jquery.nicescroll.min.js"));
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\jquery.color.js"));
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\jquery.md5.js"));
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\domain.js"));
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\ServerHost.js"));
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\all-classes.js"));*/
            engine.eval(new FileReader("C:\\Users\\80603\\Desktop\\my\\sd\\js\\mytest.js"));
            Invocable invocable = (Invocable) engine;

            result = invocable.invokeFunction("fun1", "Peter Parker");
        } catch (Exception e) {
            logger.error("执行脚本错误",e);
        }
        System.out.println(result);
        System.out.println(result.getClass());
        return result;
    }

    public static HtmlUnitDriver getWebDriver(){
        // 使用HtmlUnitDriver 是不需要 安装 浏览器 和 驱动支持
        //WebDriver driver = new HtmlUnitDriver();
        HtmlUnitDriver webDriver = new HtmlUnitDriver(BrowserVersion.CHROME);

        webDriver.setJavascriptEnabled(true); // 设置支持 js脚本解析 ,是不是跟 安卓的 webview设置很像?
        // 这里和浏览器 地址栏目 可以输入的 地址一样 支持 远程地址 和 本地地址
        //file:///C:/Users/80603/Desktop/my/testhtml/test.html
        webDriver.get("https://i.chaoxing.com:443/");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement userNameElement = webDriver.findElement(By.id("phone"));
        userNameElement.sendKeys(USER_NAME);
        WebElement pwdElement = webDriver.findElement(By.ByCssSelector.cssSelector("input.ipt-pwd"));
        pwdElement.sendKeys(PASS_WORD);
        //testSeleniumSelect(webDriver);
        WebElement submitElement = webDriver.findElement(By.id("loginBtn"));
        submitElement.click();//登录
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        //判断是否⾄少有1个元素存在于dom树中，如果定位到就返回列表
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.ByCssSelector.cssSelector("span.icon-uniE900")));
        return webDriver;
    }

    public static void downLoadTest(){
        //获取首页
        HtmlUnitDriver webDriver=getWebDriver();

        /*webDriver.switchTo().frame("frame_content");
        webDriverWait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        //判断是否⾄少有1个元素存在于dom树中，如果定位到就返回列表
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.ByCssSelector.cssSelector(".course-list")));


        List<WebElement>listBooks=webDriver.findElements(By.className("color1"));
        WebElement bookElement=null;
        for(WebElement book:listBooks){
            String queryStr=replaceFunction("设计学概论(视觉)");
            WebElement courseNameElement=book.findElement(By.className("course-name"));
            String bookText=replaceFunction(courseNameElement.getAttribute("title"));
            if(bookText.indexOf(queryStr)>-1){
                bookElement=book;
                break;
            }
        }
        bookElement.click();//点击某本书
        webDriverWait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        //判断是否⾄少有1个元素存在于dom树中，如果定位到就返回列表
        //frame_content-zj ByCssSelector.cssSelector("i.zj")
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.id("frame_content-hd")));

        webDriver.switchTo().frame("frame_content-hd");
        webDriverWait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        //判断是否⾄少有1个元素存在于dom树中，如果定位到就返回列表
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.ByCssSelector.cssSelector("div.status")));

        webDriver.switchTo().parentFrame();
        webDriverWait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        //判断是否⾄少有1个元素存在于dom树中，如果定位到就返回列表
        //frame_content-zj ByCssSelector.cssSelector("i.zj")
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.ByCssSelector.cssSelector("i.zj")));*/

        /*Set<String> iframes=webDriver.getWindowHandles();
        for(String iframeName:iframes){
            System.out.println("我是一个iframe-------------->"+iframeName);
        }
        String pageHtml=webDriver.getPageSource();
        File file=new File("C:\\Users\\80603\\Desktop\\my\\ce2.txt");
        MyFileUtil.wirteFile(pageHtml,file);*/
    }

    public static void downLoadBookPic(List<MyBookEnum> books) throws InterruptedException {
        //获取首页
        HtmlUnitDriver webDriver=getWebDriver();
        for(MyBookEnum bookEnum:books){
            File file=new File(bookEnum.getPath());
            if(file.listFiles().length>0){
                System.out.println("当前文件夹下已下载过,请确认-------------->"+bookEnum.getPath());
                System.out.println("对应远程路径下载地址--------------------->"+bookEnum.getRemoteUrl());
            }else{
                webDriver.get(bookEnum.getRemoteUrl());
                Thread.sleep(1000*10);
                downLoadByEnum(webDriver,bookEnum);
            }
        }
        webDriver.close();

    }

    public static  void downLoadByEnum(HtmlUnitDriver webDriver,MyBookEnum bookEnum) throws InterruptedException {
        List<WebElement>listBookMsg=webDriver.findElements(By.tagName("img"));
        String cookieStr=concatCooliesByDriver(getCookiesByDriver(webDriver));
        HttpSendParamDo httpSendParamDo=new HttpSendParamDo();
        httpSendParamDo.setIsReaderCookie("");
        httpSendParamDo.setIsSetCookie("true");
        httpSendParamDo.setCookieStr(cookieStr);
        httpSendParamDo.setContentType("application/x-www-form-urlencoded");
        httpSendParamDo.setGetOrPost("GET");
        httpSendParamDo.setOtherParames(null);
        for(WebElement img:listBookMsg){
            String url=img.getAttribute("src");
            if(null!=url && !"".equals(url) && url.endsWith(".png")){
                String pngName=url.substring(url.lastIndexOf("/")+1);
                httpSendParamDo.setUrl(url);
                httpSendParamDo.setWirteFileStr(bookEnum.getPath()+File.separator+pngName);
                MyURLUtil.sendUrl(httpSendParamDo);
            }
        }
    }

    public static void addCookie(String cookieJsonStr,HtmlUnitDriver webDriver){
        List<Cookie> list=JSON.parseArray(cookieJsonStr,Cookie.class);
        if(webDriver!=null){
            for(Cookie s:list){
                webDriver.manage().addCookie(s);
            }

        }


    }
    public static void testSeleniumlocal() throws InterruptedException {
        // 使用HtmlUnitDriver 是不需要 安装 浏览器 和 驱动支持
        //WebDriver driver = new HtmlUnitDriver();
        HtmlUnitDriver webDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
        webDriver.setJavascriptEnabled(true); // 设置支持 js脚本解析 ,是不是跟 安卓的 webview设置很像?
        // 这里和浏览器 地址栏目 可以输入的 地址一样 支持 远程地址 和 本地地址
        //file:///C:/Users/80603/Desktop/my/testhtml/test.html
        webDriver.get("file:///C:/Users/80603/Desktop/my/sd/aaa.html");

        /*WebElement testElement = webDriver.findElement(By.id("aaa"));
        testElement.click();*/
        WebDriverWait webDriverWait=new WebDriverWait(webDriver,Duration.ofSeconds(10));
        //判断某个元素是否被添加到了dom⾥并且可见，可见代表元素可显⽰且宽和⾼都⼤于
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated((By.id("topicList"))));
        /*webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("div[class='ans-attach-ct']")));*/
        String aaa=webDriver.getPageSource();
        System.out.println("-------------->"+aaa);
        webDriver.close();
    }

    public static void testSeleniumSelect(HtmlUnitDriver webDriver){
        List<WebElement> testWenElements=webDriver.findElements(By.ByCssSelector.cssSelector("lg-item"));
        for(WebElement any:testWenElements){
            System.out.println(any.toString());
        }
    }

    public static Set<Cookie> getCookiesByDriver(HtmlUnitDriver webDriver){
        Set<Cookie>cookieSet=webDriver.manage().getCookies();
        return cookieSet;
    }

    public static String concatCooliesByDriver(Set<Cookie>cookieSet){
        String aa=JSON.toJSONString(cookieSet);
        return aa;
    }
    @Test
    public void testLogin(){
        try {
            //readBook("插画设计","chsj");//自动化网页登录有修改，暂不使用
            /*ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval("print('Hello World!');");
            getScriptVal();
            testSeleniumlocal();*/
            downLoadBookPic(MyBookEnum.getChsjAllBooks());//直接使用书籍地址下载

        } catch (Exception e) {
            logger.error("测试失败",e);
        }
    }
}
