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
	 * @param disp, Display object represenitng the current GUI display
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
	{
		Rectangle rect = shell.getClientArea();
		e.gc.setBackground(display.getSystemColor(SWT.COLOR_WHITE)); 
        e.gc.fillRectangle(rect.x, rect.y, rect.width, rect.height);
        e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLUE));
        
        for (Tank curr : currentModel.getTanks()) {
        	 e.gc.drawText(curr.toString(), curr.getXpos(), curr.getYpos());
        }
        
        for (Bullet curr : currentModel.getShots()) {
        	e.gc.drawText("*", curr.getXpos(), curr.getYpos());
        }
        	
	}
}  