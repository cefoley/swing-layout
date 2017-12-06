package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class DefaultComponentConverterTest {
	ComponentConverter converter;
	
	@Before
	public void setUp() {
		converter = new DefaultComponentConverter();
	}
	
	@Test
	public void srtringsConvertedToLabels() {
		JLabel c = (JLabel)converter.toComponent("abc");
		assertEquals("abc", c.getText());
	}
	
	@Test
	public void objectsConvertedToLabels() {
		JLabel c = (JLabel)converter.toComponent(new Integer(45));
		assertEquals("45", c.getText());		
	}
	
	@Test
	public void jComponentsSimplyReturned() {
		JComponent expected = new JPanel();
		JPanel c = (JPanel)converter.toComponent(expected);
		assertSame(expected, c);
	}
	
	@Test
	public void componentWrappedInJPanel() {
		Component input = new Label("abc");
		JPanel actual = (JPanel)converter.toComponent(input);
		assertArrayEquals(new Component[] {input}, actual.getComponents());
		BorderLayout layout = (BorderLayout)actual.getLayout();
		assertEquals(BorderLayout.CENTER, layout.getConstraints(input));
	}
	
	@Test
	public void jComponentBuildersAreBuilt() {
		JComponent expected = new JPanel();
		JComponentBuilder c = new JComponentBuilderStub(expected);
		JComponent actual = converter.toComponent(c);
		assertSame(expected, actual);
	}
}
