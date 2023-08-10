package artifactExample;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import Data.JwtDTO;

public class RequestRestHttpClient {
	
	private static Logger logger = Logger.getLogger(RequestRestHttpClient.class);
	
	private static final String USER = "carlos";
	private static final String PASSWORD = "ortiz";

	public static void main(String[] args) {
		
		Optional<HttpResponse<String>> result = apiGetToken(USER, PASSWORD);
		
		if(result.isEmpty()) {
			logger.info("No obtuvimos un token válido");
			return;
		}
		
		Gson gson = new Gson();
		JwtDTO jwt = gson.fromJson(result.get().body(), JwtDTO.class);
		Optional<HttpResponse<String>> optPersonList = apiGetListarPersonas(jwt.getJwtoken(), USER);
		
		if(optPersonList.isEmpty()) {
			logger.info("No obtuvimos un listado de personas");
			return;
		}
		
		String personList = optPersonList.get().body();
		logger.info(personList);
		
		//PETICION A API CON CUERPO
		apiCrearPersonas(jwt.getJwtoken(), USER);

	}
	
	public static Optional<HttpResponse<String>> apiGetToken(String user, String password) {
		String urlComplete = "http://localhost:8080/token/getToken/".concat(user.toLowerCase().trim()).concat("/").concat(password.toLowerCase().trim());
		logger.info("Inicio petición :  '"+ urlComplete +"'");
		try {
			HttpClient cliente = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(urlComplete))
					.GET()
					.build();
	        	
			HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
			if(response.statusCode() != 200) {
				logger.info("Resultado EstatusCode: " + response.statusCode());
				logger.info("Resultado petición : " + urlComplete + " = " + response.body() + "\n");
			} else {
				logger.info("Resultado EstatusCode: " + response.statusCode());
				logger.info("Resultado petición : " + urlComplete + " = " + response.body() + "\n");
		        return Optional.of(response);
			}
		} catch (Exception e) {
			logger.error("Error en la llamada: ", e);
		}
		return Optional.empty();
	} 
	
	public static Optional<HttpResponse<String>> apiGetListarPersonas(String token, String user) {
		String urlComplete = "http://localhost:8080/persona/listar";
		logger.info("Inicio petición :  '"+ urlComplete +"'");
		try {
			HttpClient cliente = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(urlComplete))
					.setHeader("token", token)
					.setHeader("user", user)
					.GET()
					.build();
	        	
			HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
			if(response.statusCode() != 200) {
				logger.info("Resultado EstatusCode: " + response.statusCode());
				logger.info("Resultado petición : " + urlComplete + " = " + response.body() + "\n");
			} else {
				logger.info("Resultado EstatusCode: " + response.statusCode());
				logger.info("Resultado petición : " + urlComplete + " = " + response.body() + "\n");
		        return Optional.of(response);
			}
		} catch (Exception e) {
			logger.error("Error en la llamada", e);
		}
		return Optional.empty();
	} 
	
	public static Optional<HttpResponse<String>> apiCrearPersonas(String token, String user) {
		String urlComplete = "http://localhost:8080/persona/create";
		logger.info("Inicio petición :  '"+ urlComplete +"'");
		try {
			
	        String json = new StringBuilder()
	                .append("{")
	                .append("\"nombre\":\"Carlos\",")
	                .append("\"apellido\":\"Ortiz desde java standalone\",")
	                .append("\"email\":\"email desde java standalone\",")
	                .append("\"telefono\":\"pruebaClienteRest\"")
	                .append("}").toString();
	        
			HttpClient cliente = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(urlComplete))
					.setHeader("token", token)
					.setHeader("user", user)
					.setHeader("Content-Type", "application/json")
					.POST(HttpRequest.BodyPublishers.ofString(json))
					.build();
	        	
			HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
			if(response.statusCode() != 200) {
				logger.info("Resultado EstatusCode: " + response.statusCode());
				logger.info("Resultado petición : " + urlComplete + " = " + response.body() + "\n");
			} else {
				logger.info("Resultado EstatusCode: " + response.statusCode());
				logger.info("Resultado petición : " + urlComplete + " = " + response.body() + "\n");
		        return Optional.of(response);
			}
		} catch (Exception e) {
			logger.error("Error en la llamada", e);
		}
		return Optional.empty();
	}

}
