package cfoley.swingLayout;

import javax.swing.*;

public class ComponentConvertedReturnOriginal implements ComponentConverter {
	@Override
	public JComponent toComponent(Object o) {
		return (JComponent) o;
	}
}
