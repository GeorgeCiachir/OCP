package generics.bounds.upperBoundedWildcards;

class Bird {

	private String type;

	public Bird(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
