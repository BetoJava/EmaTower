package scripts.display.gui;

import scripts.display.gui.groups.FurtiveTextGroup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;

public class WindowManager {

	private JFrame frame;
	private JPanel panel;

	public static final int SHORT_DELAY = 80;
	public static final int NORMAL_DELAY = 120;
	public static final int LONG_DELAY = 160;
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 700;

	protected static FurtiveTextGroup furtiveTextGroup = new FurtiveTextGroup();
	
	public WindowManager() {
		this.frame = new JFrame("EMA'ToWer");
		this.frame.setBounds(70, 70, 0, 0);
		this.frame.setLocationRelativeTo(null);

		try {
			this.frame.setIconImage(ImageIO.read(new File("./src/resources/img/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setSize(WIDTH, HEIGHT);
		this.frame.setLocationRelativeTo(null);
	}

	/**
	 * Ajoute un panel sur la fenêtre.
	 * @param panel
	 */
	public void addPanel(JPanel panel) {
		this.panel = panel;
		this.panel.setBackground(Color.BLACK);
		this.panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.panel.setFocusable(true);
		this.panel.requestFocusInWindow();
	}

	/**
	 * Ajoute un keyListener au panel principal de la fenêtre.
	 * @param listener
	 */
	public void addKeyListener(KeyListener listener) {
		this.panel.addKeyListener(listener);
	}

	/**
	 * Ajoute un mouseListener (clicks, déplacement, déplacement de roulette) au panel principal de la fenêtre.
	 * @param l1 MouseListener
	 * @param l2 MouseMotionListener
	 * @param l3 MouseWheelListener
	 */
	public void addMouseListener(MouseListener l1, MouseMotionListener l2, MouseWheelListener l3) {
		this.panel.addMouseListener(l1);
		this.panel.addMouseMotionListener(l2);
		this.panel.addMouseWheelListener(l3);
	}

	/**
	 * Crée la fenêtre et la rend visible.
	 */
	public void createWindow() {
		this.frame.setContentPane(panel);
		this.frame.pack();
		this.frame.setVisible(true);
	}

	public JPanel getPanel() {
		return panel;
	}

	public JFrame getFrame() { return frame; }

	/**
	 * @return le groupe des textes éphémères.
	 */
	public static FurtiveTextGroup getFurtiveTextGroup() {
		return WindowManager.furtiveTextGroup;
	}

	/**
	 * Nettoie les groupes de textes éphémères.
	 */
	public static void clearFurtiveTexts() {
		WindowManager.furtiveTextGroup.clearInfoText();
		WindowManager.furtiveTextGroup.clearDgText();
	}

}
