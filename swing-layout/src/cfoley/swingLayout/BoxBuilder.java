package cfoley.swingLayout;

import java.awt.Component;
import java.awt.Dimension;
import java.util.*;

import javax.swing.Box;
import javax.swing.JComponent;

public class BoxBuilder extends PanelBuilder<BoxBuilder> {
		
	boolean isVertical = true;
	List<Object> items = new ArrayList<>();

	
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
	
	public BoxBuilderConfigureLatest add(Object o) {
		BoxedComponent recent = new BoxedComponent(toComponent(o));
		items.add(recent);
		return new BoxBuilderConfigureLatest(this, recent);
	}
	
	public BoxBuilder addGlue() {
		items.add(Box.createGlue());
		return this;
	}
	
	public BoxBuilder addRigidArea(int width, int height) {
		return addRigidArea(new Dimension(width, height));
	}

	public BoxBuilder addRigidArea(Dimension d) {
		items.add(Box.createRigidArea(d));
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
