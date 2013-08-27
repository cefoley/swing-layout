package cfoley.swingLayout;

public class LayoutBuilder {

	private ComponentConverter converter = Defaults.componentConverter;

	public BorderBuilder borders() {
		return new BorderBuilder(converter);
	}

	public GridBuilder gridRow(Object... os) {
		return grid().rows(1).add(os);
	}

	public GridBuilder gridCol(Object... os) {
		return grid().cols(1).add(os);
	}

	public GridBuilder grid() {
		return new GridBuilder(converter);
	}

	public ScrollerBuilder scroller(Object o) {
		return new ScrollerBuilder(converter, o);
	}

	public FlowBuilder flow(Object... os) {
		return new FlowBuilder(converter).add(os);
	}

}
