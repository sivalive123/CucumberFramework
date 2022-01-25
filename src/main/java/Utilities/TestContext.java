package Utilities;

public class TestContext {

	private final AllDriverManager driverManager;

	public TestContext() {
		driverManager = new AllDriverManager();
	}

	public AllDriverManager getDriverManager() {
		return driverManager;
	}

}
