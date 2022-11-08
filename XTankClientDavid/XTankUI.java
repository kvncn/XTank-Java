
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private char curr;

	private Canvas canvas;
	private Display display;
	
	ObjectInputStream in; 
	DataOutputStream out;
	
	public XTankUI(ObjectInputStream in, DataOutputStream out)
	{
		this.in = in;
		this.out = out;
		tanks = new ArrayList<>();
		shots = new ArrayList<>();
	}
	
	public void start()
	{
		display = new Display();
		Shell shell = new Shell(display);
		shell.setText("xtank");
		shell.setLayout(new FillLayout());

		canvas = new Canvas(shell, SWT.NO_BACKGROUND);
		Runnable runnable = new Runner();
		display.asyncExec(runnable);
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
		

		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				System.out.println("key " + e.character);
				curr = e.character;
				
				// update tank location
				/*
				try {
					out.writeChar(e.character);
				}
				catch(IOException ex) {
					System.out.println("The server did not respond (write KL).");
				}*/

				canvas.redraw();
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		try {
			out.writeInt(1);
		}
		catch(IOException ex) {
			System.out.println("The server did not respond (initial write).");
		}	
		
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
				out.writeChar(curr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				tanks = (ArrayList<Tank>) in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("TANKS: "  + tanks);

			try {
				shots = (ArrayList<Bullet>) in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			canvas.redraw();				
            display.timerExec(150, this);
		}
	};	
}


