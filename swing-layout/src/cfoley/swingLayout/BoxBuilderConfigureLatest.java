package cfoley.swingLayout;

import java.awt.*;

import javax.swing.*;

public class BoxBuilderConfigureLatest implements JComponentBuilder {
	
	private BoxBuilder owner;
	private BoxedComponent recent;

	
	protected BoxBuilderConfigureLatest(BoxBuilder owner, BoxedComponent recent) {
		this.owner = owner;
		this.recent = recent;
	}
	
	public BoxBuilder vertical(){
		return owner.vertical();
	}
		
	public BoxBuilder horizontal() {
		return owner.horizontal();
	}
	
	public BoxBuilderConfigureLatest add(Object o) {
		return owner.add(o);
	}
	
	public BoxBuilderConfigureLatest alignment(double d) {
		return alignment((float)d);
	}

	public BoxBuilderConfigureLatest alignment(float d) {
		recent.setAlignment(d);
		return this;
	}

	public BoxBuilderConfigureLatest minSize(int x, int y) {
		return minSize(new Dimension(x, y));
	}

	public BoxBuilderConfigureLatest minSize(Dimension d) {
		recent.setMinSize(d);
		return this;
	}

	public BoxBuilderConfigureLatest maxSize(int x, int y) {
		return maxSize(new Dimension(x, y));
	}

	public BoxBuilderConfigureLatest maxSize(Dimension d) {
		recent.setMaxSize(d);
		return this;
	}

	public BoxBuilderConfigureLatest preferredSize(int x, int y) {
		return preferredSize(new Dimension(x, y));
	}

	public BoxBuilderConfigureLatest preferredSize(Dimension d) {
		recent.setPreferredSize(d);
		return this;
	}
	
	public BoxBuilder addGlue() {
		return owner.addGlue();
	}
	
	public BoxBuilder addRigidArea(int width, int height) {
		return owner.addRigidArea(width, height);
	}

	public BoxBuilder addRigidArea(Dimension d) {
		return owner.addRigidArea(d);
	}

	@Override
	public JComponent build() {
		return owner.build();
	}

}
