package cfoley.swingLayout;


public class Layout {

	public static BorderBuilder borders() {
		return new BorderBuilder();
	}

	public static GridBuilder gridRow(Object... os) {
		return grid().rows(1).add(os);
	}

	public static GridBuilder gridCol(Object... os) {
		return grid().cols(1).add(os);
	}

	public static GridBuilder grid() {
		return new GridBuilder();
	}

	public static ScrollerBuilder scroller(Object o) {
		return new ScrollerBuilder(o);
	}

}
