package artifactExample.Resilient;

import java.util.List;

public class PostgresImpl implements IDataBaseService<Postgres> {

	@Override
	public Postgres getById(Long id) {
		Postgres postgres = new Postgres();
		postgres.setId(id);
		postgres.setDescription("Cualquier descripción de Postgres");
		return postgres;
	}

	@Override
	public List<Postgres> getAllRecords() {
		return null;
	}


}
