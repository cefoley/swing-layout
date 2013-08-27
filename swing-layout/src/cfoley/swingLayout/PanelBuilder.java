package cfoley.swingLayout;

import javax.swing.*;

public abstract class PanelBuilder<T extends PanelBuilder<T>> {

	private int padLeft, padRight, padTop, padBottom;

	private ComponentConverter converter;

	protected PanelBuilder() {
		converter = Defaults.componentConverter;
	}

	protected JComponent component(Object o) {
		return converter.toComponent(o);
	}

	public T pad(int i) {
		return padLeft(i).padRight(i).padTop(i).padBottom(i);
	}

	public T padLeft(int i) {
		padLeft = i;
		return self();
	}

	public T padRight(int i) {
		padRight = i;
		return self();
	}

	public T padTop(int i) {
		padTop = i;
		return self();
	}

	public T padBottom(int i) {
		padBottom = i;
		return self();
	}

	protected JComponent format(JComponent c) {
		return addPadding(c);
	}

	private JComponent addPadding(JComponent c) {
		boolean hasPadding = (padLeft != 0) || (padRight != 0) || (padTop != 0) || (padBottom != 0);
		if (hasPadding) {
			JComponent result = Layout.borders().center(c).build();
			result.setBorder(BorderFactory.createEmptyBorder(padTop, padLeft, padBottom, padRight));
			return result;
		} else {
			return c;
		}
	}

	public JComponent build() {
		return format(subclassBuild());
	}

	protected abstract JComponent subclassBuild();

	public abstract T self();

}
