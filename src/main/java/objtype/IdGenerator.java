package objtype;

public class IdGenerator {
	private int currentId;

	private IdGenerator() {
		currentId = 1;
	}

	private static IdGenerator generator = new IdGenerator();

	void increaseGeneratorBy(int plus) {
		currentId += plus;
	}

	public static IdGenerator getInstance() {
		return generator;
	}
}
