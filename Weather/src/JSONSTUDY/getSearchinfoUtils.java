package JSONSTUDY;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * 这是一个根据xml字符串获取天气信息的工具类，返回值为map类型
 * */
public class getSearchinfoUtils {
    public static Map<Integer, Map<String, String>> getWeatherINFO(String xml) {
        Map<Integer, Map<String, String>> map2 = new HashMap<>();
        try {
            Document doc = null;
            doc = DocumentHelper.parseText(xml);
            Element rootElement = doc.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            //遍历根元素下的子元素
            while (iterator.hasNext()) {
                Element e = (Element) iterator.next();
                String name = e.getName();
                String text = e.getText();
                //得到城市信息
                if (name.equals("cityInfo")) {
                    String parent = null;
                    String city = null;
                    Map<String, String> maps = new HashMap<>();
                    Iterator iterator1 = e.elementIterator();
                    //遍历城市信息子元素,得到城市省份和城市名字
                    while (iterator1.hasNext()) {
                        Element e1 = (Element) iterator1.next();
                        if (e1.getName().equals("city")) {
                            parent = e1.getText();
                        }
                        if (e1.getName().equals("parent")) {
                            city = e1.getText();
                        }
                    }
                    maps.put("cityName", parent + city);//将城市信息存入一个map中
                    //将城市信息存入要返回信息的0号位子。
                    if (city != null || parent != null) {
                        map2.put(0, maps);
                    }

                }
                //15天天气预报的数组
                if (name.equals("data") && text.equals("")) {
                    Iterator iterator2 = e.elementIterator();
                    while (iterator2.hasNext()) {
                        Element e2 = (Element) iterator2.next();
                        if (e2.getName().equals("forecast")) {
                            Iterator iterator1 = e2.elementIterator();
                            //（这个是天气预报forecast的子元素e的遍历），每一个对e都代表一天，将15天的天气信息放入map2的1-15的位置
                            int i = 1;
                            while (iterator1.hasNext()) {
                                Element e3 = (Element) iterator1.next();
                                Iterator weather15 = e3.elementIterator();
                                //得到未来15天中一天的天气信息
                                Map<String, String> maps = new HashMap<>();
                                while (weather15.hasNext()) {
                                    Element forecastIfo = (Element) weather15.next();
                                    maps.put(forecastIfo.getName(), forecastIfo.getText());
                                }
                                map2.put(i, maps);
                                i++;
                            }
                        }
                    }
                }
                //查询时的时间放在16的位置
                if (name.equals("time")) {
                    Map<String, String> maps = new HashMap<>();
                    maps.put("searchTime", text);
                    map2.put(16, maps);
                }

            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map2;
    }
}
