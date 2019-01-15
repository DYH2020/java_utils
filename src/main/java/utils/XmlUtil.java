package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import utils.serializers.JsonSerializer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author daiyihua
 * @create 2019-01-15 2:07 PM
 * @desc xml 处理
 **/

public class XmlUtil {

    /**
     * 解析 xml 得到里面的内容
     * @param result
     * @return
     */
    public static Map<String, String> xmlAnalysis(String result){
        // 读取输入流
        Document doc = null;
        Map<String, String> retMap = new HashMap<>();
        try {
            doc = DocumentHelper.parseText(result);
            // 得到xml根元素
            Element root = doc.getRootElement();
            // 得到根元素的所有子节点
            @SuppressWarnings("unchecked")
            List<Element> elementList = root.elements();
            for (Element element : elementList) {
                retMap.put(element.getName(), element.getText());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return retMap;
    }

    public static Map<String, String> xmlAnalysis(HttpServletRequest request) throws DocumentException {

        Map<String, String> reMap = new HashMap<String, String>();
        BufferedReader reader = null;
        Document doc = null;
        StringBuilder sb = new StringBuilder();
        try {
            ServletInputStream in = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            doc = DocumentHelper.parseText(sb.toString());
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator("out_trade_no"); // 获取根节点下的子节点
            Iterator timeIter = rootElt.elementIterator("time_end");// 时间
            Iterator transaction = rootElt.elementIterator("transaction_id");// 交易流水
            Iterator resultCode = rootElt.elementIterator("result_code");// 交易流水
            // 遍历节点--商户订单
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String name = recordEle.getName();
                String values = recordEle.getStringValue();
                reMap.put(name, values);
            }
            while (timeIter.hasNext()) {
                Element recordEle = (Element) timeIter.next();
                String name = recordEle.getName();
                String values = recordEle.getStringValue();
                reMap.put(name, values);
            }
            while (transaction.hasNext()) {
                Element recordEle = (Element) transaction.next();
                String name = recordEle.getName();
                String values = recordEle.getStringValue();
                reMap.put(name, values);
            }
            while (resultCode.hasNext()) {
                Element recordEle = (Element) resultCode.next();
                String name = recordEle.getName();
                String values = recordEle.getStringValue();
                reMap.put(name, values);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reMap;

    }

    /**
    public static void main(String[] args){

        String str = "<xml><return_code><![CDATA[FAIL]]></return_code>\n" +
                "<return_msg><![CDATA[invalid bill_date]]></return_msg>\n" +
                "<error_code><![CDATA[20001]]></error_code>\n" +
                "</xml>";

        Map<String, String> map = xmlAnalysis(str);
        System.out.println(JsonSerializer.toJson(map));
        // 得到的结果
        //{"return_msg":"invalid bill_date","error_code":"20001","return_code":"FAIL"}

    }
    */
}
