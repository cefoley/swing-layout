package uk.co.cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

public abstract class PanelBuilder<T extends PanelBuilder<T>> implements ComponentConverter, JComponentBuilder {
	
	private int padLeft, padRight, padTop, padBottom;
	private ComponentConverter converter;
	private Border border;
	private Color backgroundColour;

	protected PanelBuilder(ComponentConverter converter) {
		this.converter = converter;
		backgroundColour = null;
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
	
	public T backgroundColour(Color c) {
		this.backgroundColour = c;
		return self();
	}

	/* (non-Javadoc)
	 * @see cfoley.swingLayout.JComponentBuilder#build()
	 */
	@Override
	public final JComponent build() {
		return addPadding(subclassBuild());
	}

	protected abstract JComponent subclassBuild();

	private JComponent addPadding(JComponent c) {
		if (hasBorder()) {
			if (c.getBorder() != null) {
				JPanel result = new JPanel(new BorderLayout());
				result.add(c, BorderLayout.CENTER);
				result.setBorder(makeBorder());
				return result;
			} else {
				c.setBorder(makeBorder());
			}
		} 
		if (backgroundColour != null)
			c.setBackground(backgroundColour);
		return c;
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
