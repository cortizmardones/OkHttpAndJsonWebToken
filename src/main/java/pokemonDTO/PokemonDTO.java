package pokemonDTO;

import java.util.ArrayList;

public class PokemonDTO {

	private ArrayList<Ability> abilities;
	private int base_experience;
	private ArrayList<Form> forms;
	private ArrayList<GameIndex> game_indices;
	private int height;
	private ArrayList<Object> held_items;
	private int id;
	private boolean is_default;
	private String location_area_encounters;
	private ArrayList<Move> moves;
	private String name;
	private int order;
	private ArrayList<Object> past_abilities;
	private ArrayList<Object> past_types;
	private Species species;
	private Sprites sprites;
	private ArrayList<Stat> stats;
	private ArrayList<Type> types;
	private int weight;

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(ArrayList<Ability> abilities) {
		this.abilities = abilities;
	}

	public int getBase_experience() {
		return base_experience;
	}

	public void setBase_experience(int base_experience) {
		this.base_experience = base_experience;
	}

	public ArrayList<Form> getForms() {
		return forms;
	}

	public void setForms(ArrayList<Form> forms) {
		this.forms = forms;
	}

	public ArrayList<GameIndex> getGame_indices() {
		return game_indices;
	}

	public void setGame_indices(ArrayList<GameIndex> game_indices) {
		this.game_indices = game_indices;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Object> getHeld_items() {
		return held_items;
	}

	public void setHeld_items(ArrayList<Object> held_items) {
		this.held_items = held_items;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isIs_default() {
		return is_default;
	}

	public void setIs_default(boolean is_default) {
		this.is_default = is_default;
	}

	public String getLocation_area_encounters() {
		return location_area_encounters;
	}

	public void setLocation_area_encounters(String location_area_encounters) {
		this.location_area_encounters = location_area_encounters;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public ArrayList<Object> getPast_abilities() {
		return past_abilities;
	}

	public void setPast_abilities(ArrayList<Object> past_abilities) {
		this.past_abilities = past_abilities;
	}

	public ArrayList<Object> getPast_types() {
		return past_types;
	}

	public void setPast_types(ArrayList<Object> past_types) {
		this.past_types = past_types;
	}

	public Species getSpecies() {
		return species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public Sprites getSprites() {
		return sprites;
	}

	public void setSprites(Sprites sprites) {
		this.sprites = sprites;
	}

	public ArrayList<Stat> getStats() {
		return stats;
	}

	public void setStats(ArrayList<Stat> stats) {
		this.stats = stats;
	}

	public ArrayList<Type> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
