package uk.co.cfoley.swingLayout;

import java.awt.*;

class BoxedComponent implements BoxedWidgetMaker {
	
	private Component comp;
	
	BoxedComponent(Component comp) {
		this.comp = comp;
	}
	
	public Component make(boolean isVertical) {
		return comp;
	}
	
}
