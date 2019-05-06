package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */

		int bots = Integer.parseInt(JOptionPane.showInputDialog("How many robots?"));
		Color c;
		String input = JOptionPane.showInputDialog("Red, blue, or green shapes?");
		if (input.equals("red"))
			c = Color.RED;
		else if (input.equals("blue"))
			c = Color.BLUE;
		else
			c = Color.GREEN;
		int sides = Integer.parseInt(JOptionPane.showInputDialog("How many sides in the shapes?"));
		int sqrt = (int) Math.sqrt(bots);
		
		Thread[] threads = new Thread[bots];
		for (int i = 0; i < bots; i++) {
			int x = i;
			threads[i] = new Thread(() -> {
				Robot r = new Robot(((x / sqrt) * 200) + 200, ((x % sqrt) * 200) + 200);
				r.setSpeed(10);
				r.setPenColor(c);
				r.penDown();
				for (int j = 0; j < sides; j++) {
					r.move(200 / sides);
					r.turn(360 / sides);
				}
			});
		}
		for (Thread t : threads)
			t.start();
	}
}
