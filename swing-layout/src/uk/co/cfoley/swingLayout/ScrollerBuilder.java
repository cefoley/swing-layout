package uk.co.cfoley.swingLayout;

import javax.swing.*;

public class ScrollerBuilder extends PanelBuilder<ScrollerBuilder> {

	private JComponent component;
	private int vertical = JScrollPane.VERTICAL_SCROLLBAR_NEVER;
	private int horizontal = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER;

	ScrollerBuilder(ComponentConverter converter, Object component) {
		super(converter);
		this.component = toComponent(component);
	}

	public ScrollerBuilder horizontal() {
		horizontal = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
		return this;
	}

	public ScrollerBuilder horizontalAsNeeded() {
		horizontal = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		return this;
	}

	public ScrollerBuilder vertical() {
		vertical = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
		return this;
	}

	public ScrollerBuilder verticalAsNeeded() {
		vertical = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
		return this;
	}

	@Override
	public JComponent subclassBuild() {
		return new JScrollPane(component, vertical, horizontal);
	}

	@Override
	public ScrollerBuilder self() {
		return this;
	}

}
