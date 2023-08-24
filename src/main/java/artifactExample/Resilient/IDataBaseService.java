package artifactExample.Resilient;

import java.util.List;

public interface IDataBaseService<T> {
	
	T getById(Long id);
	List<T> getAllRecords();
	
	// NO GENERICO
	//String getById(Long id);
	//List<String> getAllRecords();

}
