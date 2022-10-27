
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import java.io.ObjectInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XTankUI
{
	// The location and direction of the "tank"
	private int x = 300;
	//private int y = 500;
	private ArrayList<Tank> tanks;
	private ArrayList<Bullet> shots;
	//private int directionX = 0;
	//private int directionY = -10;

	private Canvas canvas;
	private Display display;
	
	ObjectInputStream in; 
	DataOutputStream out;
	
	public XTankUI(ObjectInputStream in, DataOutputStream out)
	{
		this.in = in;
		this.out = out;
		//tanks = new ArrayList<>();
		//shots = new ArrayList<>();
	}
	
	public void start()
	{
		display = new Display();
		Shell shell = new Shell(display);
		shell.setText("xtank");
		shell.setLayout(new FillLayout());

		canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		
		/*
		event -> {
		event.gc.fillRectangle(canvas.getBounds());
		event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_DARK_GREEN));
		event.gc.fillRectangle(x, y, 50, 100);
		event.gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		event.gc.fillOval(x, y+25, 50, 50);
		event.gc.setLineWidth(4);
		event.gc.drawLine(x+25, y+25, x+25, y-15);}*/
		
		canvas.addPaintListener(new CanvasPaintListener(shell, display, tanks, shots));	
		
		/*
		canvas.addMouseListener(new MouseListener() {
			public void mouseDown(MouseEvent e) {
				System.out.println("mouseDown in canvas");
			} 
			public void mouseUp(MouseEvent e) {} 
			public void mouseDoubleClick(MouseEvent e) {} 
		}); */

		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				//System.out.println("key " + e.character);
				
				// update tank location
				try {
					out.writeChar(e.character);
				}
				catch(IOException ex) {
					System.out.println("The server did not respond (write KL).");
				}

				//canvas.redraw();
			}
			public void keyReleased(KeyEvent e) {}
		});

		try {
			out.writeInt(1);
		}
		catch(IOException ex) {
			System.out.println("The server did not respond (initial write).");
		}				
		Runnable runnable = new Runner();
		display.asyncExec(runnable);
		//---- event loop
		shell.open();
		while( !shell.isDisposed())
			if(!display.readAndDispatch()){
				/*
				try {
					Thread.sleep(8);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} */
				//tankControl.moveBullets();
				//tankControl.regHits();
			}
		display.dispose();
	}
	
	class Runner implements Runnable
	{
		public void run() 
		{
			try {
				in.readInt();
				/*
					tanks = (ArrayList<Tank>) in.readObject();
					System.out.println("TANKS: "  + tanks);
			
					shots = (ArrayList<Bullet>) in.readObject();
					
					canvas.redraw();*/
			} /* | ClassNotFoundException ex*/
			catch(IOException e) {
				System.out.println("The server did not respond (async).");
			}				
            display.timerExec(150, this);
		}
	};	
}


