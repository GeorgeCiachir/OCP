package immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Immutable {

	private final List<String> names;

	public Immutable(List<String> names) {
		// check param is not null
		this.names = new ArrayList<String>(names);
	}

	public List<String> getNames() {
		return Collections.unmodifiableList(names);
	}
}
