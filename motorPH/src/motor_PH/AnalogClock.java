package motor_PH;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnalogClock extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int CLOCK_RADIUS = 120;
	private static final int HAND_LENGTH_HOUR = 50;
	private static final int HAND_LENGTH_MINUTE = 60;
	private static final int HAND_LENGTH_SECOND = 80;

	public AnalogClock() {
		setPreferredSize(new Dimension(CLOCK_RADIUS * 2, CLOCK_RADIUS * 2));
		setBackground(Color.WHITE);

		// Update the clock every second
		Timer timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
		});
		timer.start();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		// Set rendering hints for smooth graphics
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// Set clock origin to the center of the panel
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		// Draw clock outline
		Ellipse2D clockFace = new Ellipse2D.Double(centerX - CLOCK_RADIUS, centerY - CLOCK_RADIUS, CLOCK_RADIUS * 2,
				CLOCK_RADIUS * 2);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke(2f));
		g2d.draw(clockFace);

		// Draw hour markers
		for (int i = 1; i <= 12; i++) {
			double angle = Math.PI / 6 * i;
			int x1 = (int) (centerX + (CLOCK_RADIUS - 15) * Math.sin(angle));
			int y1 = (int) (centerY - (CLOCK_RADIUS - 15) * Math.cos(angle));
			int x2 = (int) (centerX + CLOCK_RADIUS * Math.sin(angle));
			int y2 = (int) (centerY - CLOCK_RADIUS * Math.cos(angle));
			g2d.drawLine(x1, y1, x2, y2);
		}

		// Get current time
		Date currentDate = new Date();
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
		SimpleDateFormat secondFormat = new SimpleDateFormat("ss");
		int hours = Integer.parseInt(hourFormat.format(currentDate));
		int minutes = Integer.parseInt(minuteFormat.format(currentDate));
		int seconds = Integer.parseInt(secondFormat.format(currentDate));

		// Draw hour hand
		double hourAngle = Math.PI / 6 * (hours % 12) + Math.PI / 360 * minutes;
		drawClockHand(g2d, hourAngle, HAND_LENGTH_HOUR, 6, Color.BLACK);

		// Draw minute hand
		double minuteAngle = Math.PI / 30 * minutes;
		drawClockHand(g2d, minuteAngle, HAND_LENGTH_MINUTE, 4, Color.BLACK);

		// Draw second hand
		double secondAngle = Math.PI / 30 * seconds;
		drawClockHand(g2d, secondAngle, HAND_LENGTH_SECOND, 2, Color.RED);

		// Draw center circle
		int circleRadius = 4;
		g2d.setColor(Color.BLACK);
		g2d.fillOval(centerX - circleRadius, centerY - circleRadius, circleRadius * 2, circleRadius * 2);

		g2d.dispose();
	}

	private void drawClockHand(Graphics2D g2d, double angle, int length, int width, Color color) {
		int centerX = getWidth() / 2;
		int centerY = getHeight() / 2;

		int x = (int) (centerX + length * Math.sin(angle));
		int y = (int) (centerY - length * Math.cos(angle));

		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.drawLine(centerX, centerY, x, y);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Analog Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2 * CLOCK_RADIUS, 2 * CLOCK_RADIUS);
		frame.setLocationRelativeTo(null);

		AnalogClock clock = new AnalogClock();
		frame.add(clock);
		frame.setVisible(true);
	}
}
