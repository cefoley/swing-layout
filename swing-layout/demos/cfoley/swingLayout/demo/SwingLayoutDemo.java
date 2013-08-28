package cfoley.swingLayout.demo;

import static cfoley.swingLayout.Layout.*;

import javax.swing.*;

import cfoley.swingLayout.*;

public class SwingLayoutDemo extends JFrame {
	
	public static void main(String[] args) {
		new SwingLayoutDemo().setVisible(true);
	}
	
	public SwingLayoutDemo() {
		super("Swing-layout demo");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUpLayout();
	}
	
	private void setUpLayout() {
		add(tabs().addTab(commonManagers(), "Common Layout Managers")
				.build());
	}
	
	private PanelBuilder<?> commonManagers() {
		return grid().gap(50).pad(10).cols(4)
				.add(borderLayout(), gridLayout());
	}
	
	private Object borderLayout() {
		return borders().gap(5)
				.north(new JButton("North"))
				.west(new JButton("West"))
				.center(new JButton("BorderLayout (Center)"))
				.east(new JButton("East"))
				.south(new JButton("South"));
	}

	private Object gridLayout() {
		return grid().rows(4).cols(6).add(
				"Grid", "", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
	}

}
