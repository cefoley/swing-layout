package cfoley.swingLayout;

import java.awt.CardLayout;

import javax.swing.JTabbedPane;

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
	
	public HorizontalSplitPaneBuilder horizontalSplitPane(Object left, Object right) {
		return horizontalSplitPane().left(left).right(right);
	}

	public HorizontalSplitPaneBuilder horizontalSplitPane() {
		return new HorizontalSplitPaneBuilder(converter);
	}
	
	public VerticalSplitPaneBuilder verticalSplitPane(Object top, Object bottom) {
		return verticalSplitPane().top(top).bottom(bottom);
	}

	public VerticalSplitPaneBuilder verticalSplitPane() {
		return new VerticalSplitPaneBuilder(converter);
	}
	
	public BoxBuilder verticalBox() {
		return new BoxBuilder(converter).vertical();
	}

	public BoxBuilder horizontalBox() {
		return new BoxBuilder(converter).horizontal();
	}

	public CardBuilder card() {
		return new CardBuilder(converter);
	}
	
	public CardBuilder card(CardLayout layout) {
		return new CardBuilder(converter, layout);
	}
	
	public TabbedPaneBuilder tabs() {
		return new TabbedPaneBuilder(converter);
	}

	public TabbedPaneBuilder tabs(JTabbedPane panel) {
		return new TabbedPaneBuilder(converter, panel);
	}

}
