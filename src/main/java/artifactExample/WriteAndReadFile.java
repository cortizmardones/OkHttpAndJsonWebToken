package artifactExample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteAndReadFile {
	
	private static Logger logger = Logger.getLogger(WriteAndReadFile.class);
	
	private static final String BASE_PATH = "E:\\Java\\Proyectos Java\\fileIO\\fileio\\artifactExample\\src\\main\\resources";
	private static final String FILE_PATH = "ficheroInfoZelda.txt";
	private static final String FILE_PATH_EXCEL = "reporte.xlsx";
	
	public static void main(String[] args) {
		
		//JAVA IO
		writeFile();
		readFile();
		writeFileExcel();
		
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

}
