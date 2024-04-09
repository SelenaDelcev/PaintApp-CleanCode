package mvc;

import org.junit.Test;

public class ApplicationTest {
	Application application;

	@Test(expected = Test.None.class)
	public void testInvokeExpectedNoErrors() {
		Application.main(null);
	}

}
