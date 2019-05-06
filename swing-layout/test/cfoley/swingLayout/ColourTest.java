package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class ColourTest {
	@Test
	public void testDefaultColour() {
		Color expected = new JPanel().getBackground();
		JComponent testee = new BorderBuilder(new DefaultComponentConverter()).build();
		assertEquals(expected, testee.getBackground());
	}
	
	@Test
	public void setColour() {
		JComponent testee = new BorderBuilder(new DefaultComponentConverter())
				.backgroundColour(Color.RED)
				.build();
		assertEquals(Color.RED, testee.getBackground());
		
	}
}
