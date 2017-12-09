package cfoley.swingLayout;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.awt.*;

import javax.swing.*;
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
		Insets insets = ((EmptyBorder)result().getBorder()).getBorderInsets(null);
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

	public void assertComponents(Component... components) {
		assertArrayEquals(components, result().getComponents());
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

}
