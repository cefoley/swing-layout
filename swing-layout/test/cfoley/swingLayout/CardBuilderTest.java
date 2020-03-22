package cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class CardBuilderTest {
	CardBuilder builder;
	Assertions a;
	
	private JComponent A, B, C;

	@Before
	public void SetUp() {
		CardLayout layout = new CardLayout();
		builder = new CardBuilder(new ComponentConvertedReturnOriginal(), layout);
		a = new Assertions(builder);
		A = new JPanel();
		B = new JPanel();
		C = new JPanel();
	}
	
	@Test
	public void defaultCardLayout() {
		a.assertComponents();
	}
	
	@Test
	public void addComponentWithName() {
		builder.add(A, "x");
		a.assertComponents(A);
		a.assertCardLayoutName(A, "x");
	}
	
	@Test
	public void addSeveralComponentWithName() {
		builder.add(A, "x");
		builder.add(B, "y");
		builder.add(C, "z");
		a.assertComponents(A, B, C);
		a.assertCardLayoutName(A, "x");
		a.assertCardLayoutName(B, "y");
		a.assertCardLayoutName(C, "z");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addWithoutName() {
		builder.add(A);
		builder.add(B);
		a.assertComponents(A, B);
	}
	
	@Test
	public void complex() {
		builder.pad(7).add(A, "x").add(B, "y").add(C, "z");
		a.assertPadding(7, 7, 7, 7);
		a.assertComponents(A, B, C);
		a.assertCardLayoutName(A, "x");
		a.assertCardLayoutName(B, "y");
		a.assertCardLayoutName(C, "z");
	}
	

}
