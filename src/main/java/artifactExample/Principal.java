package artifactExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class Principal {

	//private static final String URL2 = "https://jsonplaceholder.typicode.com/posts";
	//private static final String URL_RANKING_GITHUB = "raw.githubusercontent.com/EvanLi/Github-Ranking/master/Data/github-ranking-2018-12-18.csv";
	
	private static Logger logger = Logger.getLogger(Principal.class);
	private static final String BASE_PATH = "E:\\Java\\Proyectos Java\\fileIO\\fileio\\artifactExample\\src\\main\\resources";
	private static final String FILE_PATH = "ficheroInfoZelda.txt";
	private static final String FILE_PATH_EXCEL = "reporte.xlsx";
	private static final String URL = "https://pokeapi.co/api/v2/pokemon/";
	private static final String USER = "carlos";
	private static final String PASSWORD = "ortiz";
	private static final String SECRET_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
	private static final String PATH_FILE_RANKING_GITHUB = "raw.githubusercontent.com_EvanLi_Github-Ranking_master_Data_github-ranking-2018-12-18.csv";
	
	public static void main(String[] args) {
		
		//LEER ARCHIVO
		leerArchivo(3, "JAVA");
		
		//Api Client with JWT
//		String token = createJwt(USER, PASSWORD);
//		String jsonData = requestPetition(token, USER , URL, "Charizard");
//		if(jsonData != null && !jsonData.isEmpty()) {
//			Gson gson = new Gson();
//			PokemonDTO pokemonDTO = gson.fromJson(jsonData, PokemonDTO.class);
//			if(pokemonDTO != null) {
//				logger.info("Datos Pokemón: ");
//				logger.info("Nombre: " + pokemonDTO.getName());
//				logger.info("Número: " + pokemonDTO.getOrder());
//				logger.info("Altura: " + pokemonDTO.getHeight());
//				logger.info("Habilidad 1: " + pokemonDTO.getAbilities().get(0).getAbility().getName());
//				logger.info("Habilidad 2: " + pokemonDTO.getAbilities().get(1).getAbility().getName());
//				logger.info("Area de encuentro: " + pokemonDTO.getLocation_area_encounters());
//				logger.info("Tipo 1: " + pokemonDTO.getTypes().get(0).getType().getName());
//				logger.info(pokemonDTO.getTypes().get(1).getType().getName() != null ? "Tipo 2: " + pokemonDTO.getTypes().get(1).getType().getName() : "");
//			}
//		}
	}
	
	private static void leerArchivo(int querySize, String lenguaje) {
		
		LinkedHashMap<Integer, String[]> resultMap = new LinkedHashMap<Integer, String[]>();
		
		File file = new File(BASE_PATH, PATH_FILE_RANKING_GITHUB);
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			List<String> rankingList = new ArrayList<String>();
			while(bufferedReader.ready()) {
				String line = bufferedReader.readLine();
				rankingList.add(line);
			}
			
			TreeMap<Integer, String[]> mapMenorMayor = new TreeMap<Integer, String[]>();
			for(String iterator : rankingList) {
				String[] arrayData = iterator.split(",");
				if(lenguaje.toLowerCase().trim().equalsIgnoreCase(arrayData[5].toLowerCase().trim())) {
					// Mapa [STARS / [DATA]]
					mapMenorMayor.put(Integer.parseInt(arrayData[3]), arrayData);
				}
			}
			
			NavigableMap<Integer, String[]> mapMayorMenor = mapMenorMayor.descendingMap();
			int cont = 0;
			for (Entry<Integer, String[]> entry : mapMayorMenor.entrySet()) {
				resultMap.put(entry.getKey(), entry.getValue());
			    cont++;
			    if(cont == querySize) {
			    	break;
			    }
			}
			//IMPRIMIR RESULTADOS.
			for (Entry<Integer, String[]> entry : resultMap.entrySet()) {
				logger.info("Rank: " + entry.getValue()[0]);
				logger.info("Item: " + entry.getValue()[1]);
				logger.info("Repo Name: " + entry.getValue()[2]);
				logger.info("Stars: " + entry.getValue()[3]);
				logger.info("Forks: " + entry.getValue()[4]);
				logger.info("Lenguaje: " + entry.getValue()[5]);
				logger.info("Repo URL: " + entry.getValue()[6]);
				logger.info("Username: " + entry.getValue()[7]);
				logger.info("Issues: " + entry.getValue()[8]);
				logger.info("Last Commit: " + entry.getValue()[9]);
				logger.info("Description: " + entry.getValue()[10]);
				logger.info("");
			}
		} catch (Exception e) {
			logger.error("Error al leer el archivo", e);
		}
	}

	public static String requestPetition2(String url) {
		String responseText = "";
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
				        .url(url)
				        .build();
			Response response = client.newCall(request).execute();
			responseText = response.body().string();
			logger.info("Respuesta API: " + responseText);
		} catch (Exception e) {
				logger.error("Error en la llamada a la api ", e);
		}
	     return responseText;
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

	public static void repeatWord(String word) {
		
		//Variables a usar
		HashMap<Integer, String> wordMap = new HashMap<>();
		int countWordRepeat = 0;
		String wordRepeat = "";
		
		//Paso la palabra a minuscula y genero el array con las palabras en base al espacio en blanco.
		String[] arrayWords = word.toLowerCase().split(" ");
		
		for(int i = 0; i < arrayWords.length; i++) {
			//Doble for para comparar las palabras 
			for(int j = 0 ; j < arrayWords.length; j++) {
				if(arrayWords[i].equalsIgnoreCase(arrayWords[j])) {
					wordRepeat = arrayWords[i];
					countWordRepeat++;
				}
			}
			wordMap.put(countWordRepeat, wordRepeat);
			countWordRepeat = 0;
			wordRepeat = "";
		}
		
		//Guardo las keys del mapa en un arrayList para posteriormente sortearlas.
		ArrayList<Integer> keys = new ArrayList<Integer>(wordMap.keySet());
		
		//SORT de keys
		Collections.sort(keys);
		
		//El mayor valor (LA CANTIDAD DE VECES QUE SE REPITE LA PALABRA) debe estar en la ultima posición.  
		int wordMostRepeat = keys.get(keys.size()-1);
		
		System.out.println("La palabra más repetida es: '" + wordMap.get(wordMostRepeat) + "' con: " + wordMostRepeat + " repeticiones");
		
	}
	
	public static String cleanArray(ArrayList<Object> array) {
		ArrayList<Object> elementForDelete = new ArrayList<Object>();
		for(Object iterator : array) {
			if(iterator == null) {
				elementForDelete.add(iterator);
				continue;
			}
			if(iterator.equals("0")) {
				elementForDelete.add(iterator);
				continue;
			}
		}
		array.removeAll(elementForDelete);
		return array.toString();
	}
	
	public static String checkWordPalindrom(String word) {
		String[] arrayOriginal = word.toLowerCase().split("");
		String[] arrayComparator = new String[word.length()];
		int j = 0;
		for(int i = arrayOriginal.length -1; i >= 0; i--) {
			arrayComparator[j] = arrayOriginal[i];
			j++;
		}
		if(Arrays.toString(arrayOriginal).equalsIgnoreCase(Arrays.toString(arrayComparator))) {
			return "La palabra: '" + word + "', SI es un palíndromo";
		}
		return "La palabra: '" + word + "', NO es un palíndromo";
	}
	
	public static int getBiggestNumber(int[] array) {
		int mayor = 0;
		for(int i = 0; i < array.length -1 ;i++) {
			if(array[i] > array[i+1]) {
				mayor = array[i];
			} else {
				mayor = array[i+1];
			}
		}
		return mayor;
	}
	
	public static int multiplyWithoutSymbol(int base , int multiply) {
		int result = 0;
		int i = 0;
		while(i < multiply) {
			result = result + base;
			i++;
		}
		return result;
	}

	public static void writeFile() {
		try {
			File folder = new File(BASE_PATH);
			if(!folder.exists()) {
				folder.mkdir();
				logger.info("Se crea nuevo directorio : '" + BASE_PATH + "' para escritura de archivo: '" + FILE_PATH +"'");
			}
			File file = new File(folder , FILE_PATH);
			//Acá no validamos que el fichero exista ya que si no está lo crearemos, solo validamos que no sea un directorio.
			if (file.isDirectory()) {
				throw new Exception("'" + file.getName() + "' es un directorio y no un fichero");
			}
			logger.info("Iniciando escritura de archivo: '" + file.getAbsolutePath() +"'");
			// El segundo parámetro true en el contructor de FileWriter es para añadir texto al ya existente en el archivo (append).
			FileWriter fileWriter = new FileWriter(file);//, true); // PrintWriter printWriter = new PrintWriter(fileWriter);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter); // BufferedWriter bufferedWriter = new BufferedWriter(printWriter);
			bufferedWriter.write("Título Videojuego\n"); // printWriter.println("");
			bufferedWriter.write("Año Lanzamiento\n");
			bufferedWriter.write("Consola Lanzamiento\n");
			ArrayList<String> listData = loadData();
			for(String iterator : listData) {
				bufferedWriter.write(iterator+"\n");
			}
			//String uuid = java.util.UUID.randomUUID().toString();
			//bufferedWriter.write(uuid + " " + LocalDateTime.now().format(DateTimeFormatter.ofPattern(("d/MM/yyyy HH:mm:ss")))); // 
			bufferedWriter.close(); // printWriter.close();
		} catch (Exception e) {
			logger.error("Error en escritura de archivo ", e);
		}
	}

	public static void readFile() {
		try {
			File file = new File(BASE_PATH, FILE_PATH);
			if (!file.exists()) {
				throw new Exception("Fichero : '" + file.getName() + "' NO existe , no se puede leer su contenido");
			}
			if (file.isDirectory()) {
				throw new Exception("'" + file.getName() + "' es un directorio y no un fichero");
			}
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			logger.info("Leyendo contenido de directorio: '" + file.getAbsolutePath() + "'");
			while (bufferedReader.ready()) {
				logger.info(bufferedReader.readLine());
			}
			bufferedReader.close();
		} catch (Exception e) {
			logger.error("Error en lectura de archivo ", e);
		}
	}

	private static void writeFileExcel() {
        try {
    		ArrayList<String> listTitle = new ArrayList<String>();
    		listTitle.add("Título Videojuego");
    		listTitle.add("Año Lanzamiento");
    		listTitle.add("Consola Lanzamiento");
    		
    		ArrayList<String> listData = loadData();
    		
            File file = new File(BASE_PATH , FILE_PATH_EXCEL);
            
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Videojuegos");
            
            Row row = sheet.createRow(0);
            int i = 0;
            for(String iteratorTitle : listTitle) {
                Cell cellTitle = row.createCell(i);
                cellTitle.setCellValue(iteratorTitle);
                sheet.autoSizeColumn(i);
                i++;
            }
            
            // Traspasar la data a array[] para poder operar mejor con los indices.
        	//String[] miArray = listData.toArray(new String[listData.size()]);
        	String[] miArray = listData.stream().toArray(String[] :: new);
        	int numberIterations = miArray.length / 3;
            
            int k = 0;
            for(int l = 0 ; l < numberIterations ; l++) {
            	// Se parte en la posicion 1 para no pisar la data de los titulos en el archivo excel.
            	Row rowData = sheet.createRow(l+1);
            	
                Cell cell1 = rowData.createCell(0);
                cell1.setCellValue(miArray[k]);
                
                Cell cell2 = rowData.createCell(1);
                cell2.setCellValue(miArray[k+1]);
                
                Cell cell3 = rowData.createCell(2);
                cell3.setCellValue(miArray[k+2]);
                
                // Se aumenta en 3 posiciones para saltar los espacios en el array y que no se repitan los datos en las filas del archivo excel.
                k = k + 3;
            }
            
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            logger.info("Archivo: '" + file.getName() + "' generado exitosamente");
            
        } catch(Exception e) {
        	logger.error("Error en creación de archivo excel.xlsx ", e);
        }
	}
	
	public static ArrayList<String> loadData(){
		ArrayList<String> listData = new ArrayList<>();
		listData.add("The Legend of Zelda: Ocarine of time");
        listData.add("1998");
		listData.add("Nintendo 64");
		
        listData.add("The Legend of Zelda: Majora's Mask");
        listData.add("2000");
		listData.add("Nintendo 64");
        
		listData.add("The Legend of Zelda: The Wind Waker");
        listData.add("2003");
		listData.add("Nintendo GameCube");
		
        listData.add("The Legend of Zelda: Twilight Princess");
        listData.add("2006");
        listData.add("Nintendo GameCube - Nintendo Wii");
        
        listData.add("The Legend of Zelda - Skyward Sword");
        listData.add("2011");
        listData.add("Nintendo Wii");
        
        listData.add("The Legend of Zelda - Breath of the wild");
        listData.add("2017");
        listData.add("Nintendo Wii U - Nintendo Switch");
        
        listData.add("The Legend of Zelda - Tears of the kingdom");
        listData.add("2023");
        listData.add("Nintendo Switch");
		return listData;
	}
	
	//JAVA IO
//	writeFile();
//	readFile();
//	writeFileExcel();
	
	//KATAS
//	logger.info(checkWordPalindrom("reconocer"));
//	logger.info(multiplyWithoutSymbol(25, 3));
//	int[] array = {3, 1, 4, 25, 73, 84, 914, 45712};
//	logger.info(getBiggestNumber(array));
//	ArrayList<Object> arrayList = new ArrayList<Object>();
//	arrayList.add(null);
//	arrayList.add("0");
//	arrayList.add(4758);
//	arrayList.add(35);
//	arrayList.add(null);
//	logger.info(cleanArray(arrayList));
//	
//	repeatWord("La luna de la tierra es pequeña en comparación con la luna de Jupiter y la luna de urano tierra tierra tierra tierra tierra");
	
//	Gson gson2 = new Gson();
//	Data[] rootData = gson2.fromJson(requestPetition2(URL2), Data[].class);
//	
//	for(Data iterator : rootData) {
//		System.out.println(iterator.getUserId());
//		System.out.println(iterator.getId());
//		System.out.println(iterator.getTitle());
//		System.out.println(iterator.getBody());
//	}
//	
//	List<String> gameList = Arrays.asList("Zelda", "Mario", "Donkey", "StarWars", "Final Fantasy", "One Piece");
//	List<String> gameList2 = gameList.stream()
//										.filter(e -> e.endsWith("ce"))
//										.map(e -> e)
//										.collect(Collectors.toList());
//	
//	for(String iterator : gameList2) {
//		System.out.println(iterator);
//	}
	

}