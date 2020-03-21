package cfoley.swingLayout;

import static org.junit.Assert.*;

import javax.swing.*;

import org.junit.*;

public class VerticalSplitPanelBuilderTest {
	VerticalSplitPaneBuilder builder;
	Assertions a;
	
	@Before
	public void setUp() {
		builder = new VerticalSplitPaneBuilder(new ComponentConvertedReturnOriginal());
		a = new Assertions(builder);
	}
	
	@Test
	public void empty() {
		a.assertSplitPaneOrientation(JSplitPane.VERTICAL_SPLIT);
		a.assertSplitPaneContinuousLayoutIsDefault();
		a.assertSplitPaneOneTouchExpandable(false);
		a.assertSplitPaneFirstComponent(null);
		a.assertSplitPaneSecondComponent(null);
		a.assertSplitPaneResizeWeight(0.5);
	}

	@Test
	public void withTopAndBottomComponents() {
		Object expectedTop = new JLabel("on the top");
		Object expectedBottom = new JLabel("on the bottom");
		builder.top(expectedTop).bottom(expectedBottom);
		a.assertSplitPaneFirstComponent(expectedTop);
		a.assertSplitPaneSecondComponent(expectedBottom);
	}
	
	@Test
	public void settingSizesWithoutComponentsDoesNotThrowAnException() {
		builder
			.topMinimumSize(10, 20)
			.bottomMinimumSize(10, 20)
			.topPreferredSize(10, 20)
			.bottomPreferredSize(10, 20)
			.build();
		// Pass if no exception is thrown.
	}
	
	@Test
	public void topMinimumSize() {
		builder.topMinimumSize(10, 20).top(new JPanel());
		a.assertSplitPaneFirstMinimumSize(10, 20);
	}
	
	@Test
	public void bottomMinimumSize() {
		builder.bottomMinimumSize(10, 20).bottom(new JPanel());
		a.assertSplitPaneSecondMinimumSize(10, 20);
	}
	
	@Test
	public void topPreferredSize() {
		builder.topPreferredSize(10, 20).top(new JPanel());
		a.assertSplitPaneFirstPreferredSize(10, 20);
	}
	
	@Test
	public void bottomPreferredSize() {
		builder.bottomPreferredSize(10, 20).bottom(new JPanel());
		a.assertSplitPaneSecondPreferredSize(10, 20);
	}
	
	@Test
	public void preferredSize() {
		builder.preferredSize(10, 20);
		a.assertPreferredSize(10, 20);
	}
	
	@Test
	public void resizeWeight() {
		builder.resizeWeight(0.3);
		a.assertSplitPaneResizeWeight(0.3);
	}
	
	@Test
	public void resizeWeightsBetweenZeroAndOneThrowNoException() {
		builder.resizeWeight(0).resizeWeight(1);
		// Pass if no exception is thrown.
	}
	
	@Test
	public void resizeWeightBelowZeroThrowsException() {
		try {
			builder.resizeWeight(-0.1);
			fail("Expected exception not thrown.");
		} catch (IllegalArgumentException e) {
			assertEquals("Resize weight must be in range 0--1, not -0.1.", e.getMessage());
		}
	}
	
	@Test
	public void resizeWeightAboveOneThrowsException() {
		try {
			builder.resizeWeight(1.1);
			fail("Expected exception not thrown.");
		} catch (IllegalArgumentException e) {
			assertEquals("Resize weight must be in range 0--1, not 1.1.", e.getMessage());
		}
	}
	
	@Test
	public void testContunuousLayoutOn() {
		builder.continuousLayoutOn();
		a.assertSplitPaneContinuousLayout(true);
	}
	
	@Test
	public void testContunuousLayoutOff() {
		builder.continuousLayoutOff();
		a.assertSplitPaneContinuousLayout(false);
	}
	
	@Test
	public void testContunuousLayoutFromOnToLookAndFeelDefault() {
		builder.continuousLayoutOn().continuousLayoutToLookAndFeelDefault();
		a.assertSplitPaneContinuousLayoutIsDefault();
	}
	
	@Test
	public void testContunuousLayoutFromOffToLookAndFeelDefault() {
		builder.continuousLayoutOff().continuousLayoutToLookAndFeelDefault();
		a.assertSplitPaneContinuousLayoutIsDefault();
	}
	
	@Test
	public void testChangeOneTouchExpandableFromOffToOn() {
		builder.oneTouchExpandableOff().oneTouchExpandableOn();
		a.assertSplitPaneOneTouchExpandable(true);
	}
	
	@Test
	public void testChangeOneTouchExpandableFromOnToOff() {
		builder.oneTouchExpandableOn().oneTouchExpandableOff();
		a.assertSplitPaneOneTouchExpandable(false);
	}
	
	@Test
	public void complex() {
		JLabel top = new JLabel("on the top");
		JLabel bottom = new JLabel("on the bottom");
		builder
			.top(top)
			.bottom(bottom)
			.topMinimumSize(10, 20)
			.bottomPreferredSize(30, 40)
			.continuousLayoutOn()
			.resizeWeight(0.8)
			.oneTouchExpandableOn();
		a.assertSplitPaneFirstComponent(top);
		a.assertSplitPaneSecondComponent(bottom);
		a.assertSplitPaneFirstMinimumSize(10, 20);
		a.assertSplitPaneSecondPreferredSize(30, 40);
		a.assertSplitPaneContinuousLayout(true);
		a.assertSplitPaneResizeWeight(0.8);
		a.assertSplitPaneOneTouchExpandable(true);
	}
}
