package cfoley.swingLayout;

public interface ExceptionThrower<T> {
	public T run() throws Throwable;
}
