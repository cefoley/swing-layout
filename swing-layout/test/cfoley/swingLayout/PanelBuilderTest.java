package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;

import org.junit.*;

public class PanelBuilderTest {
	
	JComponent input;
	PanelBuilder<?> builder;
	Assertions a;
	
	@Before
	public void setUp() {
		input = new JPanel();
		builder = new PanelBuilderSubclassStub(input, null);
		a = new Assertions(builder);
	}
	
	@Test
	public void panelBuilderReturnsJcCmponentFromSubclass() {
		a.assertResultIs(input);
	}

	@Test
	public void panelBuilderCanAddBorder() {
		Border border = BorderFactory.createLineBorder(Color.RED);
		builder.border(border);
		a.assertResultIs(input);
		a.assertBorderSame(border);
	}
	
	@Test
	public void createBorderFromTopPadding() {
		builder.padTop(4);
		a.assertResultIs(input);
		a.assertPadding(4, 0, 0, 0);
	}

	@Test
	public void createBorderFromBottomPadding() {
		builder.padBottom(7);
		a.assertResultIs(input);
		a.assertPadding(0, 0, 7, 0);
	}

	@Test
	public void createBorderFromLeftPadding() {
		builder.padLeft(5);
		a.assertResultIs(input);
		a.assertPadding(0, 5, 0, 0);
	}

	@Test
	public void createBorderFromRightPadding() {
		builder.padRight(2);
		a.assertResultIs(input);
		a.assertPadding(0, 0, 0, 2);
	}

	@Test
	public void createBorderFromSeveralPadding() {
		builder
				.padTop(2)
				.padLeft(3)
				.padBottom(5)
				.padRight(7);
		a.assertResultIs(input);
		a.assertPadding(2, 3, 5, 7);
	}

	@Test
	public void createBorderFromEvenPadding() {
		builder.pad(6);
		a.assertResultIs(input);
		a.assertPadding(6, 6, 6, 6);
	}
	
	@Test
	public void combineBorderAndPadding() {
		Border border = BorderFactory.createLineBorder(Color.RED);
		builder.pad(7).border(border);
		a.assertResultIs(input);
		a.assertInsideBorderSame(border);
		a.assertPaddingOnOutsideBorder(7, 7, 7, 7);
	}

	@Test
	public void colour() {
		builder.colour(Color.RED);
		a.assertColor(Color.RED);
	}
	
	@Test
	public void combinePaddingWithExistingBorder() {
		Border originalBorder = BorderFactory.createLineBorder(Color.BLUE);
		input.setBorder(originalBorder);
		builder.pad(7);
		a.assertPadding(7, 7, 7, 7);
		assertSame(originalBorder, input.getBorder());
	}

	@Test
	public void nestedComponentFillsEntireArea() {
		Border originalBorder = BorderFactory.createLineBorder(Color.BLUE);
		input.setBorder(originalBorder);
		builder.pad(7);
		a.assertComponents(input);
		a.assertBorderLayoutConstraints(BorderLayout.CENTER, input);
	}
}
