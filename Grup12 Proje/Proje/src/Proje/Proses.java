package Proje;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder;

public class Proses extends Thread {

    public int hafiza;
    public int yazici;
    public int tarayici;
    public int modem;
    public int cdDrivers;
    public boolean isError = false;
    public int time;
    public String isim;
    public int sure;
    public int oncelik;
    boolean isStarted;
    int renk;
    int numara;

    int timeout = 0;
    boolean isPaused;
    

    
    



    public Proses(String isim, int time, int oncelik, int sure, int numara,int hafiza , int yazici, int tarayici , int modem , int cdDrivers) {
        this.isStarted = false;
        this.isPaused = true;
        this.isim = isim;
        this.numara = numara;
        this.renk = getRandomColor();
        this.sure = sure;
        this.oncelik = oncelik;
        this.time = time;
        
        this.hafiza = hafiza;
        this.yazici = yazici;
        this.tarayici = tarayici;
        this.modem = modem;
        this.cdDrivers = cdDrivers;
    }

    public boolean hataKontrol()
    {
    	if(isError) return true;
    	else return false;
    }
    public int ZamanAsimiKontrol() {
        if (isPaused) {
            timeout++;
            if (timeout > 20) {
                ProsesZamanAsımı();
                return 0;
            }
            return 1;
        } else {
            timeout = 1;
            return 2;
        }
    }
    public boolean hataProses() throws Exception
    {
    	if(this.oncelik!=0)
    	{
    		if(this.hafiza > 960)
    		{
                write(RenkAyar.rastgeleRenk(renk) + RenkAyar.ANSI_WHITE_BACKGROUND + ProsesYonetimi.yurutmeZamani
    		                + ".saniye" + " bellek asimi !! "
    		                + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
    		                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
    		                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
    		                + RenkAyar.ANSI_RESET);
    			 isError = true;
                ProsesiBitir();
    			return true;
    		}
    		else if(this.yazici > 2 || this.tarayici >1 || this.modem >1 || this.cdDrivers > 1)
    		{
                write(RenkAyar.rastgeleRenk(renk) + RenkAyar.ANSI_WHITE_BACKGROUND + ProsesYonetimi.yurutmeZamani
 		                + ".saniye " + "kaynak tuketimi asimi !! "
 		                + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
 		                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
 		                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
 		                + RenkAyar.ANSI_RESET);
    			 isError = true;
                ProsesiBitir();
    			return true;
    			 
    		}
    		else
    		{
    			isError =false;
                ProsesiBaslat();
    			return false;
    		}
    	}
    	else
    	{
    		isError = false;
            ProsesiBaslat();
    		return false;
    	}
    }
    public boolean ProsesiBaslat() throws Exception {

        if (isStarted ) {
            return ProsesiYurut();
        } else {
            isStarted = true;
        }

        write(RenkAyar.rastgeleRenk(renk) + ProsesYonetimi.yurutmeZamani + ".saniye"
                + " proses basladi     " + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
                + RenkAyar.ANSI_RESET);
        isPaused = false;

        if (sure == 0) {
            return true;
        } else {
            sure--;

            return false;
        }
    }

    public boolean ProsesiYurut() throws Exception {
        write(RenkAyar.rastgeleRenk(renk) + ProsesYonetimi.yurutmeZamani + ".saniye"
                + " proses yurutuluyor " + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
                + RenkAyar.ANSI_RESET);
        isPaused = false;
        if (sure == 0) {
            return true;
        } else {
            sure--;

            return false;
        }
    }

    public boolean ProsesiBeklet() throws Exception {
    	if(!isError)
    	{
            write(RenkAyar.rastgeleRenk(renk) + ProsesYonetimi.yurutmeZamani + ".saniye"
    	                + " proses askida      "
    	                + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
    	                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
    	                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
    	                + RenkAyar.ANSI_RESET);
    		
    	}
       
        if (sure == 0) {
            return true;
        } else {
            isPaused = true;
            return false;
        }
    }

    public boolean ProsesiBitir() throws Exception {
        write(RenkAyar.rastgeleRenk(renk) + ProsesYonetimi.yurutmeZamani + ".saniye"
                + " proses sonlandi    "
                + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
                + RenkAyar.ANSI_RESET);
        isPaused = false;
        if (sure == 0 || isError) {
            return true;
        } else {
            throw new Exception("SIKINTI VAR");
        }
    }

    public boolean ProsesZamanAsımı() {
        write(RenkAyar.rastgeleRenk(renk) + RenkAyar.ANSI_WHITE_BACKGROUND + ProsesYonetimi.yurutmeZamani
                + ".saniye" + " proses timeout "
                + "(id:" + isim + " oncelik:" + oncelik + " kalan sure:" + sure + "saniye)"
                + " Ozellik: hafiza=" + hafiza + ", yazici=" + yazici + ", tarayici=" + tarayici
                + ", modems=" + modem + ", CD Surucu=" + cdDrivers
                + RenkAyar.ANSI_RESET);
        if (sure == 0) {
            return true;
        } else {
            return false;
        }
    }


    public void dusukOncelik() {
        oncelik++;
    }

    int getRandomColor() {
        return numara % 7;
    }

    void write(String wString) {
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "echo", wString);
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = br.readLine()) != null)
                sb.append(line);
        } catch (Exception e) {
            System.out.println("Error occured writeWithPB e:" + e);
        }
        System.out.println(sb.toString().replace("\"", ""));
    }
    
    @Override
    public boolean equals(Object obj) {

        return this.isim == ((Proses) obj).isim;
    }


}