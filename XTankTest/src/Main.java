/*
* AUTHOR: Kevin Nisterenko
* FILE: LilLexi.java
* ASSIGNMENT: A2 - LilLexi
* COURSE: CSc 335; Fall 2022
* PURPOSE: This class sets up the application by initializing the
* appropriate objects in the static main class for LilLexi. Based
* on Claveau's version.
*
* There are no inputs for this specific file. 
*/

public class Main
{
	static private XTankModel currentModel = null;

	public static void main(String args[])
	{
		// Singleton so that we only have one Document 
		if (currentModel == null)
			currentModel = new XTankModel();
		
		// set up the UI and link it to the document
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