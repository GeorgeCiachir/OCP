package generics.genericmethods;

public class Crate<T> {

	private T content;

	public Crate(T content) {
		this.content = content;
	}

	public T getContent() {
		return content;
	}
}
