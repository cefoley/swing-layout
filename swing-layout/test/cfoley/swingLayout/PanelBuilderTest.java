package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import org.junit.*;

public class PanelBuilderTest {
	
	JComponent input;
	PanelBuilder<?> builder;
	
	@Before
	public void setUp() {
		input = new JPanel();
		builder = new PanelBuilderSubclassStub(input, null);
	}
	
	@Test
	public void panelBuilderReturnsJcCmponentFromSubclass() {
		JComponent result = builder.build();
		assertSame(input, result);
	}

	@Test
	public void panelBuilderCanAddBorder() {
		Border border = BorderFactory.createLineBorder(Color.RED);
		JComponent result = builder.border(border).build();
		assertSame(input, result);
		assertSame(border, result.getBorder());
	}
	
	@Test
	public void createBorderFromTopPadding() {
		JComponent result = builder.padTop(4).build();
		assertSame(input, result);
		assertPadding(result.getBorder(), 4, 0, 0, 0);
	}

	@Test
	public void createBorderFromBottomPadding() {
		JComponent result = builder.padBottom(7).build();
		assertSame(input, result);
		assertPadding(result.getBorder(), 0, 0, 7, 0);
	}

	@Test
	public void createBorderFromLeftPadding() {
		JComponent result = builder.padLeft(5).build();
		assertSame(input, result);
		assertPadding(result.getBorder(), 0, 5, 0, 0);
	}

	@Test
	public void createBorderFromRightPadding() {
		JComponent result = builder.padRight(2).build();
		assertSame(input, result);
		assertPadding(result.getBorder(), 0, 0, 0, 2);
	}

	@Test
	public void createBorderFromSeveralPadding() {
		JComponent result = builder
				.padTop(2)
				.padLeft(3)
				.padBottom(5)
				.padRight(7)
				.build();
		assertSame(input, result);
		assertPadding(result.getBorder(), 2, 3, 5, 7);
	}

	@Test
	public void createBorderFromEvenPadding() {
		JComponent result = builder.pad(6).build();
		assertSame(input, result);
		assertPadding(result.getBorder(), 6, 6, 6, 6);
	}
	
	@Test
	public void combineBorderAndPadding() {
		Border border = BorderFactory.createLineBorder(Color.RED);
		JComponent result = builder.pad(7).border(border).build();
		assertSame(input, result);
		CompoundBorder compoundBorder = (CompoundBorder)result.getBorder();
		assertSame(border, compoundBorder.getInsideBorder());
		assertPadding(compoundBorder.getOutsideBorder(), 7, 7, 7, 7);
	}

	@Test
	public void combinePaddingWithExistingBorder() {
		Border originalBorder = BorderFactory.createLineBorder(Color.BLUE);
		input.setBorder(originalBorder);
		JComponent result = builder.pad(7).build();
		assertPadding(result.getBorder(), 7, 7, 7, 7);
		assertSame(originalBorder, input.getBorder());
	}

	@Test
	public void nestedComponentFillsEntireArea() {
		Border originalBorder = BorderFactory.createLineBorder(Color.BLUE);
		input.setBorder(originalBorder);
		JComponent result = builder.pad(7).build();
		assertArrayEquals(new Component[] {input}, result.getComponents());
		BorderLayout layout = (BorderLayout)result.getLayout();
		assertEquals(BorderLayout.CENTER, layout.getConstraints(input));
	}

	private void assertPadding(Border actual, int top, int left, int bottom, int right) {
		Insets insets = ((EmptyBorder)actual).getBorderInsets(null);
		assertEquals(top, insets.top);
		assertEquals(left, insets.left);
		assertEquals(bottom, insets.bottom);
		assertEquals(right, insets.right);
	}
}
