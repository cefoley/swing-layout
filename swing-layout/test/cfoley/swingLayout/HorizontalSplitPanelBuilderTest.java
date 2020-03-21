package cfoley.swingLayout;

import static org.junit.Assert.*;

import javax.swing.*;

import org.junit.*;

public class HorizontalSplitPanelBuilderTest {
	HorizontalSplitPaneBuilder builder;
	Assertions a;
	
	@Before
	public void setUp() {
		builder = new HorizontalSplitPaneBuilder(new ComponentConvertedReturnOriginal());
		a = new Assertions(builder);
	}
	
	@Test
	public void empty() {
		a.assertSplitPaneOrientation(JSplitPane.HORIZONTAL_SPLIT);
		a.assertSplitPaneContinuousLayoutIsDefault();
		a.assertSplitPaneOneTouchExpandable(false);
		a.assertSplitPaneFirstComponent(null);
		a.assertSplitPaneSecondComponent(null);
		a.assertSplitPaneResizeWeight(0.5);
	}

	@Test
	public void withLeftAndRightComponents() {
		Object expectedLeft = new JLabel("on the left");
		Object expectedRight = new JLabel("on the right");
		builder.left(expectedLeft).right(expectedRight);
		a.assertSplitPaneFirstComponent(expectedLeft);
		a.assertSplitPaneSecondComponent(expectedRight);
	}
	
	@Test
	public void settingSizesWithoutComponentsDoesNotThrowAnException() {
		builder
			.leftMinimumSize(10, 20)
			.rightMinimumSize(10, 20)
			.leftPreferredSize(10, 20)
			.rightPreferredSize(10, 20)
			.build();
		// Pass if no exception is thrown.
	}
	
	@Test
	public void leftMinimumSize() {
		builder.leftMinimumSize(10, 20).left(new JPanel());
		a.assertSplitPaneFirstMinimumSize(10, 20);
	}
	
	@Test
	public void rightMinimumSize() {
		builder.rightMinimumSize(10, 20).right(new JPanel());
		a.assertSplitPaneSecondMinimumSize(10, 20);
	}
	
	@Test
	public void leftPreferredSize() {
		builder.leftPreferredSize(10, 20).left(new JPanel());
		a.assertSplitPaneFirstPreferredSize(10, 20);
	}
	
	@Test
	public void rightPreferredSize() {
		builder.rightPreferredSize(10, 20).right(new JPanel());
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
		JLabel left = new JLabel("on the left");
		JLabel right = new JLabel("on the right");
		builder
			.left(left)
			.right(right)
			.leftMinimumSize(10, 20)
			.rightPreferredSize(30, 40)
			.continuousLayoutOn()
			.resizeWeight(0.8)
			.oneTouchExpandableOn();
		a.assertSplitPaneFirstComponent(left);
		a.assertSplitPaneSecondComponent(right);
		a.assertSplitPaneFirstMinimumSize(10, 20);
		a.assertSplitPaneSecondPreferredSize(30, 40);
		a.assertSplitPaneContinuousLayout(true);
		a.assertSplitPaneResizeWeight(0.8);
		a.assertSplitPaneOneTouchExpandable(true);
	}
}
