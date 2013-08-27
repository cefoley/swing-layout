package cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;

public class BorderBuilder extends PanelBuilder<BorderBuilder> {

	private Component north, south, west, east, center;
	private int hgap = 0;
	private int vgap = 0;

	/** Use Layout.borders() */
	BorderBuilder() {
		super();
	}

	public BorderBuilder gap(int i) {
		return vgap(i).hgap(i);
	}

	public BorderBuilder hgap(int i) {
		hgap = i;
		return this;
	}

	public BorderBuilder vgap(int i) {
		vgap = i;
		return this;
	}

	public BorderBuilder north(Object o) {
		north = component(o);
		return this;
	}

	public BorderBuilder south(Object o) {
		south = component(o);
		return this;
	}

	public BorderBuilder east(Object o) {
		east = component(o);
		return this;
	}

	public BorderBuilder west(Object o) {
		west = component(o);
		return this;
	}

	public BorderBuilder center(Object o) {
		center = component(o);
		return this;
	}

	@Override
	public JComponent subclassBuild() {
		JPanel result = new JPanel(new BorderLayout(hgap, vgap));
		if (north != null) {
			result.add(north, BorderLayout.NORTH);
		}
		if (south != null) {
			result.add(south, BorderLayout.SOUTH);
		}
		if (west != null) {
			result.add(west, BorderLayout.WEST);
		}
		if (east != null) {
			result.add(east, BorderLayout.EAST);
		}
		if (center != null) {
			result.add(center, BorderLayout.CENTER);
		}
		return result;
	}

	@Override
	public BorderBuilder self() {
		return this;
	}

}
