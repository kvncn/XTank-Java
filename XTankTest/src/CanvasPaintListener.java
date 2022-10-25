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
    private XTankModel currentModel;
    
    /*
	 * Constructor for the CanvasPaintListener, sets the attributes based
	 * on the shell and display. Also received the tank model
	 * 
	 * @param sh, Shell object representing the open GUI shell
	 * @param disp, Display object representing the current GUI display
	 * @param md, XTankModel object
	 */
    public CanvasPaintListener(Shell sh, Display disp, XTankModel md)
    {
    	shell = sh; display = disp; currentModel = md;
    }
    
    /*
	 * This method takes a paint event (redraw and update) to redraw the 
	 * board on the screen.
	 * 
	 * @param event, PaintEvent object representing an event from the system
	 */
	public void paintControl(PaintEvent e) 
	{   e.gc.fillRectangle(shell.getBounds());
        for (Tank curr : currentModel.getTanks()) {
    		e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
        	e.gc.fillRectangle(curr.getXpos(), curr.getYpos(), 50, 100);
    		e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    		e.gc.fillOval(curr.getXpos(), curr.getYpos()+25, 50, 50);
    		e.gc.setLineWidth(4);
    		e.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    		e.gc.drawText(curr.toString(), curr.getXpos()+7, curr.getYpos()+40);
        }
        
        for (Bullet curr : currentModel.getShots()) {
        	e.gc.drawOval(curr.getXpos()+10, curr.getYpos()+43, 10, 10);
        }
        	
	}
}  