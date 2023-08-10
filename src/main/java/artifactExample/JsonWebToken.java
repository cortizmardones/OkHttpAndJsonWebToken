package artifactExample;

import org.apache.log4j.Logger;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import pokemonDTO.PokemonDTO;

public class JsonWebToken {
	
	private static Logger logger = Logger.getLogger(JsonWebToken.class);
	
	private static final String USER = "carlos";
	private static final String PASSWORD = "ortiz";
	private static final String URL = "https://pokeapi.co/api/v2/pokemon/";
	private static final String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";

	public static void main(String[] args) {
		
		//Api Client with JWT
		String token = createJwt(USER, PASSWORD);
		String jsonData = requestPetition(token, USER , URL, "Charizard");
		if(jsonData != null && !jsonData.isEmpty()) {
			Gson gson = new Gson();
			PokemonDTO pokemonDTO = gson.fromJson(jsonData, PokemonDTO.class);
			if(pokemonDTO != null) {
				logger.info("Datos Pokemón: ");
				logger.info("Nombre: " + pokemonDTO.getName());
				logger.info("Número: " + pokemonDTO.getOrder());
				logger.info("Altura: " + pokemonDTO.getHeight());
				logger.info("Habilidad 1: " + pokemonDTO.getAbilities().get(0).getAbility().getName());
				logger.info("Habilidad 2: " + pokemonDTO.getAbilities().get(1).getAbility().getName());
				logger.info("Area de encuentro: " + pokemonDTO.getLocation_area_encounters());
				logger.info("Tipo 1: " + pokemonDTO.getTypes().get(0).getType().getName());
				logger.info(pokemonDTO.getTypes().get(1).getType().getName() != null ? "Tipo 2: " + pokemonDTO.getTypes().get(1).getType().getName() : "");
			}
		}

	}
	
	private static String createJwt(String user, String password) {
		String token = new String();
		if(!validateUserAndPass(user.toLowerCase().trim(), password.toLowerCase().trim())) {
			logger.error("Invalid credentials, a JWT will not be generated");
			return null;
		}
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			token = JWT.create()
					.withIssuer(user)
			        .sign(algorithm);
		} catch (Exception e) {
				logger.error("Invalid Signing configuration / Couldn't convert Claims.", e);
		}
		logger.info("Json Web Token generado: " + token);
		return token;
	}
	
	private static boolean validateUserAndPass(String user, String password) {
		return user.equalsIgnoreCase("carlos") && password.equalsIgnoreCase("ortiz");
	}
	
	public static String requestPetition(String token, String user, String urlBase, String pokemon) {
		String url = urlBase.concat(pokemon.toLowerCase().trim());
		String responseText = "";
		try {
			if(validateTokenJwt(token, user)) {
				OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder()
				        	.url(url)
				            .build();
			    Response response = client.newCall(request).execute();
			    responseText = response.body().string();
			    logger.info("Respuesta API: " + responseText);
			}
		} catch (Exception e) {
				logger.error("Error en la llamada a la api ", e);
		}
	     return responseText;
	}
	
	private static boolean validateTokenJwt(String token, String user) {
		logger.info("Token recibido para validar: " + token);
		DecodedJWT decodedJWT = null;
		if(token != null && !token.isEmpty()) {
			try {
			    Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
			    JWTVerifier verifier = JWT.require(algorithm)
			        .withIssuer(user)
			        .build();	        
			    decodedJWT = verifier.verify(token);
			    return token.equals(decodedJWT.getToken());
			} catch (JWTVerificationException e) {
				logger.error("Invalid signature/claims", e);
				return false;
			}
		} else {
			logger.error("Token Nulo , no se realizará request");
			return false;
		}
	}

}
