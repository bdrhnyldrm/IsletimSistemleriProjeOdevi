package Proje;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class readWrite extends Thread {
    int sayi = 0;
    String[] satir = new String[0];

    public readWrite(String path) {
        String dosyaAdi = path;

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            List<String> tempLines = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                tempLines.add(line);
            }
            satir = tempLines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kuyrugaEkle(int time) {
        int i = 0;
        while (i < satir.length) {
            String[] parts = satir[i].split(",\\s*");

            int gelisZamani = Integer.parseInt(parts[0]);
            int oncelik = Integer.parseInt(parts[1]);
            int process = Integer.parseInt(parts[2]);
            int hafiza = Integer.parseInt(parts[3]);
            int yazici = Integer.parseInt(parts[4]);
            int tarayici = Integer.parseInt(parts[5]);
            int modems = Integer.parseInt(parts[6]);
            int Driver = Integer.parseInt(parts[7]);


            if (time == gelisZamani) {
                if (oncelik == 0) {
                    Proses newProcess = new Proses("P" + sayi, gelisZamani, oncelik, process, sayi,
                            hafiza, yazici, tarayici, modems, Driver);
                    Runtime.runtimeProcesses.add(newProcess);

                    Runtime.pausedProcesses.add(newProcess);
                } else {
                    Proses newProcess = new Proses("P" + sayi, gelisZamani, oncelik, process, sayi,
                            hafiza, yazici, tarayici, modems, Driver);
                    Runtime.feedbackQueues.get(oncelik).add(newProcess);

                    Runtime.pausedProcesses.add(newProcess);
                }
                sayi++;
            }
            i++;
        }
    }
}
