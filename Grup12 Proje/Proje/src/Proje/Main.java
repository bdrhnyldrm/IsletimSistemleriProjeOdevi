package Proje;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {



	public static void kuyruklariOlustur() {
		ProsesYonetimi.yurutmeZamani = 0;

		Runtime.feedbackQueues = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			Runtime.feedbackQueues.add(new LinkedList<>());
		}
		Runtime.runtimeProcesses = new LinkedList<>();
		Runtime.pausedProcesses = new LinkedList<>();

		System.out.println("Kuyruklar olusturuldu...");
	}

	public static void main(String[] args) throws InterruptedException {
		kuyruklariOlustur();
		Thread.sleep(1000);

		String path = (args.length != 0) ? args[0] : "./giris.txt";

		ProsesYonetimi controller = new ProsesYonetimi(path);
		controller.start();
	}

}
