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
		BorderLayout layout = (BorderLayout)result().getLayout();
		assertEquals(expectedHorizontalGap, layout.getHgap());
		assertEquals(expectedVerticalGap, layout.getVgap());
	}

	public void assertBorderLayoutConstraints(String expected, JComponent input) {
		BorderLayout layout = (BorderLayout)result().getLayout();
		assertEquals(expected, layout.getConstraints(input));
	}

	public void assertComponents(Component... components) {
		assertArrayEquals(components, result().getComponents());
	}

	public void assertGridRowsAndColumns(int expectedRows, int expectedColumns) {
		GridLayout layout = (GridLayout)result().getLayout();
		assertEquals(expectedRows, layout.getRows());
		assertEquals(expectedColumns, layout.getColumns());
	}

	public void assertGridGap(int expectedHGap, int expectedVGap) {
		GridLayout layout = (GridLayout)result().getLayout();
		assertEquals(expectedHGap, layout.getHgap());
		assertEquals(expectedVGap, layout.getVgap());
	}

}
