package cfoley.swingLayout;

import java.awt.*;
import java.util.*;

import javax.swing.*;

public class FlowBuilder extends PanelBuilder<FlowBuilder> {

	private int align, hgap, vgap;
	private boolean isValignedOnBaseline = false;

	private ArrayList<Component> items;

	/** Use Layout.flow() */
	FlowBuilder(ComponentConverter converter) {
		super(converter);
		items = new ArrayList<>();
		FlowLayout defaults = new FlowLayout();
		align = defaults.getAlignment();
	}

	public FlowBuilder add(Object... os) {
		for (Object o : os) {
			items.add(toComponent(o));
		}
		return this;
	}

	public FlowBuilder gap(int i) {
		return vgap(i).hgap(i);
	}

	public FlowBuilder hgap(int i) {
		hgap = i;
		return this;
	}

	public FlowBuilder vgap(int i) {
		vgap = i;
		return this;
	}

	public FlowBuilder alignLeft() {
		align = FlowLayout.LEFT;
		return this;
	}

	public FlowBuilder alignRight() {
		align = FlowLayout.RIGHT;
		return this;
	}

	public FlowBuilder alignLeading() {
		align = FlowLayout.LEADING;
		return this;
	}

	public FlowBuilder alignTrailing() {
		align = FlowLayout.TRAILING;
		return this;
	}

	public FlowBuilder alignCenter() {
		align = FlowLayout.CENTER;
		return this;
	}

	public FlowBuilder vAlignOnBaseline() {
		isValignedOnBaseline = true;
		return this;
	}

	public FlowBuilder vAlignOnCenter() {
		isValignedOnBaseline = false;
		return this;
	}

	@Override
	protected JComponent subclassBuild() {
		FlowLayout layout = new FlowLayout(align, hgap, vgap);
		layout.setAlignOnBaseline(isValignedOnBaseline);
		JPanel result = new JPanel(layout);
		for (Component c : items) {
			result.add(c);
		}
		return result;
	}

	@Override
	public FlowBuilder self() {
		return this;
	}

}
