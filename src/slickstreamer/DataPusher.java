package slickstreamer;


import com.turbomanage.httpclient.BasicHttpClient;
import com.turbomanage.httpclient.HttpResponse;
import com.turbomanage.httpclient.ParameterMap;

import java.util.Map;

public class DataPusher {


    public void push(String url, Map<String, String> data){
        BasicHttpClient httpClient = new BasicHttpClient(url);
        ParameterMap params = httpClient.newParams();
        params.putAll(data);

        httpClient.addHeader("name", "value");
        httpClient.setConnectionTimeout(2000);
        HttpResponse httpResponse = httpClient.post("", params);
    }

}
