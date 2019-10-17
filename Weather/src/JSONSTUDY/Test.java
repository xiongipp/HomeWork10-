package JSONSTUDY;

import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

public class Test {
    public  static String getTodayT(String cityname){
        String code=getCityCode.getCodeByName(cityname);
        String url="http://t.weather.sojson.com/api/weather/city/"+code;
        String info=NetUtil.get(url);
        XMLSerializer xmlSerializer=new XMLSerializer();
        //json字符串转为xml字符串
        String xml = xmlSerializer.write(JSONSerializer.toJSON(info));
        return xml;
    }

}
