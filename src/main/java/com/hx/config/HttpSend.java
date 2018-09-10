package com.hx.config;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/*发送文件
* */
public class HttpSend {
    /**
     *
     * 功能描述:发送文件
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/10 15:57
     */
    public static String sendHttp(@RequestParam("file") MultipartFile file,String Ipall,
                       HttpServletRequest request) {
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //接收文件的路径
        HttpPost post = new HttpPost("http://"+Ipall+":8080/receiveData.do");//httpclienttofile_war  http://172.16.107.205:8080/httptwo.do
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();//设置请求和传输超时时间
        post.setConfig(requestConfig);
        String fileName = file.getOriginalFilename();
        InputStream inputStream=null;
        try {
            inputStream = file.getInputStream();
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName("utf-8"));
            //第一个参数为 相当于 Form表单提交的file框的name值 第二个参数就是我们要发送的InputStream对象了
            //第三个参数是文件名
            //3)
            builder.addBinaryBody("file", inputStream, ContentType.create("multipart/form-data"), fileName);
            //4)构建请求参数 普通表单项
            StringBody stringBody = new StringBody("12", ContentType.MULTIPART_FORM_DATA);
            builder.addPart("id", stringBody);
            HttpEntity entity = builder.build();
            post.setEntity(entity);
            //发送请求
            HttpResponse response = client.execute(post);
            entity = response.getEntity();
            if (entity != null) {
                inputStream = entity.getContent();
                //转换为字节输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Consts.UTF_8));
                String body = null;
                while ((body = br.readLine()) != null) {
                    System.out.println(body);
                }
            }
            return "成功";
        } catch (IOException e) {
            e.printStackTrace();
            return "失败";
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "失败";
                }
            }
        }
    }
    /**
     *
     * 功能描述: 发送文件的其他数据
     *
     * @param:
     * @return:
     * @auther: 张立恒
     * @date: 2018/9/10 15:57
     */
    public static String HttpPostWithJson(String Ipall, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try{
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost("http://"+Ipall+":8080/receiveData.do");
            //第三步：给httpPost设置JSON格式的参数
            StringEntity se = new StringEntity(json.toString());
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");
            httpPost.setEntity(se);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String resData = EntityUtils.toString(response.getEntity());
            httpClient.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "发送失败";
        }
        finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "发送失败";
            }
        }
        //第五步：处理返回值
        return "发送成功";
    }

}
