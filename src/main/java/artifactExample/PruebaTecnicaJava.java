package artifactExample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

public class PruebaTecnicaJava {
	
	private static Logger logger = Logger.getLogger(PruebaTecnicaJava.class);
	
	private static final String BASE_PATH = "E:\\Java\\Proyectos Java\\fileIO\\fileio\\artifactExample\\src\\main\\resources";
	private static final String PATH_FILE_RANKING_GITHUB = "raw.githubusercontent.com_EvanLi_Github-Ranking_master_Data_github-ranking-2018-12-18.csv";
	//private static final String URL_RANKING_GITHUB = "raw.githubusercontent.com/EvanLi/Github-Ranking/master/Data/github-ranking-2018-12-18.csv";
	
	public static void main(String[] args) {
		
		//LEER ARCHIVO
		leerArchivo(2, "CSS");
	}
	
	private static void leerArchivo(int querySize, String lenguaje) {
		
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
				String[] arrayData = iterator.split(";");
				if(lenguaje.toLowerCase().trim().equalsIgnoreCase(arrayData[5].toLowerCase().trim())) {
					// Mapa [STARS / [DATA]]
					mapMenorMayor.put(Integer.parseInt(arrayData[3]), arrayData);
				}
			}
			
			NavigableMap<Integer, String[]> mapMayorMenor = mapMenorMayor.descendingMap();
			
			int cont = 0;
			LinkedHashMap<Integer, String[]> resultMap = new LinkedHashMap<Integer, String[]>();
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

}
