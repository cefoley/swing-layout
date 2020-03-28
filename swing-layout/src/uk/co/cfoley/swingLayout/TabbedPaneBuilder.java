package uk.co.cfoley.swingLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class TabbedPaneBuilder extends PanelBuilder<TabbedPaneBuilder> {
	
	private JTabbedPane panel;

	public TabbedPaneBuilder(ComponentConverter converter) {
		this(converter, new JTabbedPane());
	}
	
	public TabbedPaneBuilder(ComponentConverter converter, JTabbedPane panel) {
		super(converter);
		this.panel = panel;
	}
	
	public TabbedPaneBuilder tabPlacementTop() {
		panel.setTabPlacement(JTabbedPane.TOP);
		return this;
	}

	public TabbedPaneBuilder tabPlacementBottom() {
		panel.setTabPlacement(JTabbedPane.BOTTOM);
		return this;
	}

	public TabbedPaneBuilder tabPlacementLeft() {
		panel.setTabPlacement(JTabbedPane.LEFT);
		return this;
	}

	public TabbedPaneBuilder tabPlacementRight() {
		panel.setTabPlacement(JTabbedPane.RIGHT);
		return this;
	}

	public TabbedPaneBuilder wrapExcessTabs() {
		panel.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		return this;
	}

	public TabbedPaneBuilder scrollExcessTabs() {
		panel.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		return this;
	}
	
	public TabbedPaneBuilder add(String tabName, Object o) {
		panel.addTab(tabName, toComponent(o));
		return this;
	}

	public TabbedPaneBuilder add(String tabName, Icon icon, Object o) {
		panel.addTab(tabName, icon, toComponent(o));
		return this;
	}

	@Override
	protected JComponent subclassBuild() {
		return panel;
	}

	@Override
	public TabbedPaneBuilder self() {
		return this;
	}

}
