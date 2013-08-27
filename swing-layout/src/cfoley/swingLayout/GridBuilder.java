package cfoley.swingLayout;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class GridBuilder extends PanelBuilder<GridBuilder> {
	private ComponentConverter converter;
	private int rows, cols, vgap, hgap;
	private ArrayList<Component> items;

	/** Use Layout.grid() */
	GridBuilder() {
		converter = Defaults.componentConverter;
		items = new ArrayList<>();
	}

	public GridBuilder hgap(int i) {
		hgap = i;
		return this;
	}

	public GridBuilder gap(int i) {
		return vgap(i).hgap(i);
	}

	public GridBuilder vgap(int i) {
		vgap = i;
		return this;
	}

	public GridBuilder rows(int i) {
		rows = i;
		return this;
	}

	public GridBuilder cols(int i) {
		cols = i;
		return this;
	}

	public GridBuilder add(Object... os) {
		for (Object o : os) {
			items.add(converter.toComponent(o));
		}
		return this;
	}

	@Override
	public JComponent subclassBuild() {
		JPanel result = new JPanel(new GridLayout(rows, cols, hgap, vgap));
		for (Component c : items) {
			result.add(c);
		}
		return result;
	}

	@Override
	public GridBuilder self() {
		return this;
	}

}
