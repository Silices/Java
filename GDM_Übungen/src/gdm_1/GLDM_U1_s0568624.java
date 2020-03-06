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

		for (int i=0; i<width; i++) {
		for (int x=0; x<width; x++) {
			if(x%3==0) {r++;}
			
			
			
		for (int y=0; y<height; y++) {
		int pos = y*width + x; // Arrayposition bestimmen
						
			// Werte zurueckschreiben
			pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
		}}
			
			
		for (int y=0; y<height; y++) {

			if(y%3==0) {b++;}
			for (int x1=0; x1<width; x1++) {
			
		int pos = y*width + x1; // Arrayposition bestimmen
						
			// Werte zurueckschreiben
			pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
		}}
		}
	}
	private void generateTschechicheFahne(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 255;
				int g = 215;
				int b = 0;
				
				// Werte zurueckschreiben
				pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
			}
		}
	}
	private void generateBangladeschischeFahne(int width, int height, int[] pixels) {
		// Schleife ueber die y-Werte
		for (int y=0; y<height; y++) {
			// Schleife ueber die x-Werte
			for (int x=0; x<width; x++) {
				int pos = y*width + x; // Arrayposition bestimmen
				
				int r = 255;
				int g = 215;
				int b = 0;
				
				// Werte zurueckschreiben
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

