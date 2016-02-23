package FlowChart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FlowChartGenerator {
	
	private static final int BOXWIDTH = 400;
	private static final int BOXHEIGHT = 150;
	private static final int LINELENGTH = 50;
	private static final int SPACE = 15;
	private static final Font font = new Font("TimesRoman", Font.BOLD, 25);
	private static int x;
	private static int y;
	
	
	public static void main(String[] args)
	{		
		try {
			int height = 3000;
			int width = 2000;
			x = 0;
			y = 0;
			
		      // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
		      // into integer pixels
		      BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		      Graphics2D ig2 = bi.createGraphics();


		      ig2.setFont(font);
		      /* String message = "";
		      FontMetrics fontMetrics = ig2.getFontMetrics();
		      int stringWidth = fontMetrics.stringWidth(message);
		      int stringHeight = fontMetrics.getAscent();
		      ig2.setPaint(Color.black);
		      ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
		      */
		      CodeReader codeReader = new CodeReader("dom97_5b.txt");
		      String[] lines = codeReader.readLines();
		      drawStart();
		      for (int i = 0; i < lines.length; i++)
		    	  i = drawBox(lines, ig2, i);

		      ImageIO.write(bi, "PNG", new File("TestFlowChart.PNG"));
		      
		      
		    } catch (IOException ie) {
		      ie.printStackTrace();
		    }
	}
	
	private static void drawStart()
	{
		return;
	}
	
	private static void drawEnd()
	{
		return;
	}
	
	private static int drawBox(String[] lines, Graphics2D g, int index)
	{
		String line = lines[index];
		if (line.startsWith("%") || line.equals(""))
			return index;
		else if (line.indexOf("input")>=0)
			index = drawInput(lines, g, index) - 1;
		else if (line.indexOf("disp")>=0 || line.indexOf("print")>=0)
			drawOutput(lines, g, index);
		else if (line.indexOf("if")>=0)
			drawDecision();
		else if (line.indexOf("while")>=0 || line.indexOf("for")>=0)
			drawRepitition();
		else
			drawProcess(lines, g, index);
		return index;
	}
	
	private static void drawRepitition()
	{
		
	}
	
	private static void drawDecision()
	{
		
	}
	
	private static void drawProcess(String[] lines, Graphics2D g, int index)
	{
		String line = lines[index];
		String message = line;
		message = message.substring(0, message.length()-1);
		g.setPaint(Color.black);
		g.drawRect(x, y, BOXWIDTH, BOXHEIGHT);
		
		FontMetrics fontMetrics = g.getFontMetrics();
	    int stringWidth = fontMetrics.stringWidth(message);
	    int stringHeight = fontMetrics.getAscent();
	    if (stringWidth > 175) {
	    	
	    }
	    else
	    g.drawString(message, x + SPACE, y + (BOXHEIGHT/2) - stringHeight );
	    y += BOXHEIGHT + LINELENGTH;
	}
	
	private static int drawInput(String[] lines, Graphics2D g, int index)
	{
		String message = "";
		String line = lines[index];
		
		while (line.indexOf("input")>=0) {
			line = lines[index];
			message = message + getFirstWord(line)+ ",";
			line = lines[++index];
		}
		message = message.substring(0, message.length()-1);
		g.setPaint(Color.black);
		int[] xPoints = {x + (BOXWIDTH/4), x + BOXWIDTH, x + (3 * BOXWIDTH / 4), x};
		int[] yPoints = {y, y, y + BOXHEIGHT, y + BOXHEIGHT  };
		g.drawPolygon(xPoints, yPoints, 4);
		
		FontMetrics fontMetrics = g.getFontMetrics();
	    int stringWidth = fontMetrics.stringWidth(message);
	    int stringHeight = fontMetrics.getAscent();
	    
	    g.drawString("INPUT", x + (3*BOXWIDTH/8), y + stringHeight);
	    
	    g.drawString(message, x + (stringWidth / 4), y + (stringHeight*4) );//probably need to fix this
	    
	    y += BOXHEIGHT + LINELENGTH;
	    
	    return index;
			
	}
	private static void drawOutput(String[] lines, Graphics2D g, int index)
	{
		
	}
	
	private static String getFirstWord(String line)
	{
		String[] split = line.split(" ");
		return split[0];
	}
	
	private String removeComments(String line)
	{
		String[] split = line.split("%");
		return split[0];
	}
	
	private String[] SeparateMessage(String message, Graphics2D g)
	{
		String[] ans = new String[3];
		FontMetrics fontMetrics = g.getFontMetrics();
	    int stringWidth = fontMetrics.stringWidth(message);
	    String[] split = message.split(" ");
	    String temp1 = "";
	    String temp2 = "";
	    for (int i = 0; i < split.length ;i++)
	    
		return ans;
	}
	
}
