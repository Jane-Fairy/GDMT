package com.utils;

import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class FileUtils {

    //文件上传
    public String upload(MultipartFile upload, Integer uid) throws IOException {
        //上传路径（linux）
        String path="/usr/gdmt/"+uid;
        //上传路径（windows测试）
        //String path="D:/upload/"+uid;
        //子目录序号
        int indexId=1;
        File file=new File(path);
        //父目录不存在，创建父目录
        if(!file.exists()){
            file.mkdirs();
        }else {
            File indexFile;
            //父目录存在但无子目录，创建第一个子目录
            if(file.listFiles().length==0){
                indexFile=new File(path+"/1");
                indexFile.mkdir();
                path=indexFile.getAbsolutePath();
            }else {
                //存在子目录，获取最后一个子目录
                indexFile=file.listFiles()[file.listFiles().length-1];
                //文件数量超过x，新建子目录。否则上传到当前子目录
                if (indexFile.listFiles().length>=200){
                    path=path+"/"+(file.listFiles().length+1);
                    indexFile=new File(path);
                    indexFile.mkdir();
                    indexId=file.listFiles().length+1;
                }else {
                    path=indexFile.getAbsolutePath();
                    indexId=file.listFiles().length;
                }
            }
        }

        String fileName=upload.getOriginalFilename();
        String uuid= UUID.randomUUID().toString().replace("-","");
        fileName=uuid+"-"+fileName;
        upload.transferTo(new File(path,fileName));
        return path+"/"+fileName;
    }

    //文件删除
    public void delFile(String path){
        File file=new File(path);
        if(file.exists()){
            file.delete();
        }
    }

    //文件下载
    public void down(String filePath, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取文件名
        String fileName=filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf(".")).trim();
        //获取文件类型
        String fileType=filePath.substring(filePath.lastIndexOf(".")+1).trim();
        //设置content-type，即告诉客户端所发送的数据属于什么类型
        response.setHeader("content-type",fileType);
        response.setContentType("application/octet-stream");
        //设置编码，让浏览器识别正确的文件名
        String fnCharset=new String(fileName.getBytes("utf-8"),"iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型
        response.setHeader("Content-Disposition","attachment;filename="+fnCharset);

        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        byte[] buff = new byte[1024*1024];
        try {
            outputStream = response.getOutputStream();
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int read = bis.read(buff);
            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
