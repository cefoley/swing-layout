package uk.co.cfoley.swingLayout;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComponent;

class BoxedJComponent implements BoxedWidgetMaker {
	
	private JComponent comp;
	private float alignment = 0.5f;
	private Dimension min, max, preferred;
	
	BoxedJComponent(JComponent comp) {
		this.comp = comp;
	}
	
	void setAlignment(float d) {
		if (d < 0 || d > 1) {
			String message = "Alignment should be in range 0--1, not " + d + ".";
			throw new IllegalArgumentException(message);
		}
		alignment = d;
	}
	
	public void setMinSize(Dimension d) {
		min = d;
	}
	
	public void setMaxSize(Dimension d) {
		max = d;
	}
	
	public void setPreferredSize(Dimension d) {
		preferred = d;
	}
	
	public Component make(boolean isVertical) {
		if (isVertical) {
			comp.setAlignmentY(alignment);
		} else {
			comp.setAlignmentX(alignment);
		}
		if (min != null) {
			comp.setMinimumSize(min);
		}
		if (preferred != null) {
			comp.setPreferredSize(preferred);
		}
		if (max != null) {
			comp.setMaximumSize(max);
		}
		return comp;
	}
	
}
