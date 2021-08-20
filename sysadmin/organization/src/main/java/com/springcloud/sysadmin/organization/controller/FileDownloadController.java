package com.springcloud.sysadmin.organization.controller;

import com.springcloud.core.entity.vo.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("file/download")
public class FileDownloadController {

    public static Result<Boolean> download(String href) {
        System.out.println("--进来了--");
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        try {
            URL url = new URL(href);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream("/home/liangjinquan/桌面/a.html");

            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Result.success(true);
    }

    public static void main(String[] args) throws Exception{
        String wwwhtml = "https://blog.csdn.net/jjzhoulong/article/details/78622003/";

        String[] strs = wwwhtml.split("/");
        //  文件夹地址
        String dirName = strs[strs.length-1];
        String htmlCode = downloadWeb(wwwhtml);
        // m3u8下载文件地址
        String m3u8Url = getM3u8url(htmlCode);
        System.out.println("m3u8Url:"+m3u8Url);

        // 提取item
        List<String> items = parseM3u8(m3u8Url);
        String downUrl = m3u8Url.substring(0, m3u8Url.lastIndexOf("/"));
        String downloadDir = "/home/liangjinquan/桌面/"+dirName;
        File file = new File(downloadDir);
        if(!file.exists()){
            file.mkdirs();
        }
        for(String item : items){
            System.out.println(downUrl+"/"+item);
            File ts = new File(downloadDir+"/"+item);
            if(!ts.exists()) {
                ts.createNewFile();
                downloadFile(downUrl + "/" + item, downloadDir + "/" + item);
            }
        }
    }

    private static String getM3u8url(String htmlCode){
        String pattern = "\"http://.*m3u8\"";
        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);
        // 现在创建 matcher 对象
        Matcher m = r.matcher(htmlCode);
        if(m.find()) {
            return m.group(0).replace("\"","");
        }
        return null;
    }

   private static String downloadWeb(String href){
        try {
            URL url = new URL(href);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                result.append(line);
                result.append("\n");
            }
            reader.close();
            return result.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
   }

    /**
     * 解析m3u8
     * @param fileurl
     * @return
     */
    private static List<String> parseM3u8(String fileurl){
        List<String> list = new ArrayList<String>();
        try {
            URL url = new URL(fileurl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            StringBuffer result = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                if(!line.startsWith("#")){
                    list.add(line);
                }
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private static void downloadFile(String fileUrl, String saveFileUrl) {
        // 下载网络文件
        int bytesum = 0;
        int byteread = 0;

        try {
            URL url = new URL(fileUrl);
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            FileOutputStream fs = new FileOutputStream(saveFileUrl);

            byte[] buffer = new byte[1204];
            int length;
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread;
                fs.write(buffer, 0, byteread);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String fileUrl) {
        File file = new File(fileUrl);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
                sbf.append("\n");
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }
}
