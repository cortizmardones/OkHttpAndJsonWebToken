package artifactExample.callRest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import Data.Data;

public class OkHttpClients {
	
	private static Logger logger = Logger.getLogger(OkHttpClients.class);
	
	private static final String URL = "https://jsonplaceholder.typicode.com/posts";
	
	public static void main(String[] args) {
		
		Gson gson2 = new Gson();
		Data[] rootData = gson2.fromJson(requestPetition(URL), Data[].class);
		for(Data iterator : rootData) {
			System.out.println("UserId: " + iterator.getUserId());
			System.out.println("Id: " + iterator.getId());
			System.out.println("Title: " + iterator.getTitle());
			System.out.println("Body: " + iterator.getBody() + "\n");
		}
	}
	
	public static String requestPetition(String url) {
		String responseText = "";
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
				        .url(url)
				        .build();
			Response response = client.newCall(request).execute();
			responseText = response.body().string();
			logger.info("Respuesta API: " + responseText + "\n");
		} catch (Exception e) {
				logger.error("Error en la llamada a la api ", e);
		}
	     return responseText;
	}

}
