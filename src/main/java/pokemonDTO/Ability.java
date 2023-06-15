package pokemonDTO;

public class Ability {

	private Ability2 ability;
	private boolean is_hidden;
	private int slot;

	public Ability2 getAbility() {
		return ability;
	}

	public void setAbility(Ability2 ability) {
		this.ability = ability;
	}

	public boolean isIs_hidden() {
		return is_hidden;
	}

	public void setIs_hidden(boolean is_hidden) {
		this.is_hidden = is_hidden;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}

}
