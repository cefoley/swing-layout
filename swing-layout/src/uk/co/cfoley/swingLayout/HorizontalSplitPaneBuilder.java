package uk.co.cfoley.swingLayout;

import java.awt.Dimension;

public class HorizontalSplitPaneBuilder extends
		AbstractSplitPaneBuilder<HorizontalSplitPaneBuilder> {

	protected HorizontalSplitPaneBuilder(ComponentConverter converter) {
		super(converter, true);
	}

	public HorizontalSplitPaneBuilder left(Object o) {
		first = toComponent(o);
		return this;
	}

	public HorizontalSplitPaneBuilder right(Object o) {
		second = toComponent(o);
		return this;
	}

	public HorizontalSplitPaneBuilder leftMinimumSize(int width, int height) {
		return leftMinimumSize(new Dimension(width, height));
	}

	public HorizontalSplitPaneBuilder leftMinimumSize(Dimension d) {
		firstMinSize = d;
		return this;
	}

	public HorizontalSplitPaneBuilder rightMinimumSize(int width, int height) {
		return rightMinimumSize(new Dimension(width, height));
	}

	public HorizontalSplitPaneBuilder rightMinimumSize(Dimension d) {
		secondMinSize = d;
		return this;
	}

	public HorizontalSplitPaneBuilder leftPreferredSize(int width, int height) {
		return leftPreferredSize(new Dimension(width, height));
	}

	public HorizontalSplitPaneBuilder leftPreferredSize(Dimension d) {
		firstPreferredSize = d;
		return this;
	}

	public HorizontalSplitPaneBuilder rightPreferredSize(int width, int height) {
		return rightPreferredSize(new Dimension(width, height));
	}

	public HorizontalSplitPaneBuilder rightPreferredSize(Dimension d) {
		secondPreferredSize = d;
		return this;
	}

	public HorizontalSplitPaneBuilder preferredSize(int width, int height) {
		return preferredSize(new Dimension(width, height));
	}

	@Override
	public HorizontalSplitPaneBuilder self() {
		return this;
	}

}
