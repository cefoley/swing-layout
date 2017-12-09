package cfoley.swingLayout;

import javax.swing.*;

import org.junit.*;

public class ScrollerBuilderTest {
	ScrollerBuilder builder;
	Assertions a;
	
	JComponent component;
	
	@Before
	public void setUp() {
		component = new JPanel();
		builder = new ScrollerBuilder(new ComponentConvertedReturnOriginal(), component);
		a = new Assertions(builder);
	}
	
	
	@Test
	public void defaultSettings() {
		a.assertJScrollPaneComponentComponent(component);
		a.assertScrollBars(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}
	
	@Test
	public void horizontalAlways() {
		builder.horizontal();
		a.assertScrollBars(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS, JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}

	@Test
	public void horizontalAsNeeded() {
		builder.horizontalAsNeeded();
		a.assertScrollBars(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED, JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}

	@Test
	public void verticalAlways() {
		builder.vertical();
		a.assertScrollBars(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}

	@Test
	public void verticalAsNeeded() {
		builder.verticalAsNeeded();
		a.assertScrollBars(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	@Test
	public void padding() {
		builder.pad(2);
		a.assertPadding(2, 2, 2, 2);
	}
	
}
