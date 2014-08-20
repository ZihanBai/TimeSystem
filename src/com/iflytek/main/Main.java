package com.iflytek.main;

import com.iflytek.watch.Watch;

public class Main {

	//低于Java 8的版本需要这样申明，以免报Cannot make a static reference to the non-static field myWatch
	static Watch myWatch = new Watch();
	public static void main(String[] args) {
		// java 8可直接像下面这样申明
//		Watch myWatch = new Watch();
		myWatch.setVisible(true);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
						try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
					myWatch.update("HH:mm:ss");
				}
			}
		}).start();
		
	}

}
