package cfoley.swingLayout;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class HorizontalSplitPaneBuilder extends PanelBuilder<HorizontalSplitPaneBuilder> {
	
	private JComponent left, right;
	private double resizeWeight = 0.5;
	private Dimension leftMinSize, rightMinSize;
	private Dimension leftPreferredSize, rightPreferredSize;
	private Dimension preferredSize;
	private boolean isOneTouchExpandable = false;
	
	
	private enum ContinuousSetting{ON, OFF, DEFAULT};
	private ContinuousSetting isContunuousLayout = ContinuousSetting.DEFAULT;

	protected HorizontalSplitPaneBuilder(ComponentConverter converter) {
		super(converter);
	}
	
	public HorizontalSplitPaneBuilder left(Object o) {
		left = toComponent(o);
		return this;
	}
	
	public HorizontalSplitPaneBuilder right(Object o) {
		right = toComponent(o);
		return this;
	}
	
	public HorizontalSplitPaneBuilder resizeWeight(double w) {
		if (w < 0 || w > 1) {
			String message = "Resize weight must be in range 0--1, not " + w;
			throw new IllegalArgumentException(message);
		}
		resizeWeight = w;
		return this;
	}
	
	public HorizontalSplitPaneBuilder continuousLayoutOn() {
		isContunuousLayout = ContinuousSetting.ON;
		return this;
	}
	
	public HorizontalSplitPaneBuilder continuousLayoutOff() {
		isContunuousLayout = ContinuousSetting.OFF;
		return this;
	}
	
	public HorizontalSplitPaneBuilder continuousLayoutToLookAndFeelDefault() {
		isContunuousLayout = ContinuousSetting.DEFAULT;
		return this;
	}
	
	public HorizontalSplitPaneBuilder oneTouchExpandableOn() {
		isOneTouchExpandable = true;
		return this;
	}
	
	public HorizontalSplitPaneBuilder oneTouchExpandableOff() {
		isOneTouchExpandable = false;
		return this;
	}
	
	public HorizontalSplitPaneBuilder leftMinimumSize(int width, int height) {
		return leftMinimumSize(new Dimension(width, height));
	}
	
	public HorizontalSplitPaneBuilder leftMinimumSize(Dimension d) {
		leftMinSize = d;
		return this;
	}
	
	public HorizontalSplitPaneBuilder rightMinimumSize(int width, int height) {
		return rightMinimumSize(new Dimension(width, height));
	}
	
	public HorizontalSplitPaneBuilder rightMinimumSize(Dimension d) {
		rightMinSize = d;
		return this;
	}
	
	public HorizontalSplitPaneBuilder leftPreferredSize(int width, int height) {
		return leftPreferredSize(new Dimension(width, height));
	}
	
	public HorizontalSplitPaneBuilder leftPreferredSize(Dimension d) {
		leftPreferredSize = d;
		return this;
	}
	
	public HorizontalSplitPaneBuilder rightPreferredSize(int width, int height) {
		return rightPreferredSize(new Dimension(width, height));
	}
	
	public HorizontalSplitPaneBuilder rightPreferredSize(Dimension d) {
		rightPreferredSize = d;
		return this;
	}
	
	public HorizontalSplitPaneBuilder preferredSize(int width,int height) {
		return preferredSize(new Dimension(width, height));
	}
	
	public HorizontalSplitPaneBuilder preferredSize(Dimension d) {
		preferredSize = d;
		return this;
	}
	
	@Override
	protected JComponent subclassBuild() {
		JSplitPane result = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
		result.setResizeWeight(resizeWeight);
		result.setOneTouchExpandable(isOneTouchExpandable);
		
		setContinuousLayout(result);
		
		setMinSize(left, leftMinSize);
		setMinSize(right, rightMinSize);
		setPreferredSize(left, leftPreferredSize);
		setPreferredSize(right, rightPreferredSize);
		setPreferredSize(result, preferredSize);
		
		return result;
	}
	
	private void setContinuousLayout(JSplitPane splitPane) {
		switch (isContunuousLayout) {
		case ON:
			splitPane.setContinuousLayout(true);
		case OFF:
			splitPane.setContinuousLayout(false);
		case DEFAULT:
			// nothing to do here
		}
	}
	
	private void setMinSize(JComponent c, Dimension d) {
		if (d != null) {
			c.setMinimumSize(d);
		}
	}

	private void setPreferredSize(JComponent c, Dimension d) {
		if (d != null) {
			c.setMinimumSize(d);
		}
	}

	@Override
	public HorizontalSplitPaneBuilder self() {
		return this;
	}

}
