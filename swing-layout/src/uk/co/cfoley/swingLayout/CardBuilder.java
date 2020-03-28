package uk.co.cfoley.swingLayout;

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class CardBuilder extends PanelBuilder<CardBuilder> {
	
	private CardLayout layout;
	private List<JComponent> components = new ArrayList<>();
	private List<String> names = new ArrayList<>();

	protected CardBuilder(ComponentConverter converter, CardLayout layout) {
		super(converter);
		this.layout = layout;
	}
	
	@Deprecated
	public CardBuilder add(Object o) {
		return add(o, null);
	}
	
	public CardBuilder add(Object o, String name) {
		components.add(toComponent(o));
		names.add(name);
		return this;
	}

	@Override
	protected JComponent subclassBuild() {
		JPanel result = new JPanel(layout);
		for(int i = 0; i < names.size(); i++) {
			JComponent comp = components.get(i);
			String name = names.get(i);
			if (name == null) {
				result.add(comp);
			} else {
				result.add(comp, name);
			}
		}
		return result;
	}

	@Override
	public CardBuilder self() {
		return this;
	}

}
