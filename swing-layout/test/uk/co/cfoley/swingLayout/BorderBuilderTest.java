package uk.co.cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class BorderBuilderTest {
	JLabel N, S, E, W, C;
	
	BorderBuilder bb;
	Assertions A;
	
	@Before
	public void setUp() {
		bb = new BorderBuilder(new ComponentConvertedReturnOriginal());
		N = new JLabel();
		S = new JLabel();
		E = new JLabel();
		W = new JLabel();
		C = new JLabel();
		A = new Assertions(bb);
	}
	
	@Test
	public void addingNoElements() {
		A.assertComponents();
	}
	
	@Test
	public void addNorth() {
		bb.north(N);
		A.assertComponents(N);	
		A.assertBorderLayoutConstraints(BorderLayout.NORTH, N);
	}

	@Test
	public void addEast() {
		bb.east(E);
		A.assertComponents(E);	
		A.assertBorderLayoutConstraints(BorderLayout.EAST, E);
	}

	@Test
	public void addWest() {
		bb.west(W);
		A.assertComponents(W);	
		A.assertBorderLayoutConstraints(BorderLayout.WEST, W);
	}

	@Test
	public void addSouth() {
		bb.south(S);
		A.assertComponents(S);	
		A.assertBorderLayoutConstraints(BorderLayout.SOUTH, S);
	}

	@Test
	public void addCenter() {
		bb.center(C);
		A.assertComponents(C);	
		A.assertBorderLayoutConstraints(BorderLayout.CENTER, C);
	}
	
	@Test
	public void combination() {
		bb.center(C).north(N);
		A.assertComponents(N, C);	
		A.assertBorderLayoutConstraints(BorderLayout.CENTER, C);
		A.assertBorderLayoutConstraints(BorderLayout.NORTH, N);
	}
	
	@Test
	public void defaultGap() {
		A.assertBorderLayoutGaps(0, 0);
	}
	
	@Test
	public void horizontalGap() {
		bb.hgap(5);
		A.assertBorderLayoutGaps(5, 0);
	}
	
	@Test
	public void verticalGap() {
		bb.vgap(7);
		A.assertBorderLayoutGaps(0, 7);
	}
	
	@Test
	public void bothGaps() {
		bb.vgap(11).hgap(13);
		A.assertBorderLayoutGaps(13, 11);
	}
	
	@Test
	public void bothGapsSame() {
		bb.gap(17);
		A.assertBorderLayoutGaps(17, 17);
	}
	
	@Test
	public void complex() {
		bb
				.hgap(7)
				.center(C)
				.north(N)
				.pad(5)
				.east(E);
		A.assertComponents(N, E, C);
		A.assertBorderLayoutConstraints(BorderLayout.CENTER, C);
		A.assertBorderLayoutConstraints(BorderLayout.NORTH, N);
		A.assertBorderLayoutConstraints(BorderLayout.EAST, E);
		A.assertBorderLayoutGaps(7, 0);
		A.assertPadding(5, 5, 5, 5);
	}
}
