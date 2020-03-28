package uk.co.cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class LayoutBuilderTest {
	JComponent input;
	LayoutBuilder builder;
	JLabel label0;
	JLabel label1;
	JLabel label2;

	@Before
	public void setUp() {
		input = new JPanel();
		builder = new LayoutBuilder(new ComponentConvertedReturnOriginal());
		label0 = new JLabel("label 0");
		label1 = new JLabel("label 1");
		label2 = new JLabel("label 2");
	}
	
	@Test
	public void borders() {
		assertLayoutType(BorderBuilder.class, builder.borders());
	}

	@Test
	public void grid() {
		assertLayoutType(GridBuilder.class, builder.grid());
	}

	@Test
	public void gridRow() {
		GridBuilder actual = builder.gridRow(label0, label1, label2);
		Assertions a = new Assertions(actual);
		a.assertComponents(label0, label1, label2);
		a.assertGridRowsAndColumns(1, 0);
	}

	@Test
	public void gridCol() {
		GridBuilder actual = builder.gridCol(label0, label1, label2);
		Assertions a = new Assertions(actual);
		a.assertComponents(label0, label1, label2);
		a.assertGridRowsAndColumns(0, 1);
	}

	@Test
	public void scroller() {
		ScrollerBuilder actual = builder.scroller(label0);
		Assertions a = new Assertions(actual);
		a.assertJScrollPaneComponentComponent(label0);
	}

	@Test
	public void flow() {
		FlowBuilder actual = builder.flow(label0, label1);
		Assertions a = new Assertions(actual);
		a.assertFlowLayoutAlignment(FlowLayout.CENTER);
		a.assertComponents(label0, label1);
	}

	@Test
	public void horizontalSplitPane() {
		HorizontalSplitPaneBuilder actual = builder.horizontalSplitPane(label0, label1);
		Assertions a = new Assertions(actual);
		a.assertSplitPaneOrientation(JSplitPane.HORIZONTAL_SPLIT);
		a.assertSplitPaneFirstComponent(label0);
		a.assertSplitPaneSecondComponent(label1);
	}

	@Test
	public void verticalSplitPane() {
		VerticalSplitPaneBuilder actual = builder.verticalSplitPane(label0, label1);
		Assertions a = new Assertions(actual);
		a.assertSplitPaneOrientation(JSplitPane.VERTICAL_SPLIT);
		a.assertSplitPaneFirstComponent(label0);
		a.assertSplitPaneSecondComponent(label1);
	}

	@Test
	public void verticalBox() {
		BoxBuilder actual = builder.verticalBox();
		Assertions a = new Assertions(actual);
		a.assertBoxIsVertical();
	}
	
	@Test
	public void horizontalBox() {
		BoxBuilder actual = builder.horizontalBox();
		Assertions a = new Assertions(actual);
		a.assertBoxIsHorizontal();
	}
	
	@Test
	public void card() {
		CardBuilder actual = builder.card(new CardLayout()).add(label0, "some name");
		Assertions a = new Assertions(actual);
		a.assertCardLayoutName(label0, "some name");
	}
	
	@Test
	public void tabs() {
		TabbedPaneBuilder actual = builder.tabs().add("some name", label0);
		Assertions a = new Assertions(actual);
		a.assertTabbedPaneTab(0, "some name", null, label0);
	}
	
	@Test
	public void tabsSupplyingPanel() {
		JTabbedPane expected = new JTabbedPane();
		TabbedPaneBuilder actual = builder.tabs(expected);
		Assertions a = new Assertions(actual);
		assertEquals(expected, a.result());
	}
	
	private void assertLayoutType(Class<?> expectedClass, PanelBuilder<?> actual) {
		assertEquals(expectedClass, actual.getClass());
	}
	
	@Test
	public void defaultConstructorUsesDefaultComponentConverter() {
		LayoutBuilder builder = new LayoutBuilder();
		JLabel actualLabel = (JLabel)builder.flow("x").build().getComponent(0);
		assertEquals("x", actualLabel.getText());
	}
}
