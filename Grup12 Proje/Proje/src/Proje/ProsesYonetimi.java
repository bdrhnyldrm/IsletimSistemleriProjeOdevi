package Proje;

public class ProsesYonetimi extends Thread {
    public static int yurutmeZamani;
    readWrite read;
    public String path;


    public ProsesYonetimi(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        read = new readWrite(path);

        while (true) {
            read.kuyrugaEkle(yurutmeZamani);
            Runtime.zamanAsimiProsesKontrol();
            Runtime.hataProsesKontrol();

            try {
                if (!ProsesKontrol()) {

                    if (Runtime.ProsesGeriBildirim()) {
                        if (Runtime.currentProcess != null) {

                            sonProses();
                        } else {
                            break;
                        }

                    }

                }
            } catch (Exception e1) {

                e1.printStackTrace();
            }

            try {
                Thread.sleep(1);
                yurutmeZamani++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void sonProses() throws Exception {
        if (Runtime.currentProcess.sure > 1) {
            Runtime.currentProcess.hataProses();
        } else {
            Runtime.currentProcess.ProsesiBitir();
            Runtime.currentProcess = null;
        }
    }

    boolean ProsesKontrol() throws Exception {

        if (!Runtime.runtimeProcesses.isEmpty()) { // Runtime process listesinde bir proses var

            if (Runtime.currentProcess == null) {

                Runtime.ProsesCalismaZamani();

            } else if (Runtime.currentProcess.oncelik == 0) { // calisan proses zaten 0 onceligine sahip


                Runtime.ProsesCalismaZamani();

            } else {
                Runtime.ProsesBeklet();
                Runtime.ProsesCalismaZamani();

            }

            return true;
        } else {
            return false;
        }

    }

   

}
