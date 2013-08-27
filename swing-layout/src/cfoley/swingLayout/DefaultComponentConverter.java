package cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;

public class DefaultComponentConverter implements ComponentConverter {

	@Override
	public JComponent toComponent(Object o) {
		if (o instanceof JComponent) {
			return (JComponent) o;
		} else if (o instanceof PanelBuilder) {
			return ((PanelBuilder<?>) o).build();
		} else if (o instanceof Component) {
			JPanel result = new JPanel(new BorderLayout());
			result.add((Component) o, BorderLayout.CENTER);
			return result;
		} else {
			return new JLabel(o.toString());
		}
	}

}
