/*
* AUTHOR: Kevin Nisterenko
* FILE: Main.java
* ASSIGNMENT: A3 - XTank
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class sets up the application by initializing the
* appropriate objects in the static main class for XTank. Based
* on Claveau's version.
*
* There are no inputs for this specific file. 
*/

public class Main
{
	static private XTankModel currentModel = null;

	public static void main(String args[])
	{
		// Singleton so that we only have one model
		if (currentModel == null)
			currentModel = new XTankModel();
		
		// set up the UI and link it to the model
		XTankUI tankUI = new XTankUI();
		tankUI.setCurrentModel( currentModel );
		currentModel.setUI(tankUI);
		
		// set the control to the the UI
		XTankControl tankControl = new XTankControl( currentModel );
		tankUI.setController( tankControl );
		
		// start the application and its window
		tankUI.start();
	} 
}