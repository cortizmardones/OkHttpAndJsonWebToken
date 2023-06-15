package pokemonDTO;

import java.util.ArrayList;

public class Move {

	private Move move;
	private ArrayList<VersionGroupDetail> version_group_details;

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

	public ArrayList<VersionGroupDetail> getVersion_group_details() {
		return version_group_details;
	}

	public void setVersion_group_details(ArrayList<VersionGroupDetail> version_group_details) {
		this.version_group_details = version_group_details;
	}

}
