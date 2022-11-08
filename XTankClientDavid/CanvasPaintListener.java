/*
* AUTHOR: Kevin Nisterenko
* FILE: CanvasPaintListener.java
* ASSIGNMENT: A3 - XTank
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class is an implementation of the PaintListener 
* class, it is slightly modified from the one given by the instructor.
* The use of this class is to draw the XTank window, particularly the tanks and 
* bullets.
*
* There are no inputs for this specific file. 
*/
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * repaints the canvas
 * 
 */
public class CanvasPaintListener implements PaintListener 
{
    private Shell shell;
    private Display display;
    private ArrayList<Tank> tanks;
    private ArrayList<Bullet> shots;
    
    /*
	 * Constructor for the CanvasPaintListener, sets the attributes based
	 * on the shell and display. Also received the tank model
	 * 
	 * @param sh, Shell object representing the open GUI shell
	 * @param disp, Display object representing the current GUI display
	 * @param md, XTankModel object
	 */
    public CanvasPaintListener(Shell sh, Display disp, ArrayList<Tank> tks, ArrayList<Bullet> sts)
    {
    	shell = sh; display = disp; tanks = tks; shots = sts;
    }
    
    /*
	 * This method takes a paint event (redraw and update) to redraw the 
	 * board on the screen.
	 * 
	 * @param event, PaintEvent object representing an event from the system
	 */
	public void paintControl(PaintEvent event) {   
		
		event.gc.fillRectangle(shell.getBounds());
        for (Tank curr : tanks) {
        	event.gc.fillRectangle(shell.getBounds());
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
			event.gc.fillRectangle(curr.getXpos(), curr.getYpos(), 50, 100);
			event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
			event.gc.fillOval(curr.getXpos(), curr.getYpos()+25, 50, 50);
			event.gc.setLineWidth(4);
			event.gc.drawLine(curr.getXpos()+25, curr.getYpos()+25, curr.getXpos()+25, curr.getYpos()-15);
        }
        
        for (Bullet curr : shots) {
        	event.gc.drawOval(curr.getXpos()+10, curr.getYpos()+43, 10, 10);
        }
	}
}  