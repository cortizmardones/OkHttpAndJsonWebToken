package artifactExample.Resilient;

public class MainPrincipalExecution {

	public static void main(String[] args) {
		
		MongoImpl mongo = new MongoImpl();
		PostgresImpl postgres = new PostgresImpl();
		
		System.out.println(mongo.getById(20L));
		System.out.println(postgres.getById(30L));

	}

}
