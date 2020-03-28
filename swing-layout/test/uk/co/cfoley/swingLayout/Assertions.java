package uk.co.cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.Box.*;
import javax.swing.border.*;

public class Assertions {
	private PanelBuilder<?> builder;
	private JComponent builtComponent;

	public Assertions(PanelBuilder<?> builder) {
		this.builder = builder;
		this.builtComponent = null;
	}
	
	public JComponent result() {
		if (builtComponent == null) 
			builtComponent = builder.build();
		return builtComponent;
	}
	
	public void assertResultIs(JComponent expected) {
		assertSame(expected, result());
	}
	
	public void assertBorderSame(Border expected) {
		assertSame(expected, result().getBorder());
	}
	
	public void assertInsideBorderSame(Border expected) {
		CompoundBorder compoundBorder = (CompoundBorder)result().getBorder();
		assertSame(expected, compoundBorder.getInsideBorder());
	}
	
	public void assertPadding(int top, int left, int bottom, int right) {
		Insets insets = (result().getBorder() == null)
				? new Insets(0, 0, 0, 0)
				: ((EmptyBorder)result().getBorder()).getBorderInsets(null);
		assertInsets(insets, top, left, bottom, right);
	}

	public void assertPaddingOnOutsideBorder(int top, int left, int bottom, int right) {
		CompoundBorder compoundBorder = (CompoundBorder)result().getBorder();
		Insets insets = ((EmptyBorder)compoundBorder.getOutsideBorder()).getBorderInsets(null);
		assertInsets(insets, top, left, bottom, right);
	}

	private void assertInsets(Insets insets, int top, int left, int bottom, int right) {
		assertEquals(top, insets.top);
		assertEquals(left, insets.left);
		assertEquals(bottom, insets.bottom);
		assertEquals(right, insets.right);
	}

	public void assertColor(Color expected) {
		assertEquals(expected, result().getBackground());
	}

	public void assertBorderLayoutGaps(int expectedHorizontalGap, int expectedVerticalGap) {
		assertEquals(expectedHorizontalGap, borderLayout().getHgap());
		assertEquals(expectedVerticalGap, borderLayout().getVgap());
	}

	private BorderLayout borderLayout() {
		return (BorderLayout)result().getLayout();
	}

	public void assertBorderLayoutConstraints(String expected, JComponent input) {
		BorderLayout layout = borderLayout();
		assertEquals(expected, layout.getConstraints(input));
	}

	public void assertComponents(Component... expecteds) {
		Component[] actuals = result().getComponents();
		assertEquals(expecteds.length, actuals.length);
		for (int i = 0; i < actuals.length; i++) {
			String failureMessage = "Components differed at index " + i + ".";
			if (expecteds[i] instanceof Filler) {
				assertTrue(failureMessage, actuals[i] instanceof Filler);
				assertFillersEqual((Filler)expecteds[i], (Filler)actuals[i]);
			} else {
				assertEquals(failureMessage, expecteds[i], actuals[i]);
			}
		}
	}

	private void assertFillersEqual(Filler expected, Filler actual) {
		assertEquals(expected.getMinimumSize(), actual.getMinimumSize());
		assertEquals(expected.getMaximumSize(), actual.getMaximumSize());
	}

	public void assertGridRowsAndColumns(int expectedRows, int expectedColumns) {
		assertEquals(expectedRows, gridLayout().getRows());
		assertEquals(expectedColumns, gridLayout().getColumns());
	}

	private GridLayout gridLayout() {
		return (GridLayout)result().getLayout();
	}

	public void assertGridGap(int expectedHGap, int expectedVGap) {
		assertEquals(expectedHGap, gridLayout().getHgap());
		assertEquals(expectedVGap, gridLayout().getVgap());
	}
	
	public void assertFlowLayoutGaps(int expectedHorizontalGap, int expectedVerticalGap) {
		assertEquals(expectedHorizontalGap, flowLayout().getHgap());
		assertEquals(expectedVerticalGap, flowLayout().getVgap());
	}

	private FlowLayout flowLayout() {
		return (FlowLayout)result().getLayout();
	}

	public void assertFlowLayoutAlignment(int expected) {
		assertEquals(expected, flowLayout().getAlignment());
	}

	public void assertFlowLayoutVAlignedOnBaseline(boolean expected) {
		assertEquals(expected, flowLayout().getAlignOnBaseline());
	}

	public void assertJScrollPaneComponentComponent(JComponent expected) {
		JScrollPane panel = jScrollPane();
		Component actual = panel.getViewport().getView();
		assertSame(expected, actual);
	}

	private JScrollPane jScrollPane() {
		return (JScrollPane)result();
	}

	public void assertScrollBars(int expectedHorizontal, int expectedVertical) {
		assertEquals(expectedHorizontal, jScrollPane().getHorizontalScrollBarPolicy());
		assertEquals(expectedVertical, jScrollPane().getVerticalScrollBarPolicy());
		
	}

	public void assertCardLayoutName(JComponent expected, String name) {
		Component actual = cardLayoutComponentForName(name);
		assertSame(expected, actual);
	}

	private Component cardLayoutComponentForName(String name) {
		JComponent parent = result();
		CardLayout layout = (CardLayout)parent.getLayout();
		layout.show(parent, name);
		return Arrays.asList(parent.getComponents())
				.stream()
				.filter(c -> c.isVisible())
				.findFirst().get();
	}

	public void assertTabbedPanePlacement(int expected) {
		assertEquals(expected, jTabbedPane().getTabPlacement());
	}

	private JTabbedPane jTabbedPane() {
		return (JTabbedPane)result();
	}

	public void assertTabbedPaneLayoutPolicy(int expected) {
		assertEquals(expected, jTabbedPane().getTabLayoutPolicy());
	}

	public void assertTabbedPaneTab(
			int index, 
			String expectedLabel, 
			Icon expectedIcon, 
			Object expectedContent
	) {
		JTabbedPane actual = jTabbedPane();
		assertEquals(expectedLabel, actual.getTitleAt(index));
		assertEquals(expectedIcon, actual.getIconAt(index));
		assertEquals(expectedContent, actual.getComponentAt(index));
		
	}

	public void assertSplitPaneOrientation(int expected) {
		assertEquals(expected, splitPane().getOrientation());
	}

	private JSplitPane splitPane() {
		return (JSplitPane)result();
	}

	public void assertSplitPaneFirstComponent(Object expected) {
		assertEquals(expected, splitPane().getLeftComponent());
	}

	public void assertSplitPaneSecondComponent(Object expected) {
		assertEquals(expected, splitPane().getRightComponent());
	}

	public void assertSplitPaneFirstMinimumSize(int expectedWidth, int expectedHeight) {
		Dimension expected = new Dimension(expectedWidth, expectedHeight);
		assertEquals(expected, splitPane().getLeftComponent().getMinimumSize());
	}

	public void assertSplitPaneSecondMinimumSize(int expectedWidth, int expectedHeight) {
		Dimension expected = new Dimension(expectedWidth, expectedHeight);
		assertEquals(expected, splitPane().getRightComponent().getMinimumSize());
	}

	public void assertSplitPaneFirstPreferredSize(int expectedWidth, int expectedHeight) {
		Dimension expected = new Dimension(expectedWidth, expectedHeight);
		assertEquals(expected, splitPane().getLeftComponent().getPreferredSize());
	}

	public void assertSplitPaneSecondPreferredSize(int expectedWidth, int expectedHeight) {
		Dimension expected = new Dimension(expectedWidth, expectedHeight);
		assertEquals(expected, splitPane().getRightComponent().getPreferredSize());
	}

	public void assertPreferredSize(int expectedWidth, int expectedHeight) {
		Dimension expected = new Dimension(expectedWidth, expectedHeight);
		assertEquals(expected, result().getPreferredSize());
	}

	public void assertSplitPaneResizeWeight(double expected) {
		assertEquals(expected, splitPane().getResizeWeight(), 0.0001);
	}

	public void assertSplitPaneContinuousLayoutIsDefault() {
		boolean expected = new JSplitPane().isContinuousLayout();
		assertSplitPaneContinuousLayout(expected);
	}
	
	public void assertSplitPaneContinuousLayout(boolean expected) {
		assertEquals(expected, splitPane().isContinuousLayout());
	}

	public void assertSplitPaneOneTouchExpandable(boolean expected) {
		assertEquals(expected, splitPane().isOneTouchExpandable());
	}

	public void assertBoxIsVertical() {
		assertEquals(BoxLayout.Y_AXIS, boxLayout().getAxis());
	}

	private BoxLayout boxLayout() {
		return (BoxLayout)box().getLayout();
	}

	private Box box() {
		return (Box)result();
	}

	public void assertBoxIsHorizontal() {
		assertEquals(BoxLayout.X_AXIS, boxLayout().getAxis());
	}
}
