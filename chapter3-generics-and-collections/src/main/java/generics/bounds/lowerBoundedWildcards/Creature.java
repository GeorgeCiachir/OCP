package generics.bounds.lowerBoundedWildcards;

public class Creature {

	private String type;

	public Creature(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}