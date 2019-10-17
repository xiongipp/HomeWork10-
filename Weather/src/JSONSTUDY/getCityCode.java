package JSONSTUDY;



import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;

//获取城市代码的工具类,这里我懒得写获取，直接把城市ID的json文件下了下来
public class getCityCode {
    public  static  String getCodeByName(String cityName) {
        File file=new File("C:\\Users\\Administrator\\eclipse-workspace\\Weather\\src\\CityCode\\citycode.json");
        String content=null;
        try {
           content = FileUtils.readFileToString(file,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将结果存到json数组
        JSONArray array= JSONArray.fromObject(content);


        for (Object o:array){
            JSONObject object=JSONObject.fromObject(o.toString());
            String cname=object.getString("city_name");
            if(cityName.equals(cname)){
                return object.getString("city_code");
            }
        }
        return null;
    }

   /* public static void main(String[] args) {
        System.out.println(getCodeByName("荆州市"));
    }*/


}
