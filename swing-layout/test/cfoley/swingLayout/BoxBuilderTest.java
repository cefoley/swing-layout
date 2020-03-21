package cfoley.swingLayout;

import static org.junit.Assert.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.Box.*;

import org.junit.*;

public class BoxBuilderTest {
	BoxBuilder builder;
	Assertions a;
	
	@Before
	public void setUp() {
		builder = new BoxBuilder(new ComponentConvertedReturnOriginal());
		a = new Assertions(builder);
	}

	@Test
	public void empty() {
		a.assertBoxIsVertical();
		a.assertComponents();
	}
	
	@Test
	public void testVertical() {
		builder.vertical();
		a.assertBoxIsVertical();
	}
	
	@Test
	public void testHorizontal() {
		builder.horizontal();
		a.assertBoxIsHorizontal();
	}
	
	@Test
	public void testGlue() {
		builder.addGlue();
		a.assertComponents(glue());
	}

	private Filler glue() {
		return new Filler(
				new Dimension(0,0), new Dimension(0,0), 
				new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
	}
	
	@Test
	public void rigidArea() {
		builder.addRigidArea(10,  20);
		a.assertComponents(Box.createRigidArea(new Dimension(10, 20)));
	}
	
	@Test
	public void addComponent() {
		JLabel label = new JLabel("some label");
		builder.add(label);
		a.assertComponents(label);
	}
	
	@Test
	public void addTwoComponents() {
		JLabel label1 = new JLabel("some label");
		JLabel label2 = new JLabel("some other label");
		builder.add(label1).add(label2);
		a.assertComponents(label1, label2);
	}
	
	@Test
	public void addComponetThenSetVertical() {
		JLabel label1 = new JLabel("some label");
		builder.add(label1).vertical();
		a.assertComponents(label1);
		a.assertBoxIsVertical();
	}
	
	@Test
	public void addComponetThenSetHorizontal() {
		JLabel label1 = new JLabel("some label");
		builder.add(label1).horizontal();
		a.assertComponents(label1);
		a.assertBoxIsHorizontal();
	}
	
	@Test
	public void addComponetThenSetYAlignment() {
		JLabel label = new JLabel("some label");
		builder.vertical().add(label).alignment(0.2);
		a.assertComponents(label);
		assertEquals(0.2f, label.getAlignmentY(), 0.001);
	}
	
	@Test
	public void addComponetThenSetXAlignment() {
		JLabel label = new JLabel("some label");
		builder.horizontal().add(label).alignment(0.2);
		a.assertComponents(label);
		assertEquals(0.2f, label.getAlignmentX(), 0.001);
	}
	
	@Test
	public void alignmentsOfZeroAndOneAreAllowed() {
		builder.horizontal().add(new JLabel("some label"))
			.alignment(0).alignment(1);
		// Pass if no exception is thrown
	}
	
	@Test
	public void alignmentsLessThanZeroThrowException() {
		BoxBuilderConfigureLatest builder2 = builder.horizontal().add(new JLabel("some label"));
		try {
			builder2.alignment(-0.01);
		} catch (IllegalArgumentException e) {
			assertEquals("Alignment should be in range 0--1, not -0.01.", e.getMessage());
		}
		// Pass if no exception is thrown
	}
	
	@Test
	public void alignmentsMoreThanOmneThrowException() {
		BoxBuilderConfigureLatest builder2 = builder.horizontal().add(new JLabel("some label"));
		try {
			builder2.alignment(1.001);
		} catch (IllegalArgumentException e) {
			assertEquals("Alignment should be in range 0--1, not 1.001.", e.getMessage());
		}
		// Pass if no exception is thrown
	}
	
	@Test
	public void addComponetThenSetMinSize() {
		JLabel label = new JLabel("some label");
		builder.horizontal().add(label).minSize(7, 11);
		a.assertComponents(label);
		assertEquals(new Dimension(7, 11), label.getMinimumSize());
	}

	@Test
	public void addComponetThenSetMaxSize() {
		JLabel label = new JLabel("some label");
		builder.horizontal().add(label).maxSize(7, 11);
		a.assertComponents(label);
		assertEquals(new Dimension(7, 11), label.getMaximumSize());
	}

	@Test
	public void addComponetThenSetPreferredSize() {
		JLabel label = new JLabel("some label");
		builder.horizontal().add(label).preferredSize(7, 11);
		a.assertComponents(label);
		assertEquals(new Dimension(7, 11), label.getPreferredSize());
	}

	@Test
	public void addComponetThenAddRigidAreaFromCoordinates() {
		JLabel label = new JLabel("some label");
		builder.add(label).addRigidArea(3, 5);
		a.assertComponents(label, Box.createRigidArea(new Dimension(3, 5)));
	}

	@Test
	public void addComponetThenAddRigidAreaFromDimension() {
		JLabel label = new JLabel("some label");
		builder.add(label).addRigidArea(new Dimension(3, 5));
		a.assertComponents(label, Box.createRigidArea(new Dimension(3, 5)));
	}

	@Test
	public void addComponetThenBuildImmediately() {
		JLabel label = new JLabel("some label");
		Box actual = (Box) builder.add(label).build();
		assertEquals(1, actual.getComponentCount());
		assertEquals(label, actual.getComponent(0));
	}

	@Test
	public void complex() {
		builder.horizontal().pad(5).addGlue().addRigidArea(10, 20);
		a.assertBoxIsHorizontal();
		a.assertComponents(glue(), Box.createRigidArea(new Dimension(10, 20)));
	}
	
	@Test
	public void complexWithComponent() {
		JLabel label = new JLabel("some label");		
		builder.horizontal().pad(5).add(label).addGlue().addRigidArea(10, 20);
		a.assertBoxIsHorizontal();
		a.assertComponents(label, glue(), Box.createRigidArea(new Dimension(10, 20)));
		a.assertPadding(5, 5, 5, 5);
	}
}
