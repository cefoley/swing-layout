package cfoley.swingLayout;

import static org.junit.Assert.*;

public class ThrowableAssert {
	private Throwable actual;

	public static <T> ThrowableAssert catchExceptionFrom(ExceptionThrower<T> action) {
		try {
			action.run();
			return new ThrowableAssert(null);
		} catch (Throwable t) {
			return new ThrowableAssert(t);
		}
	}

	public void assertNothingThrown() {
		assertNull("Expected no exception but " + actual + " was thrown.", actual);
	}
	
	public ThrowableAssert(Throwable actualThrowable) {
		this.actual = actualThrowable;
	}
	
	public void assertExceptionType(Class<?> expectedClass) {
		assertEquals(expectedClass, actual.getClass());
	}
	
	public void assertMessageEquals(String expectedMessage) {
		assertEquals(expectedMessage, actual.getMessage());
	}
}
