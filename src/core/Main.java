package core;

import java.awt.Graphics2D;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;

@ScriptManifest(author = "beeryboy", info = "chops trees duh", name = "SimpleCut", version = 0, logo = "")
public class Main extends Script {
	
	 private Gui gui = new Gui();
	 private Location tree;
	
	@Override
	public void onStart() {
		try {
			SwingUtilities.invokeAndWait(() -> {
				gui = new Gui();
				gui.open();
	        });
		} catch (InterruptedException | InvocationTargetException e) {
			e.printStackTrace();
			stop();
			return;
		}
	        
	    // If the user closed the dialog and didn't click the Start button
	    if (!gui.isStarted()) 
	    {
	        stop();
	        return;
	    }
	        
	    tree = gui.getSelectedTree();
	}

	public int onLoop() throws InterruptedException {
		
		boolean banking = bankLogs(tree.getBank());
		walkBack(banking, tree.getLocation());
		cutLogs(tree.toString(), banking);
		
		return random(200, 300);
	}

	@Override
	public void onExit() {
		log("exiting");
	}

	@Override
	public void onPaint(Graphics2D g) {
		
	}
	
	public boolean checkInventory()
	{
		if(getInventory().contains(tree.toString() + " Logs") && getInventory().isFull())
		{
			return true;
		}
		return false;	
	}
	
	public void bankItems()
	{
		NPC banker = getNpcs().closest("Banker");
		if(!myPlayer().isMoving())
		{
			banker.interact("Bank");
			sleeper(1000);
		}
		bank.deposit(tree.toString() + " logs", Bank.STORE_ALL);
	}
	
	public boolean bankLogs(boolean bank)
	{
		if(checkInventory())
		{
			if(bank)
			{
				getWalking().webWalk(tree.getBankLocation());
				bankItems();
				return true;
			} else {
				dropLogs();
			}
		}
		return false;
	}
	
	public void cutLogs(String treeType, boolean ifBanking)
	{
		RS2Object tree = getObjects().closest(treeType);
		if (tree != null); 
		{
			if(!ifBanking)
			{
				if(!myPlayer().isAnimating() && !myPlayer().isMoving())
				{
					tree.interact("Chop down");
					sleeper(1000);
				}
			}
		}
	}
	
	public void walkBack(boolean ifBanking, Area treeGrid)
	{
		if(!treeGrid.contains(myPosition()) && !ifBanking)
		{
			getWalking().walk(treeGrid);
		}
	}
	
	public void dropLogs()
	{
		getInventory().dropAll(tree.toString() + " logs");
	}
	
	public void sleeper(int ms)
	{
		try {
			sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}


