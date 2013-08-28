package cfoley.swingLayout;

public class Layout {

	private static LayoutBuilder builder = new LayoutBuilder();

	public LayoutBuilder getDefaultLauoutBuilder() {
		return builder;
	}

	public static BorderBuilder borders() {
		return builder.borders();
	}

	public static GridBuilder gridRow(Object... os) {
		return builder.gridRow(os);
	}

	public static GridBuilder gridCol(Object... os) {
		return builder.gridCol(os);
	}

	public static GridBuilder grid() {
		return builder.grid();
	}

	public static ScrollerBuilder scroller(Object o) {
		return builder.scroller(o);
	}

	public static FlowBuilder flow(Object... os) {
		return builder.flow(os);
	}
	
	public HorizontalSplitPaneBuilder horizontalSplitPane(Object left, Object right) {
		return builder.horizontalSplitPane(left, right);
	}

	public HorizontalSplitPaneBuilder horizontalSplitPane() {
		return builder.horizontalSplitPane();
	}
	
	public VerticalSplitPaneBuilder verticalSplitPane(Object top, Object bottom) {
		return builder.verticalSplitPane(top, bottom);
	}

	public VerticalSplitPaneBuilder verticalSplitPane() {
		return builder.verticalSplitPane();
	}
	
	public static BoxBuilder verticalBox() {
		return builder.verticalBox();
	}

	public static BoxBuilder horizontalBox() {
		return builder.horizontalBox();
	}



}
