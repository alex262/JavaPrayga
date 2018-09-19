package main;

import java.io.IOException;

public class MainThread implements Runnable{

	private MainWindow mv;

    public MainThread(MainWindow mv){
        this.mv = mv;
    }
    @Override
	public void run() {
    	while (true) {
		
	    	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	    	if(MainWindow.flCreateBase)
	    	{
	    		MainWindow.LoadBaseTerrakot18();
	    	}
    	}
	}
}
