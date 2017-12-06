package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import org.junit.*;

public class BorderBuilderTest {
	JLabel N, S, E, W, C;
	
	BorderBuilder bb;
	JComponent result;
	
	@Before
	public void setUp() {
		bb = new BorderBuilder(new ComponentConvertedReturnOriginal());
		result = null;
		N = new JLabel();
		S = new JLabel();
		E = new JLabel();
		W = new JLabel();
		C = new JLabel();
	}
	
	@Test
	public void addingNoElements() {
		result = bb.build();
		assertComponents();
	}
	
	@Test
	public void addNorth() {
		result = bb.north(N).build();
		assertComponents(N);	
		assertConstraints(BorderLayout.NORTH, N);
	}

	@Test
	public void addEast() {
		result = bb.east(E).build();
		assertComponents(E);	
		assertConstraints(BorderLayout.EAST, E);
	}

	@Test
	public void addWest() {
		result = bb.west(W).build();
		assertComponents(W);	
		assertConstraints(BorderLayout.WEST, W);
	}

	@Test
	public void addSouth() {
		result = bb.south(S).build();
		assertComponents(S);	
		assertConstraints(BorderLayout.SOUTH, S);
	}

	@Test
	public void addCenter() {
		result = bb.center(C).build();
		assertComponents(C);	
		assertConstraints(BorderLayout.CENTER, C);
	}
	
	@Test
	public void combination() {
		result = bb.center(C).north(N).build();
		assertComponents(N, C);	
		assertConstraints(BorderLayout.CENTER, C);
		assertConstraints(BorderLayout.NORTH, N);
	}
	
	@Test
	public void defaultGap() {
		result = bb.build();
		assertGaps(0, 0);
	}
	
	@Test
	public void horizontalGap() {
		result = bb.hgap(5).build();
		assertGaps(5, 0);
	}
	
	@Test
	public void verticalGap() {
		result = bb.vgap(7).build();
		assertGaps(0, 7);
	}
	
	@Test
	public void bothGaps() {
		result = bb.vgap(11).hgap(13).build();
		assertGaps(13, 11);
	}
	
	@Test
	public void bothGapsSame() {
		result = bb.gap(17).build();
		assertGaps(17, 17);
	}
	
	@Test
	public void complex() {
		result = bb
				.hgap(7)
				.center(C)
				.north(N)
				.pad(5)
				.east(E)
				.build();
		assertComponents(N, E, C);
		assertConstraints(BorderLayout.CENTER, C);
		assertConstraints(BorderLayout.NORTH, N);
		assertConstraints(BorderLayout.EAST, E);
		assertGaps(7, 0);
		assertPadding(result.getBorder(), 5, 5, 5, 5);
	}
	
	private void assertPadding(Border actual, int top, int left, int bottom, int right) {
		Insets insets = ((EmptyBorder)actual).getBorderInsets(null);
		assertEquals(top, insets.top);
		assertEquals(left, insets.left);
		assertEquals(bottom, insets.bottom);
		assertEquals(right, insets.right);
	}

	private void assertGaps(int expectedHorizontalGap, int expectedVerticalGap) {
		BorderLayout layout = (BorderLayout)result.getLayout();
		assertEquals(expectedHorizontalGap, layout.getHgap());
		assertEquals(expectedVerticalGap, layout.getVgap());
	}
	
	

	private void assertConstraints(String expected, JLabel component) {
		BorderLayout layout = (BorderLayout)result.getLayout();
		assertEquals(expected, layout.getConstraints(component));
	}

	private void assertComponents(Component... components) {
		assertArrayEquals(components, result.getComponents());
	}
}
