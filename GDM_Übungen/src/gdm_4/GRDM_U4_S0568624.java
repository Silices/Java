package gdm_4;
import ij.*;
import ij.io.*;
import ij.process.*;
import ij.gui.*;
import ij.plugin.filter.*;


public class GRDM_U4_S0568624 implements PlugInFilter {

	protected ImagePlus imp;
	final static String[] choices = {"Wischen", "Weiche Blende", "‹berlagerung", "Schieb-Blende", "Chroma Key", "Extra"};

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_RGB+STACK_REQUIRED;
	}
	
	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen 
		ij.exitWhenQuitting(true);
		
		IJ.open("S:\\Eclipse\\GDM_‹bungen\\src\\gdm_4\\StackB.zip");
		
		GRDM_U4_S0568624 sd = new GRDM_U4_S0568624();
		sd.imp = IJ.getImage();
		ImageProcessor B_ip = sd.imp.getProcessor();
		sd.run(B_ip);
	}

	public void run(ImageProcessor B_ip) {
		// Film B wird uebergeben
		ImageStack stack_B = imp.getStack();
		
		int length = stack_B.getSize();
		int width  = B_ip.getWidth();
		int height = B_ip.getHeight();
		
		// ermoeglicht das Laden eines Bildes / Films
		Opener o = new Opener();
		OpenDialog od_A = new OpenDialog("Ausw√§hlen des 2. Filmes ...",  "");
				
		// Film A wird dazugeladen
		String dateiA = od_A.getFileName();
		if (dateiA == null) return; // Abbruch
		String pfadA = od_A.getDirectory();
		ImagePlus A = o.openImage(pfadA,dateiA);
		if (A == null) return; // Abbruch

		ImageProcessor A_ip = A.getProcessor();
		ImageStack stack_A  = A.getStack();

		if (A_ip.getWidth() != width || A_ip.getHeight() != height)
		{
			IJ.showMessage("Fehler", "Bildgr√∂√üen passen nicht zusammen");
			return;
		}
		
		// Neuen Film (Stack) "Erg" mit der kleineren Laenge von beiden erzeugen
		length = Math.min(length,stack_A.getSize());

		ImagePlus Erg = NewImage.createRGBImage("Ergebnis", width, height, length, NewImage.FILL_BLACK);
		ImageStack stack_Erg  = Erg.getStack();

		// Dialog fuer Auswahl des Ueberlagerungsmodus
		GenericDialog gd = new GenericDialog("√úberlagerung");
		gd.addChoice("Methode",choices,"");
		gd.showDialog();

		int methode = 0;		
		String s = gd.getNextChoice();
		if (s.equals("Wischen")) methode = 1;
		if (s.equals("Weiche Blende")) methode = 2;
		if (s.equals("‹berlagerung")) methode = 3;
		if (s.equals("Schieb-Blende")) methode = 4;
		if (s.equals("Chroma Key")) methode = 5;
		if (s.equals("Extra")) methode = 6;

		// Arrays fuer die einzelnen Bilder
		int[] pixels_B;
		int[] pixels_A;
		int[] pixels_Erg;

		int r;
		int g;
		int b;
		
		
		// Schleife ueber alle Bilder
		for (int z=1; z<=length; z++)
		{
			pixels_B   = (int[]) stack_B.getPixels(z);
			pixels_A   = (int[]) stack_A.getPixels(z);
			pixels_Erg = (int[]) stack_Erg.getPixels(z);

			int pos = 0;
			int zX = (int) ((z-1)*(double)width/(length-1));
            int zY = (int) ((z-1)*(double)height/(length-1));
            
            
			for (int y=0; y<height; y++)
				for (int x=0; x<width; x++, pos++)
				{
					int cA = pixels_A[pos];
					int rA = (cA & 0xff0000) >> 16;
					int gA = (cA & 0x00ff00) >> 8;
					int bA = (cA & 0x0000ff);

					int cB = pixels_B[pos];
					int rB = (cB & 0xff0000) >> 16;
					int gB = (cB & 0x00ff00) >> 8;
					int bB = (cB & 0x0000ff);

					if (methode == 1)
					{
						pixels_Erg[pos] = (y+1 > zY)? pixels_B[pos]: pixels_A[pos];
					}

					double trans = (z-1)* 255d / (length-1); 
		            double dif = 255d-trans;
		            
					if (methode == 2)
					{

					r = (int) ((trans*rA + dif*rB ) / 255d);
	                b = (int) ((trans*bA + dif*bB ) / 255d);
	                g = (int) ((trans*gA + dif*gB ) / 255d);

					pixels_Erg[pos] = 0xFF000000 + ((r & 0xff) << 16) + ((g & 0xff) << 8) + ( b & 0xff);
					}
					
					if (methode == 3)
					{
						
						r = rB <= 128? rA*rB/128: 255- ((255-rA)* (255-rB)/128);
						g = gB <= 128 ? gA*gB/128: 255-((255-gA)*(255-gB)/128);
                        b = bB <= 128 ? bA*bB/128: 255-((255-bA)*(255-bB)/128);
						
						pixels_Erg[pos] = 0xFF000000 + ((r & 0xff) << 16) + ((g & 0xff) << 8) + ( b & 0xff);
						
					}
		             
			
		            if(methode == 4){
                        if (x+1 > zX){
                             
                            int a = pos - zX;
                            a = a > pixels_B.length-1 ?pixels_B.length-1 : a < 0 ? 0 : a;
                            pixels_Erg[pos] = pixels_B[a];
                        }
                             
                        else{
                            int a = y*width-zX+x;
                            a = a > pixels_A.length-1 ?pixels_A.length-1 : a < 0 ? 0 : a;
                            pixels_Erg[pos] = pixels_A[a];
                        }
                    }
		            
		         
		            int rH = 218;
		            int gH = 158;
		            int bH = 68;
		            
		            if(methode == 5){
                        
                        double distance = Math.sqrt((rH - rA) * (rH - rA) +(gH - gA) * (gH - gA) + (bH - bA) * (bH - bA) );
 
                        pixels_Erg[pos] = distance < 100 ?pixels_B[pos] : pixels_A[pos];
                         
                    }
		            
		            if(methode == 6){
		            	pixels_Erg[pos] = y+1 > zY || x+1 > zX || width-x > zX || height-y > zY?pixels_B[pos]: pixels_A[pos]; 
                    }
					
					
				}
		}

		// neues Bild anzeigen
		Erg.show();
		Erg.updateAndDraw();

	}

}
