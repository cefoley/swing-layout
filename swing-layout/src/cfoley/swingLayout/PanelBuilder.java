package cfoley.swingLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

public abstract class PanelBuilder<T extends PanelBuilder<T>> implements ComponentConverter {

	private int padLeft, padRight, padTop, padBottom;
	private ComponentConverter converter;
	private Border border;

	protected PanelBuilder(ComponentConverter converter) {
		this.converter = converter;
	}

	@Override
	public JComponent toComponent(Object o) {
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
	
	public T border(Border b) {
		border = b;
		return self();
	}

	public JComponent build() {
		return addPadding(subclassBuild());
	}

	protected abstract JComponent subclassBuild();

	// TODO avoid creating a new panel if possible.
	// BorderBuilder, GridBuilder, etc don't need a wrapper panel.
	// ScrollerBuilder probably does.
	private JComponent addPadding(JComponent c) {
		if (hasBorder()) {
			JComponent result = Layout.borders().center(c).build();
			result.setBorder(makeBorder());
			return result;
		} else {
			return c;
		}
	}
	
	private boolean hasBorder() {
		return (border != null) || hasPadding();
	}
	
	private boolean hasPadding() {
		return (padLeft != 0) || 
				(padRight != 0) || 
				(padTop != 0) || 
				(padBottom != 0);
	}
	
	private Border makeBorder() {
		if (hasPadding()) {
			Border padding = BorderFactory.createEmptyBorder(padTop, padLeft, padBottom, padRight);
			if (border == null) {
				return padding;
			} else {
				return BorderFactory.createCompoundBorder(padding, border);
			}
		} else {
			return border;
		}
	}
	
	

	public abstract T self();

}
