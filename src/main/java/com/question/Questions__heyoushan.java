package com.question;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @auth v_fanhaibo on   2018/2/9
 */
public class Questions__heyoushan {


    public static void main(String[] args) throws Exception {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES,true);
//        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
//
//        Map map = mapper.readValue("11:22", HashMap.class);
//        System.out.println(map);
//
//        String phoneMessage = getPhoneMessage("13430878244");
//        System.out.println(phoneMessage);

    }

    public static String getPhoneMessage( String phone) throws Exception, IOException{
        //手机归属地查询api
        //淘宝，返回json
        //https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=13430878244

        //拍拍，返回json
        //http://virtual.paipai.com/extinfo/GetMobileProductInfo?mobile=13430878244&amount=10000&callname=getPhoneNumInfoExtCallback

        //百度钱包，返回json的unicode
        //https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=13430878244
//
//        URI uri = new URIBuilder().setScheme("https").setHost("tcc.taobao.com")
//                .setPath("/cc/json/mobile_tel_segment.htm")
//                .setParameter("tel", phone).build();
//        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(uri);
//        CloseableHttpResponse closeableHttpResponse =  closeableHttpClient.execute(httpGet);
//        HttpEntity httpEntity = closeableHttpResponse.getEntity();
//        InputStream inputStream = httpEntity.getContent();
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
//        StringBuffer stringBuffer = new StringBuffer();
//        String text = null;
//        while((text =bufferedReader.readLine()) != null){
//            stringBuffer.append(text);
//        }
//        inputStream.close();
//        closeableHttpResponse.close();
//        String jsonString = stringBuffer.toString().split("=")[1].trim();//处理=号前的非json字符串
        
        String jsonString = "{" +
                "mts:'1343087'," +
                "province:'广东'," +
                "catName:'中国移动'," +
                "telString:'13430878244'," +
                "areaVid:'30517'," +
                "ispVid:'3236139'," +
                "carrier:'广东移动'" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//设置可用单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);//设置字段可以不用双引号包括
        JsonNode root = objectMapper.readTree(jsonString);
        return root.path("catName").asText() + root.path("carrier").asText();
    }
}
