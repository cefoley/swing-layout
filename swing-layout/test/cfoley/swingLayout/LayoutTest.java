package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class LayoutTest {
	JComponent input;
	LayoutBuilder builder;
	JLabel label0;
	JLabel label1;
	JLabel label2;

	@Before
	public void setUp() {
		input = new JPanel();
		label0 = new JLabel("label 0");
		label1 = new JLabel("label 1");
		label2 = new JLabel("label 2");
	}
	
	@Test
	public void defaultLayoutBuilderCanBeChanged() {
		LayoutBuilder ultimateLayoutBUilder = new LayoutBuilder();
		Layout.setDefaultLayoutBuilder(ultimateLayoutBUilder);
		assertEquals(ultimateLayoutBUilder, Layout.getDefaultLauoutBuilder());
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void layoutClassCanBeInstantiated() {
		assertLayoutType(BorderBuilder.class, new Layout().borders());
	}
	
	@Test
	public void borders() {
		assertLayoutType(BorderBuilder.class, Layout.borders());
	}

	@Test
	public void grid() {
		assertLayoutType(GridBuilder.class, Layout.grid());
	}

	@Test
	public void gridRow() {
		GridBuilder actual = Layout.gridRow(label0, label1, label2);
		Assertions a = new Assertions(actual);
		a.assertComponents(label0, label1, label2);
		a.assertGridRowsAndColumns(1, 0);
	}

	@Test
	public void gridCol() {
		GridBuilder actual = Layout.gridCol(label0, label1, label2);
		Assertions a = new Assertions(actual);
		a.assertComponents(label0, label1, label2);
		a.assertGridRowsAndColumns(0, 1);
	}

	@Test
	public void scroller() {
		ScrollerBuilder actual = Layout.scroller(label0);
		Assertions a = new Assertions(actual);
		a.assertJScrollPaneComponentComponent(label0);
	}

	@Test
	public void flow() {
		FlowBuilder actual = Layout.flow(label0, label1);
		Assertions a = new Assertions(actual);
		a.assertFlowLayoutAlignment(FlowLayout.CENTER);
		a.assertComponents(label0, label1);
	}

	@Test
	public void horizontalSplitPane() {
		HorizontalSplitPaneBuilder actual = Layout.horizontalSplitPane();
		Assertions a = new Assertions(actual);
		a.assertSplitPaneOrientation(JSplitPane.HORIZONTAL_SPLIT);
	}

	@Test
	public void horizontalSplitPaneWithContents() {
		HorizontalSplitPaneBuilder actual = Layout.horizontalSplitPane(label0, label1);
		Assertions a = new Assertions(actual);
		a.assertSplitPaneOrientation(JSplitPane.HORIZONTAL_SPLIT);
		a.assertSplitPaneFirstComponent(label0);
		a.assertSplitPaneSecondComponent(label1);
	}

	@Test
	public void verticalSplitPane() {
		VerticalSplitPaneBuilder actual = Layout.verticalSplitPane();
		Assertions a = new Assertions(actual);
		a.assertSplitPaneOrientation(JSplitPane.VERTICAL_SPLIT);
	}

	@Test
	public void verticalSplitPaneWithContents() {
		VerticalSplitPaneBuilder actual = Layout.verticalSplitPane(label0, label1);
		Assertions a = new Assertions(actual);
		a.assertSplitPaneOrientation(JSplitPane.VERTICAL_SPLIT);
		a.assertSplitPaneFirstComponent(label0);
		a.assertSplitPaneSecondComponent(label1);
	}

	@Test
	public void verticalBox() {
		BoxBuilder actual = Layout.verticalBox();
		Assertions a = new Assertions(actual);
		a.assertBoxIsVertical();
	}
	
	@Test
	public void horizontalBox() {
		BoxBuilder actual = Layout.horizontalBox();
		Assertions a = new Assertions(actual);
		a.assertBoxIsHorizontal();
	}
	
	@Test
	public void card() {
		CardBuilder actual = Layout.card(new CardLayout()).add(label0, "some name");
		Assertions a = new Assertions(actual);
		a.assertCardLayoutName(label0, "some name");
	}
	
	@Test
	public void tabs() {
		TabbedPaneBuilder actual = Layout.tabs().add("some name", label0);
		Assertions a = new Assertions(actual);
		a.assertTabbedPaneTab(0, "some name", null, label0);
	}
	
	@Test
	public void tabsSupplyingPanel() {
		JTabbedPane expected = new JTabbedPane();
		TabbedPaneBuilder actual = Layout.tabs(expected);
		Assertions a = new Assertions(actual);
		assertEquals(expected, a.result());
	}
	
	private void assertLayoutType(Class<?> expectedClass, PanelBuilder<?> actual) {
		assertEquals(expectedClass, actual.getClass());
	}
}
