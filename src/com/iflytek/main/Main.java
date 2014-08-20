package com.iflytek.main;

import com.iflytek.watch.Watch;

public class Main {

	//����Java 8�İ汾��Ҫ�������������ⱨCannot make a static reference to the non-static field myWatch
	static Watch myWatch = new Watch();
	public static void main(String[] args) {
		// java 8��ֱ����������������
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
