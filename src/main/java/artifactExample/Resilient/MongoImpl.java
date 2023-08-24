package artifactExample.Resilient;

import java.util.List;

public class MongoImpl implements IDataBaseService<Mongo> {

	@Override
	public Mongo getById(Long id) {
		Mongo mongo = new Mongo();
		mongo.setId(id);
		mongo.setDescription("Cualquier descripción de MONGO");
		return mongo;
	}

	@Override
	public List<Mongo> getAllRecords() {
		return null;
	}
	

}
