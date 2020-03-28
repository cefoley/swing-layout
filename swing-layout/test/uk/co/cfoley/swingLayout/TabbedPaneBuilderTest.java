package uk.co.cfoley.swingLayout;

import javax.swing.*;

import org.junit.*;

public class TabbedPaneBuilderTest {
	TabbedPaneBuilder builder;
	Assertions a;
	
	@Before
	public void setUp() {
		builder = new TabbedPaneBuilder(new ComponentConvertedReturnOriginal());
		a = new Assertions(builder);
	}
	
	@Test
	public void empty() {
		a.assertComponents();
		a.assertPadding(0, 0, 0, 0);
		a.assertComponents();
		a.assertTabbedPanePlacement(JTabbedPane.TOP);
		a.assertTabbedPaneLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
	}
	
	@Test
	public void tabPlacementTop() {
		builder.tabPlacementLeft().tabPlacementTop();
		a.assertTabbedPanePlacement(JTabbedPane.TOP);
	}
	
	@Test
	public void tabPlacementBotton() {
		builder.tabPlacementLeft().tabPlacementBottom();
		a.assertTabbedPanePlacement(JTabbedPane.BOTTOM);
	}
	
	@Test
	public void tabPlacementLeft() {
		builder.tabPlacementTop().tabPlacementLeft();
		a.assertTabbedPanePlacement(JTabbedPane.LEFT);
	}
	
	@Test
	public void tabPlacementRight() {
		builder.tabPlacementTop().tabPlacementRight();
		a.assertTabbedPanePlacement(JTabbedPane.RIGHT);
	}
	
	@Test
	public void wrapExcessTabs() {
		builder.wrapExcessTabs();
		a.assertTabbedPaneLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
	}
	
	@Test
	public void scrollExcessTabs() {
		builder.scrollExcessTabs();
		a.assertTabbedPaneLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	@Test
	public void addOneTab() {
		JLabel tab1 = new JLabel();
		builder.add("A", tab1);
		Icon noIcon = null;
		a.assertTabbedPaneTab(0, "A", noIcon, tab1);
	}

	@Test
	public void addThreeTabs() {
		JLabel tab1 = new JLabel("a");
		JLabel tab2 = new JLabel("b");
		JLabel tab3 = new JLabel("c");
		builder.add("A", tab1);
		builder.add("B", tab2);
		builder.add("C", tab3);
		Icon noIcon = null;
		a.assertTabbedPaneTab(0, "A", noIcon, tab1);
		a.assertTabbedPaneTab(1, "B", noIcon, tab2);
		a.assertTabbedPaneTab(2, "C", noIcon, tab3);
	}

	@Test
	public void addOneTabWithImage() {
		JLabel tab1 = new JLabel();
		Icon icon1 = new ImageIcon();
		builder.add("A", icon1, tab1);
		a.assertTabbedPaneTab(0, "A", icon1, tab1);
	}
	
	@Test
	public void addThreeTabsWithImages() {
		JLabel tab1 = new JLabel("a");
		JLabel tab2 = new JLabel("b");
		JLabel tab3 = new JLabel("c");
		Icon icon1 = new ImageIcon();
		Icon icon2 = new ImageIcon();
		Icon icon3 = new ImageIcon();
		builder.add("A", icon1, tab1);
		builder.add("B", icon2, tab2);
		builder.add("C", icon3, tab3);
		a.assertTabbedPaneTab(0, "A", icon1, tab1);
		a.assertTabbedPaneTab(1, "B", icon2, tab2);
		a.assertTabbedPaneTab(2, "C", icon3, tab3);
	}

	@Test
	public void complex() {
		JLabel tab1 = new JLabel("a");
		JLabel tab2 = new JLabel("b");
		Icon icon1 = new ImageIcon();
		builder.pad(5).add("A", null, tab1).add("B", icon1, tab2);
		a.assertPadding(5, 5, 5, 5);
		a.assertTabbedPaneTab(0, "A", null, tab1);
		a.assertTabbedPaneTab(1, "B", icon1, tab2);
	}
}
