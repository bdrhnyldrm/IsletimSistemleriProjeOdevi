package Proje;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Runtime {
    public static Proses currentProcess;
    public static List<Proses> timeoutProcesses = new LinkedList<Proses>();

    public static ArrayList<Queue<Proses>> feedbackQueues;
    public static Queue<Proses> runtimeProcesses;
    public static List<Proses> pausedProcesses;




    public static void ProsesBeklet() throws Exception {
        currentProcess.dusukOncelik();
        currentProcess.ProsesiBeklet();
        pausedProcesses.add(currentProcess);
        feedbackQueues.get(currentProcess.oncelik).add(currentProcess);
    }

    public static void ProsesCalismaZamani() throws Exception {

        if (currentProcess == null) {
            runtimeProcesses.peek().ProsesiBaslat();
            currentProcess = runtimeProcesses.peek();

        } else if (currentProcess.sure < 1) {
            // end old process
            runtimeProcesses.remove().ProsesiBitir();

            if (runtimeProcesses.peek() != null) {
                runtimeProcesses.peek().ProsesiBaslat();
                currentProcess = runtimeProcesses.peek();
            } else {
                ProsesGeriBildirim();
            }

        } else {
            runtimeProcesses.peek().hataProses();
            currentProcess = runtimeProcesses.peek();
        }

    }

    public static boolean ProsesGeriBildirim() throws Exception {

        if (!feedbackQueues.get(1).isEmpty()) {

            if (currentProcess == null) {
                currentProcess = feedbackQueues.get(1).poll();
                currentProcess.hataProses();
            } else if (currentProcess.sure > 0 && currentProcess.oncelik != 0) {
                currentProcess.dusukOncelik();
                currentProcess.ProsesiBeklet();
                pausedProcesses.add(currentProcess);
                feedbackQueues.get(currentProcess.oncelik).add(currentProcess);

                currentProcess = feedbackQueues.get(1).poll();
                currentProcess.hataProses();
            } else {

                if (currentProcess.oncelik != 0) {
                    currentProcess.ProsesiBitir();
                }

                currentProcess = feedbackQueues.get(1).poll();
                currentProcess.hataProses();
            }

        } else if (!feedbackQueues.get(2).isEmpty()) {

            if (currentProcess.sure > 0 && currentProcess.oncelik != 0) {
                currentProcess.dusukOncelik();
                currentProcess.ProsesiBeklet();
                pausedProcesses.add(currentProcess);
                feedbackQueues.get(currentProcess.oncelik).add(currentProcess);

                currentProcess = feedbackQueues.get(2).poll();
                currentProcess.hataProses();
            } else {

                if (currentProcess.oncelik != 0) {
                    currentProcess.ProsesiBitir();
                }

                currentProcess = feedbackQueues.get(2).poll();
                currentProcess.hataProses();
            }

        } else if (!feedbackQueues.get(3).isEmpty()) {

            if (currentProcess.sure > 0 && currentProcess.oncelik != 0) {

                if (currentProcess.oncelik <= 2) {
                    currentProcess.dusukOncelik();
                }

                currentProcess.ProsesiBeklet();
                pausedProcesses.add(currentProcess);
                feedbackQueues.get(3).add(currentProcess);

                currentProcess = feedbackQueues.get(3).poll();
                currentProcess.hataProses();
            } else {

                if (currentProcess.oncelik != 0) {
                    currentProcess.ProsesiBitir();
                }

                currentProcess = feedbackQueues.get(3).poll();
                currentProcess.hataProses();
            }
        } else {

            return true;
        }
        return false;
    }
    public static void hataProsesKontrol()
    {
    	  if (!pausedProcesses.isEmpty()) {

              for (int i = 0; i < pausedProcesses.size(); i++) {

                  boolean control = pausedProcesses.get(i).hataKontrol();
                  	if(control)
                  	{
                          for (int j = 1; j <= 3; j++) {
                              if (feedbackQueues.get(j).contains(pausedProcesses.get(i))) {
                                  feedbackQueues.get(j).remove(pausedProcesses.get(i));
                              }
                          }
                          pausedProcesses.remove(i);
                          i--;
                          break;
                     

 
                  }
              }
    	  }

     
    }
    public static void zamanAsimiProsesKontrol() {
        if (!pausedProcesses.isEmpty()) {

            for (int i = 0; i < pausedProcesses.size(); i++) {

                int control = pausedProcesses.get(i).ZamanAsimiKontrol();

                switch (control) {
                    case 0:

                        for (int j = 1; j <= 3; j++) {
                            if (feedbackQueues.get(j).contains(pausedProcesses.get(i))) {
                                feedbackQueues.get(j).remove(pausedProcesses.get(i));
                            }
                        }
                        pausedProcesses.remove(i);
                        i--;
                        break;
                    case 1:

                        break;

                    case 2:
                        pausedProcesses.remove(i);
                        i--;
                        break;

                    default:
                        break;
                }
            }

        }
    }

}
