package com.xyqproject.first.myfirstprodect;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyfirstprodectApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	public <T> boolean sendInfo(T you) {
		Boolean resultStatus = Boolean.FALSE;
		try {
			//连接服务器
			URL url = new URL("you url");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.connect();
			
			//创建输入流
			OutputStream os = http.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			
			//创建对象
			JSONArray jsonArray = new JSONArray();
			JSONObject jsonObject = new JSONObject();

			 
            jsonObject.put("username",you);  //写入对象数据
            jsonArray.put(jsonObject);
            bw.write(jsonArray.toString());
            bw.flush();
            
         // (3)数据读取流接收数据
            InputStream is = http.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            String result = br.readLine();  //获取web 端返回的数据
            if (result.equals("succeed")) {  //如果返回数据为 succeed 处理成功，否则失败
                resultStatus = true;
            }
 
            // (4)关闭相关流
            if (os != null)  os.close();
            if (osw != null)  osw.close();
            if (is != null)  is.close();
            if (isr != null)  isr.close();
            if (br != null)  br.close();
            if (bw != null)  bw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStatus;
	}
}
