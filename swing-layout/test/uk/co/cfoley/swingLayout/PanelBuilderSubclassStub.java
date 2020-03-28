package uk.co.cfoley.swingLayout;

import javax.swing.*;

public class PanelBuilderSubclassStub extends PanelBuilder<PanelBuilderSubclassStub> {
	
	private final JComponent returnValue;

	protected PanelBuilderSubclassStub(JComponent returnValue, ComponentConverter converter) {
		super(converter);
		this.returnValue = returnValue;
	}

	@Override
	protected JComponent subclassBuild() {
		return returnValue;
	}

	@Override
	public PanelBuilderSubclassStub self() {
		return this;
	}

}
