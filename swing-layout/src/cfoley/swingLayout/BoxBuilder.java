package cfoley.swingLayout;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class BoxBuilder extends PanelBuilder<BoxBuilder> {
		
	boolean isVertical = true;
	List<BoxedWidgetMaker> items = new ArrayList<>();

	
	BoxBuilder(ComponentConverter converter) {
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
		BoxedJComponent recent = new BoxedJComponent(toComponent(o));
		items.add(recent);
		return new BoxBuilderConfigureLatest(this, recent);
	}
	
	public BoxBuilder addGlue() {
		items.add(new BoxedComponent(Box.createGlue()));
		return this;
	}
	
	public BoxBuilder addRigidArea(int width, int height) {
		return addRigidArea(new Dimension(width, height));
	}

	public BoxBuilder addRigidArea(Dimension d) {
		items.add(new BoxedComponent(Box.createRigidArea(d)));
		return this;
	}

	@Override
	protected JComponent subclassBuild() {
		Box result = isVertical ? Box.createVerticalBox() : Box.createHorizontalBox();
		for(BoxedWidgetMaker o : items) {
			result.add(o.make(isVertical));
		}
		return result;
	}

	@Override
	public BoxBuilder self() {
		return this;
	}

}
