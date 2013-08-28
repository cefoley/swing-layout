package cfoley.swingLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComponent;

public class BoxBuilder extends PanelBuilder<BoxBuilder> {
	
	private boolean isVertical = true;
	private List<Object> items = new ArrayList<>();
	private BoxedComponent recent = null;

	protected BoxBuilder(ComponentConverter converter) {
		super(converter);
	}
	
	public BoxBuilder vertical(){
		isVertical = true;
		return this;
	}
	
	public BoxBuilder horizontal() {
		isVertical = false;
		return this;
	}
	
	public BoxBuilder add(Object o) {
		recent = new BoxedComponent(toComponent(o));
		items.add(recent);
		return this;
	}
	
	public BoxBuilder alignment(double d) {
		return alignment((float)d);
	}

	public BoxBuilder alignment(float d) {
		recent.setAlignment(d);
		return this;
	}

	public BoxBuilder minSize(int x, int y) {
		return minSize(new Dimension(x, y));
	}

	public BoxBuilder minSize(Dimension d) {
		recent.setMinSize(d);
		return this;
	}

	public BoxBuilder maxSize(int x, int y) {
		return maxSize(new Dimension(x, y));
	}

	public BoxBuilder maxSize(Dimension d) {
		recent.setMaxSize(d);
		return this;
	}

	public BoxBuilder preferredSize(int x, int y) {
		return preferredSize(new Dimension(x, y));
	}

	public BoxBuilder preferredSize(Dimension d) {
		recent.setPreferredSize(d);
		return this;
	}
	
	public BoxBuilder addGlue() {
		items.add(Box.createGlue());
		recent = null;
		return this;
	}
	
	public BoxBuilder addRigidArea(int width, int height) {
		return addRigidArea(new Dimension(width, height));
	}

	public BoxBuilder addRigidArea(Dimension d) {
		items.add(Box.createRigidArea(d));
		recent = null;
		return this;
	}

	@Override
	protected JComponent subclassBuild() {
		Box result = isVertical ? Box.createVerticalBox() : Box.createHorizontalBox();
		for(Object o : items) {
			if (o instanceof BoxedComponent) {
				BoxedComponent bc = (BoxedComponent) o;
				result.add(bc.make(isVertical));
			} else {
				result.add((Component) o);
			}
		}
		return result;
	}

	@Override
	public BoxBuilder self() {
		return this;
	}

}
