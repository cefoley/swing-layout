package cfoley.swingLayout;

import javax.swing.*;

import org.junit.*;

public class GridBuilderTest {
	GridBuilder builder;
	Assertions a;
	
	private JComponent A, B, C;
	
	@Before
	public void setUp() {
		builder = new GridBuilder(new ComponentConvertedReturnOriginal());
		a = new Assertions(builder);
		A = new JLabel("A");
		B = new JLabel("B");
		C = new JLabel("C");
	}
	
	@Test
	public void rows() {
		builder.rows(3);
		a.assertGridRowsAndColumns(3, 0);
	}

	@Test
	public void columns() {
		builder.cols(5);
		a.assertGridRowsAndColumns(0, 5);
	}

	@Test
	public void rowsAndColumns() {
		builder.cols(7).rows(11);
		a.assertGridRowsAndColumns(11, 7);
	}
	
	@Test
	public void defaultGap() {
		builder.rows(3);
		a.assertGridGap(0, 0);
	}

	@Test
	public void hGap() {
		builder.rows(3).hgap(11);
		a.assertGridGap(11, 0);
	}

	@Test
	public void vGap() {
		builder.rows(3).vgap(13);
		a.assertGridGap(0, 13);
	}

	@Test
	public void bothGaps() {
		builder.rows(3).vgap(17).hgap(19);
		a.assertGridGap(19, 17);
	}

	@Test
	public void sameGap() {
		builder.rows(3).gap(21);
		a.assertGridGap(21, 21);
	}

	@Test
	public void noComponents() {
		builder.rows(3);
		a.assertComponents();
	}

	@Test
	public void oneComponent() {
		builder.rows(3).add(A);
		a.assertComponents(A);
	}

	@Test
	public void twoComponents() {
		builder.rows(3).add(A).add(B);
		a.assertComponents(A, B);
	}

	@Test
	public void multipleComponents() {
		builder.rows(3).add(A, B, C);
		a.assertComponents(A, B, C);
	}

	@Test
	public void padding() {
		builder.rows(1).pad(4);
		a.assertPadding(4, 4, 4, 4);
	}

}
