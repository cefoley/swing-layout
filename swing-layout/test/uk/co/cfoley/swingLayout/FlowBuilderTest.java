package uk.co.cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;

import org.junit.*;

public class FlowBuilderTest {
	FlowBuilder builder;
	Assertions a;
	
	JPanel A, B, C;
	
	@Before
	public void setUp() {
		builder = new FlowBuilder(new ComponentConvertedReturnOriginal());
		a = new Assertions(builder);
		A = new JPanel();
		B = new JPanel();
		C = new JPanel();
	}
	
	@Test
	public void emptyWithDefaults() {
		a.assertComponents();
		a.assertFlowLayoutGaps(0, 0);
		a.assertFlowLayoutAlignment(FlowLayout.CENTER);
		a.assertFlowLayoutVAlignedOnBaseline(false);
		a.assertComponents();
	}
	
	@Test
	public void alignOnBaseline() {
		builder.vAlignOnBaseline();
		a.assertFlowLayoutVAlignedOnBaseline(true);
	}
	
	@Test
	public void alignOnCenter() {
		builder.vAlignOnBaseline().vAlignOnCenter();
		a.assertFlowLayoutVAlignedOnBaseline(false);
	}
	
	@Test
	public void alignLeft() {
		builder.alignLeft();
		a.assertFlowLayoutAlignment(FlowLayout.LEFT);
	}
	
	@Test
	public void alignRight() {
		builder.alignRight();
		a.assertFlowLayoutAlignment(FlowLayout.RIGHT);
	}
	
	@Test
	public void alignLeading() {
		builder.alignLeading();
		a.assertFlowLayoutAlignment(FlowLayout.LEADING);
	}
	
	@Test
	public void alignTrailing() {
		builder.alignTrailing();
		a.assertFlowLayoutAlignment(FlowLayout.TRAILING);
	}
	
	@Test
	public void alignCenter() {
		builder.alignTrailing().alignCenter();
		a.assertFlowLayoutAlignment(FlowLayout.CENTER);
	}
	
	@Test
	public void hGap() {
		builder.hgap(3);
		a.assertFlowLayoutGaps(3, 0);
	}
	
	@Test
	public void vGap() {
		builder.vgap(5);
		a.assertFlowLayoutGaps(0, 5);
	}
	
	@Test
	public void sameGap() {
		builder.gap(7);
		a.assertFlowLayoutGaps(7, 7);
	}
	
	@Test
	public void addOneItem() {
		builder.add(A);
		a.assertComponents(A);
	}
	
	@Test
	public void addTwice() {
		builder.add(A).add(B);
		a.assertComponents(A, B);
	}
	
	@Test
	public void addMultiple() {
		builder.add(A, B, C);
		a.assertComponents(A, B, C);
	}
	
	@Test
	public void complex() {
		builder.gap(3).vAlignOnBaseline().alignLeft().pad(7).add(A, C);
		a.assertComponents(A, C);
		a.assertFlowLayoutGaps(3, 3);
		a.assertFlowLayoutAlignment(FlowLayout.LEFT);
		a.assertFlowLayoutVAlignedOnBaseline(true);
		a.assertPadding(7, 7, 7, 7);
	}
	
}
