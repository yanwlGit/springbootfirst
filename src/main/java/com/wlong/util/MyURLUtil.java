package com.wlong.util;

import com.alibaba.fastjson.JSONObject;
import com.wlong.dodata.HttpBackDo;
import com.wlong.dodata.HttpSendParamDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MyURLUtil {

    private static Logger logger= LoggerFactory.getLogger(MyURLUtil.class);

    /**
     * http,https（Https 站的证书为机构所颁发的被认证的证书，非自定义证书） 均可使用
     * */
    public static HttpBackDo sendUrl(HttpSendParamDo httpSendParamDo){
        HttpBackDo httpBackDo=new HttpBackDo();
        OutputStream outFileStream=null;
        InputStream input=null;
        PrintWriter out = null;
        try{
            StringBuffer sb=new StringBuffer();
            //创建文件
            MyFileUtil.createFile(httpSendParamDo.getWirteFileStr());
            outFileStream=new FileOutputStream(httpSendParamDo.getWirteFileStr());
            URL url=new URL(httpSendParamDo.getUrl());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为3秒
            conn.setConnectTimeout(3*1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            if("true".equals(httpSendParamDo.getIsSetCookie())){
                conn.setRequestProperty("Cookie", httpSendParamDo.getCookieStr());
                httpBackDo.setCookieStr(httpSendParamDo.getCookieStr());
            }
            if(null!=httpSendParamDo.getContentType() && !"".equals(httpSendParamDo.getContentType())){
                conn.setRequestProperty("Content-Type", httpSendParamDo.getContentType());
            }
            if("POST".equals(httpSendParamDo.getGetOrPost())){
                //设置通用的请求属性
                conn.setRequestProperty("accept", "*/*");
                conn.setRequestProperty("connection", "keep-Alive");
                //发送POST请求必须设置如下两行
                conn.setDoOutput(true);
                conn.setDoInput(true);
            }
            if(null!=httpSendParamDo.getOtherParames()){
                // 获取URLConnection对象对应的输出流
                if("true".equals(httpSendParamDo.getIsJsonParams())){
                    conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                    out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
                    String jsonStr=JSONObject.toJSONString(httpSendParamDo.getOtherParames());
                    JSONObject jsonObject=JSONObject.parseObject(jsonStr);
                    // 发送请求参数
                    out.print(jsonObject.toJSONString());
                }else{
                    out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(),"UTF-8"));
                    String params=getParamsByMap(httpSendParamDo.getOtherParames());
                    // 发送请求参数
                    out.print(params);
                }
                // flush输出流的缓冲
                out.flush();
            }
            int code = conn.getResponseCode();
            if(200==code){
                //得到输入流
                input = conn.getInputStream();
                //2xxx响应成功 3xxx重定向 4xxx资源错误 5xxx服务器错误
                byte buffBytes[] = new byte[1024];
                int read = 0;
                while ((read = input.read(buffBytes)) != -1) {
                    sb.append(new String(buffBytes));
                    outFileStream.write(buffBytes, 0, read);

                }
                outFileStream.flush();
                if("true".equals(httpSendParamDo.getIsReaderCookie())){
                    //获取cookie
                    Map<String, List<String>> map=conn.getHeaderFields();
                    Set<String> set=map.keySet();
                    for (Iterator iterator = set.iterator(); iterator.hasNext();) {
                        String key = (String) iterator.next();
                        if ("Set-Cookie".equals(key)) {
                            List<String> list = map.get(key);
                            StringBuilder builder = new StringBuilder();
                            for (String str : list) {
                                builder.append(str).append(";");
                            }
                            String firstCookie=builder.toString();
                            httpBackDo.setCookieStr(firstCookie);
                        }
                    }
                }
                logger.info("请求成功---->"+httpSendParamDo.getUrl());
            }else{
                logger.info("请求失败状态码---->"+httpSendParamDo.getUrl()+","+code);
            }
            httpBackDo.setWriteFileStr(httpSendParamDo.getWirteFileStr());
            httpBackDo.setStatus(String.valueOf(code));
            httpBackDo.setReturnContentText(sb.toString());
        }catch (Exception e){
            logger.error("请求失败---->"+httpSendParamDo.getUrl(),e);
        }finally {
            try{
                if(outFileStream!=null){
                    outFileStream.close();
                }
                if(input!=null){
                    input.close();
                }
            }catch (Exception e){
                logger.error("读取返回结果错误",e);
            }
        }
        return httpBackDo;
    }

    public static String getParamsByMap(Map<String,String> map){
        StringBuffer sb=new StringBuffer();
        if(map!=null && !map.isEmpty()){
            Set<Map.Entry<String,String>> set=map.entrySet();
            Iterator<Map.Entry<String,String>> ite=set.iterator();
            String linkChar="&";
            while (ite.hasNext()){
                Map.Entry<String,String> entry=ite.next();
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append(linkChar);
            }
            if(sb.toString().endsWith("&")){
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return sb.toString();
    }

}
