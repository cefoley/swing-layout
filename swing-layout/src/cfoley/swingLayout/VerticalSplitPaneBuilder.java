package cfoley.swingLayout;

import java.awt.Dimension;

public class VerticalSplitPaneBuilder extends AbstractSplitPaneBuilder<VerticalSplitPaneBuilder> {
	

	protected VerticalSplitPaneBuilder(ComponentConverter converter) {
		super(converter, false);
	}
	
	public VerticalSplitPaneBuilder top(Object o) {
		first = toComponent(o);
		return this;
	}
	
	public VerticalSplitPaneBuilder bottom(Object o) {
		second = toComponent(o);
		return this;
	}
	
	public VerticalSplitPaneBuilder topMinimumSize(int width, int height) {
		return topMinimumSize(new Dimension(width, height));
	}
	
	public VerticalSplitPaneBuilder topMinimumSize(Dimension d) {
		firstMinSize = d;
		return this;
	}
	
	public VerticalSplitPaneBuilder bottomMinimumSize(int width, int height) {
		return bottomMinimumSize(new Dimension(width, height));
	}
	
	public VerticalSplitPaneBuilder bottomMinimumSize(Dimension d) {
		secondMinSize = d;
		return this;
	}
	
	public VerticalSplitPaneBuilder topPreferredSize(int width, int height) {
		return topPreferredSize(new Dimension(width, height));
	}
	
	public VerticalSplitPaneBuilder topPreferredSize(Dimension d) {
		firstPreferredSize = d;
		return this;
	}
	
	public VerticalSplitPaneBuilder bottomPreferredSize(int width, int height) {
		return bottomPreferredSize(new Dimension(width, height));
	}
	
	public VerticalSplitPaneBuilder bottomPreferredSize(Dimension d) {
		secondPreferredSize = d;
		return this;
	}
	
	public VerticalSplitPaneBuilder preferredSize(int width,int height) {
		return preferredSize(new Dimension(width, height));
	}
	
	@Override
	public VerticalSplitPaneBuilder self() {
		return this;
	}

}
