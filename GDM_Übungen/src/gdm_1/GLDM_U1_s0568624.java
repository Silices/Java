package gdm_1;

import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

public class GLDM_U1_s0568624 implements PlugIn {
	
	final static String[] choices = {
		"Schwarzes Bild",
		"Gelbes Bild",
		"Belgische Fahne",
		"Fahne der USA",
		"horizontalen Schwarz/Rot Verlaufs bei gleichzeitigem vertikalen Schwarz/Blau Verlauf",
		"tschechiche Fahne",
		"bangladeschische Fahne",
	};
	
	private String choice;
	
	public static void main(String args[]) {
		ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen 
		ij.exitWhenQuitting(true);
		
		GLDM_U1_s0568624 imageGeneration = new GLDM_U1_s0568624();
		imageGeneration.run("");
	}
	
	public void run(String arg) {
		
		int width  = 566;  // Breite
		int height = 400;  // Hoehe
		
		// RGB-Bild erzeugen
		ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
		ImageProcessor ip = imagePlus.getProcessor();
		
		// Arrays fuer den Zugriff auf die Pixelwerte
		int[] pixels = (int[])ip.getPixels();
		
		dialog();
		
		////////////////////////////////////////////////////////////////
		// Hier bitte Ihre Aenderungen / Erweiterungen
		
		if ( choice.equals("Schwarzes Bild") ) {
		generateBlackImage(width, height, pixels);}

		if ( choice.equals("Gelbes Bild") ) {
		generateYellowImage(width, height, pixels);}
		
		if ( choice.equals("Belgische Fahne") ) {
		generateBelgischeFahne(width, height, pixels);}

		if ( choice.equals("Fahne der USA") ) {
		generateUSAFahne(width, height, pixels);}

		if ( choice.equals("horizontalen Schwarz/Rot Verlaufs bei gleichzeitigem vertikalen Schwarz/Blau Verlauf") ) {
		generateFarbVerlauf(width, height, pixels);}

		if ( choice.equals("tschechiche Fahne") ) {
		generateTschechicheFahne(width, height, pixels);}

		if ( choice.equals("bangladeschische Fahne") ) {
		generateBangladeschischeFahne(width, height, pixels);}
		
		////////////////////////////////////////////////////////////////////
		
		// neues Bild anzeigen
		imagePlus.show();
		imagePlus.updateAndDraw();
	}

	private void generateBlackImage(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 0;
				int g = 0;
				int b = 0;
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}
	
	private void generateYellowImage(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 255;
				int g = 255;
				int b = 0;
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}
	
	private void generateBelgischeFahne(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<(width/3); x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 0;
				int g = 0;
				int b = 0;
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
			for (int x=(width/3); x<(2*(width/3)); x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 255;
				int g = 215;
				int b = 0;
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
			for (int x=(2*(width/3)); x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 255;
				int g = 0;
				int b = 0;
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}
	
	private void generateUSAFahne(int width, int height, int[] pixels) {
		
		double height13 = height/13d;
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 255;
				int g = 255;
				int b = 255;
				
				if (x < width/2 && y < height13*7)
				{
					r = 0; g = 0;
				}
				else if (y%(height13*2)< height13 )
				{
					g = 0;
					b = 0;
				}
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}
	private void generateFarbVerlauf(int width, int height, int[] pixels) {
		int r = 0;
		int g = 0;
		int b = 0;
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
				
				r= (int) 255*x/width;		//jeh groeßer x, desto heller wird das rot
				g = 0;
				b= (int) 255*y/height;     //geh groeßer y, desto heller wird das blau
			
			int pos = y*width + x; // Arrayposition bestimmen
						
			// Werte zurueckschreiben
			pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
		}
	}
}
	private void generateTschechicheFahne(int width, int height, int[] pixels) {
		int pxl=0;
		int r =0;
		int g=0;
		int b=0;
		int HalfHeight = height/2;
		
		for (int y=0; y<height; y++) {
			for (int x=0; x<width; x++) {
					int pos = y*width + x; // Arrayposition bestimmen
					
					if (y<HalfHeight) {
					r = 255;           //obere haelfte weiß	
					g = 255;
					b = 255;}
					else {
						r=255;			//rest rot 
						g=0;
						b=0;
					}
					if (x<=pxl)
					{
						r=0;
						g=0; 			//x = Anzahl der blauen pixel
						b=255;
					}
					// Werte zurueckschreiben
					pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
				}
				 if(y>=HalfHeight){
		                pxl--;}        // pxl steigt bis zur mitte des bildes, dann singt es wieder
				 else{pxl++;}		
	}
}
	private void generateBangladeschischeFahne(int width, int height, int[] pixels) {
        int r=0;
        int g=0;
        int b=0;
        int radius=150;
        
        int centerX=width/2;		//Mitte der x axe
        int centerY=height/2;    //Mitte der y axe
        
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                int pos = y*width + x; // Arrayposition bestimmen
                 
                int deltaY=Math.abs(centerY-y);		//distanz mitte zu pixel
                int deltaX=Math.abs(centerX-x); 	// same
                
                double d =Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));  //distantz pixel zu mitte
                 
                if(d<radius){
                    r=255;			// wenn c im radius  dann Farbe rot
                    g=0;
                    b=0;
                } 
                
                if(d>radius){
                    r=0;
                    g=130;			// wenn c ausserhalb der radius dann gr�n 
                    b=0;
                }
                 
                pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                 
            }
        }
    }
	
	private void dialog() {
		// Dialog fuer Auswahl der Bilderzeugung
		GenericDialog gd = new GenericDialog("Bildart");
		
		gd.addChoice("Bildtyp", choices, choices[0]);
		
		
		gd.showDialog();	// generiere Eingabefenster
		
		choice = gd.getNextChoice(); // Auswahl uebernehmen
		
		if (gd.wasCanceled())
			System.exit(0);
	}
}

