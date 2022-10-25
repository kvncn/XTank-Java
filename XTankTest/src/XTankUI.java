/*
* AUTHOR: Kevin Nisterenko
* FILE: XTankUI.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class serves as the view (in an MVC idea) of the
* LilLexi application. Here is where the UI and shell are setup, as well
* as the listeners for any and all events that the user will interact with
* in the application (scrolling, commands, basic keyboard typing). It is
* a modified version of Claveau's version.
*
* There are no inputs for this specific file. 
*/

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;


/**
 * XTankUI
 * 
 */
public class XTankUI
{
	private XTankModel currentModel;
	private XTankControl tankControl;
	private Display display;
	private Shell shell;
	private Canvas canvas;
	
	/**
	 * Constructor for the UI of XTank, starts the basic layout and
	 * shell. 
	 */
	public XTankUI() 
	{
		//---- create the window and the shell
		Display.setAppName("XTank");
		display = new Display();  
		shell = new Shell(display);
	    shell.setText("XTank");
		shell.setSize(900,900);
		shell.setLayout(new GridLayout());	
	}
		
	/**
	 * Setups and starts the UI for XTank, has the basic listeners
	 */
	public void start() {	
		//---- create widgets for the interface
	    Composite upperComp = new Composite(shell, SWT.NO_FOCUS);
	    //Composite lowerComp = new Composite(shell, SWT.NO_FOCUS);
	    
	    //---- canvas for the document
		canvas = new Canvas(upperComp, SWT.DOUBLE_BUFFERED);
		canvas.setSize(900,900);
		
		// canvas paint listener, repaints canvas after redraw called
		canvas.addPaintListener(new CanvasPaintListener(shell, display, currentModel));	
		
		Tank p1 = new Tank(0, 450, 0, "P1");
		tankControl.addTank(p1);
		
		Tank p2 = new Tank(900, 450, 0, "P2");
		tankControl.addTank(p2);
		
        // This key Listener is used to get a character from the keyboard
        canvas.addKeyListener(new KeyAdapter()
		{	
			public void keyPressed(KeyEvent e)
			{
				// SHOOTS
				if (e.character == 'r') {
					tankControl.addShot(p2);
				}
				
				// MOVES TANK
				if (e.character == 'w') {
					tankControl.moveTank(p2, 0, -10, 2);
				}
				if (e.character == 's') {
					tankControl.moveTank(p2, 0, 10, 3);
				}
				if (e.character == 'a') {
					tankControl.moveTank(p2, -10, 0, 1);
				}
				if (e.character == 'd') {
					tankControl.moveTank(p2, 10, 0, 0);
				}
				
				
				// SHOOTS
				if (e.character == '/') {
					tankControl.addShot(p1);
				}
				
				// MOVES TANK
				if (e.keyCode == SWT.ARROW_UP) {
					tankControl.moveTank(p1, 0, -10, 2);
				}
				if (e.keyCode == SWT.ARROW_DOWN) {
					tankControl.moveTank(p1, 0, 10, 3);
				}
				if (e.keyCode == SWT.ARROW_LEFT) {
					tankControl.moveTank(p1, -10, 0, 1);
				}
				if (e.keyCode == SWT.ARROW_RIGHT) {
					tankControl.moveTank(p1, 10, 0, 0);
				}
			}
		});
        
        
		//---- event loop
		shell.open();
		while( !shell.isDisposed())
			if(!display.readAndDispatch()){
				try {
					Thread.sleep(8);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tankControl.moveBullets();
				tankControl.regHits();
			}
		display.dispose();
	} 

	/**
	 * Calls the canvas redraw method to update the UI to any changes
	 * made to the document or to it.
	 */
	public void updateUI() {
		//System.out.println("updateUI");
		canvas.redraw();
	}
	
	/**
	 * Sets the current XTankModel (Model) of the application.
	 * 
	 * @param md, XTankModel object representing current model 
	 * of the game
	 */
	public void setCurrentModel(XTankModel md) { currentModel = md; }
	
	/**
	 * Sets the current XTankControl (Controller) of the application.
	 * 
	 * @param tc, XTankControl object representing current controller 
	 * of the game
	 */
	public void setController(XTankControl tc) { tankControl = tc; }

}