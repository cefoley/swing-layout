package cfoley.swingLayout;

import javax.swing.*;

public class JComponentBuilderStub implements JComponentBuilder {
	
	private JComponent returnValue;

	public JComponentBuilderStub(JComponent returnValue) {
		this.returnValue = returnValue;
	}
	
	@Override
	public JComponent build() {
		return returnValue;
	}

}
