package JSONSTUDY;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
//封装发送接收方法
/*
 * 这是一个工具类用于发送，接收请求
 * */
public class  NetUtil {
    //发送请求的方法
    public static <K,V> String post(String URL, Map<K,V> param ){
        HttpURLConnection conn=null;
        StringBuffer sb=null;
        try {
            URL url=new URL(URL);
            conn=(HttpURLConnection) url.openConnection();
            if(param!=null){
                sb=new StringBuffer();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                OutputStream out=conn.getOutputStream();
                BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(out));
                //请求参数封装成键值对
                for (Map.Entry s:param.entrySet()){
                    sb.append(s.getKey()).append("=").append(s.getValue()).append("&");
                }
                writer.write(sb.deleteCharAt(sb.toString().length()-1).toString());
                writer.close();
                sb=null;
            }
            conn.connect();
            sb=new StringBuffer();
            int recode=conn.getResponseCode();
            //连接成功了
            if(recode==200){
                InputStream in=conn.getInputStream();
                BufferedReader reader=null;
                reader=new BufferedReader(new InputStreamReader(in));
                String str=null;
                while ((str=reader.readLine())!=null){
                    sb.append(str).append(System.getProperty("line.separator"));//后面那句相当于换行符
                }
                reader.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null){
                conn.disconnect();
            }
        }
        return String.valueOf(sb);
    }
    //接收请求的方法
    public static  String get(String url){
        return post(url,null);
    }
}
