package cfoley.swingLayout;

import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JSplitPane;


public abstract class AbstractSplitPaneBuilder<T extends AbstractSplitPaneBuilder<T>> extends PanelBuilder<T> {

	protected JComponent first, second;
	private double resizeWeight = 0.5;
	protected Dimension firstMinSize, secondMinSize;
	protected Dimension firstPreferredSize, secondPreferredSize;
	private Dimension preferredSize;
	private boolean isOneTouchExpandable = false;
	private boolean isHorizontal;
	
	private enum ContinuousSetting{ON, OFF, DEFAULT};
	private ContinuousSetting isContunuousLayout = ContinuousSetting.DEFAULT;

	
	protected AbstractSplitPaneBuilder(ComponentConverter converter, boolean isHorizontal) {
		super(converter);
		this.isHorizontal = isHorizontal;
	}
	
	@Override
	protected JComponent subclassBuild() {
		int orientation = isHorizontal ? JSplitPane.HORIZONTAL_SPLIT : JSplitPane.VERTICAL_SPLIT;
		JSplitPane result = new JSplitPane(orientation, first, second);
		result.setResizeWeight(resizeWeight);
		result.setOneTouchExpandable(isOneTouchExpandable);
		
		setContinuousLayout(result);
		
		setMinSize(first, firstMinSize);
		setMinSize(second, secondMinSize);
		setPreferredSize(first, firstPreferredSize);
		setPreferredSize(second, secondPreferredSize);
		setPreferredSize(result, preferredSize);
		
		return result;
	}
	
	public T resizeWeight(double w) {
		if (w < 0 || w > 1) {
			String message = "Resize weight must be in range 0--1, not " + w;
			throw new IllegalArgumentException(message);
		}
		resizeWeight = w;
		return self();
	}
	
	public T continuousLayoutOn() {
		isContunuousLayout = ContinuousSetting.ON;
		return self();
	}
	
	public T continuousLayoutOff() {
		isContunuousLayout = ContinuousSetting.OFF;
		return self();
	}
	
	public T continuousLayoutToLookAndFeelDefault() {
		isContunuousLayout = ContinuousSetting.DEFAULT;
		return self();
	}
	
	public T oneTouchExpandableOn() {
		isOneTouchExpandable = true;
		return self();
	}
	
	public T oneTouchExpandableOff() {
		isOneTouchExpandable = false;
		return self();
	}

	
	public T preferredSize(Dimension d) {
		preferredSize = d;
		return self();
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


}
