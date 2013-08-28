package cfoley.swingLayout.demo;

import static cfoley.swingLayout.Layout.*;

import java.awt.*;
import java.awt.event.*;

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
				.add(borderLayout(), 
						gridLayout(),
						hBox(),
						vBox(),
						cardLayout(),
						flowLayout(),
						hSplit(),
						vSplit(),
						scroll(),
						tab());

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
	
	private Object hBox() {
		return horizontalBox()
				.add("H-Box")
				.addRigidArea(10, 0)
				.add(new JButton("button"));
	}

	private Object vBox() {
		return verticalBox()
				.add("Vertical Box")
				.addRigidArea(0, 30)
				.add(new JButton("button"))
				.addRigidArea(0, 30)
				.add(new JTextField("textField"))
				.addGlue();
	}
	
	private Object cardLayout() {
		final CardLayout layout = new CardLayout();
		final Container parent;
		
		JButton b1 = new JButton("Card Layout (click)");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		
		parent = card(layout).add(b1).add(b2).add(b3).build();
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.next(parent);
			}
		};
		
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		
		return parent;
	}
	
	private Object flowLayout() {
		return flow("Flow Layout", new JButton("Button"), new JTextField(20)).gap(5);
	}
	
	private Object hSplit() {
		return horizontalSplitPane(new JButton("Left side"), new JButton("Right side")).continuousLayoutOn();
	}

	private Object vSplit() {
		return verticalSplitPane(new JButton("Top"), new JButton("Bottom")).continuousLayoutOff();
	}
	
	private Object scroll() {
		Integer[] data = new Integer[100];
		for(int i = 0; i < data.length; i++) {
			data[i] = i;
		}
		return scroller(new JList<>(data)).vertical();
	}
	
	private Object tab() {
		return tabs()
				.addTab("Some tabs", "A")
				.addTab(borderLayout(), "B")
				.addTab(gridLayout(), "C");
	}

}
