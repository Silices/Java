package gdm_3;
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageCanvas;
import ij.gui.ImageWindow;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
     Opens an image window and adds a panel below the image
 */
public class GRDM_U3_S0568624  implements PlugIn {

	ImagePlus imp; // ImagePlus object
	private int[] origPixels;
	private int width;
	private int height;

	String[] items = {"Original", "Rot-Kanal", "Graustufen", "Negativ des Bildes", "Bin‰rbild", "Bin‰rbild mit horizontaler Fehlerdiffusion", "Sepia", "Sechs Farben"};


	public static void main(String args[]) {

		IJ.open("S:\\Eclipse\\GDM_‹bungen\\src\\gdm_3\\Bear.jpg");
		//IJ.open("Z:/Pictures/Beispielbilder/orchid.jpg");

		GRDM_U3_S0568624 pw = new GRDM_U3_S0568624();
		pw.imp = IJ.getImage();
		pw.run("");
	}

	public void run(String arg) {
		if (imp==null) 
			imp = WindowManager.getCurrentImage();
		if (imp==null) {
			return;
		}
		CustomCanvas cc = new CustomCanvas(imp);

		storePixelValues(imp.getProcessor());

		new CustomWindow(imp, cc);
	}


	private void storePixelValues(ImageProcessor ip) {
		width = ip.getWidth();
		height = ip.getHeight();

		origPixels = ((int []) ip.getPixels()).clone();
	}


	class CustomCanvas extends ImageCanvas {

		CustomCanvas(ImagePlus imp) {
			super(imp);
		}

	} // CustomCanvas inner class


	class CustomWindow extends ImageWindow implements ItemListener {

		private String method;
		
		CustomWindow(ImagePlus imp, ImageCanvas ic) {
			super(imp, ic);
			addPanel();
		}

		void addPanel() {
			//JPanel panel = new JPanel();
			Panel panel = new Panel();

			JComboBox cb = new JComboBox(items);
			panel.add(cb);
			cb.addItemListener(this);

			add(panel);
			pack();
		}

		public void itemStateChanged(ItemEvent evt) {

			// Get the affected item
			Object item = evt.getItem();

			if (evt.getStateChange() == ItemEvent.SELECTED) {
				System.out.println("Selected: " + item.toString());
				method = item.toString();
				changePixelValues(imp.getProcessor());
				imp.updateAndDraw();
			} 

		}


		private void changePixelValues(ImageProcessor ip) {

			// Array zum Zur√ºckschreiben der Pixelwerte
			int[] pixels = (int[])ip.getPixels();

			if (method.equals("Original")) {

				for (int y=0; y<height; y++) {
					for (int x=0; x<width; x++) {
						int pos = y*width + x;
						
						pixels[pos] = origPixels[pos];
					}
				}
			}
			
			if (method.equals("Rot-Kanal")) {

				for (int y=0; y<height; y++) {
					for (int x=0; x<width; x++) {
						int pos = y*width + x;
						int argb = origPixels[pos];  // Lesen der Originalwerte 

						int r = (argb >> 16) & 0xff;
						//int g = (argb >>  8) & 0xff;
						//int b =  argb        & 0xff;

						int rn = r;
						int gn = 0;
						int bn = 0;

						// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

						pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;
					}
				}
			}
			
			if (method.equals("Graustufen")) {

				for (int y=0; y<height; y++) {
					for (int x=0; x<width; x++) {
						int pos = y*width + x;
						int argb = origPixels[pos];  // Lesen der Originalwerte 

						int r = (argb >> 16) & 0xff;
						int g = (argb >> 8) & 0xff;
						int b = (argb) & 0xff;

						 int grau =  (int) (0.3 * r + 0.59 * g + 0.11 * b);


						// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

						pixels[pos] = (0xFF<<24) | (grau<<16) | (grau<<8) |grau;
					}
				}
			}
			
			if (method.equals("Negativ des Bildes")) {

				for (int y=0; y<height; y++) {
					for (int x=0; x<width; x++) {
						int pos = y*width + x;
						int argb = origPixels[pos];  // Lesen der Originalwerte 

						int r = (argb >> 16) & 0xff;
						int g = (argb >> 8) & 0xff;
						int b = (argb) & 0xff;
						
						
						int rn = 255-r;
						int rg = 255-g;
						int rb = 255-b;


						// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

						pixels[pos] = (0xFF<<24) | (rn<<16) | (rg<<8) |rb;
					}
				}
			}
		
		

		
		
		
		if (method.equals("Bin‰rbild")) {

			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					int pos = y*width + x;
					int argb = origPixels[pos];  // Lesen der Originalwerte 

					int r = (argb >> 16) & 0xff;
					int g = (argb >> 8) & 0xff;
					int b = (argb) & 0xff;
					
					
					   int bit =  (int) (0.3 * r + 0.59 * g + 0.11 * b);
				        // BIn‰rwert berechnen
				        int binarvalue;
				        if (bit <= 128)
				            binarvalue = 0;
				        else
				            binarvalue = 255;


					// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

					pixels[pos] = (0xFF<<24) | (binarvalue<<16) | (binarvalue<<8) |binarvalue;
				}
			}
		}
	
		if (method.equals("Bin‰rbild mit horizontaler Fehlerdiffusion")) {


			int Fehler = 0;
			int schwellenwert = 255/2;
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					int pos = y*width + x;
					int argb = origPixels[pos];  // Lesen der Originalwerte 

					int r = (argb >> 16) & 0xff;
					int g = (argb >> 8) & 0xff;
					int b = (argb) & 0xff;
					
					int rn = 255;
					int gn= 255;
					int bn = 255;
					
					if ((r+g+b+Fehler) < schwellenwert*3) {
						rn = 0; gn = 0; bn =0;

					} 
					
					Fehler = ((r+g+b+Fehler)-(rn+gn+bn));

					// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

					pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) |bn;
				}
				Fehler = 0;
			}
		}
		
		if (method.equals("Sepia")) {

			int sepiaDepth = 25;
            int sepiaIntensity=15;

            for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					int pos = y*width + x;
					int argb = origPixels[pos];  // Lesen der Originalwerte 

					int r = (argb >> 16) & 0xff;
					int g = (argb >> 8) & 0xff;
					int b = (argb) & 0xff;

				      int gry = (int) (0.3 * r + 0.59 * g + 0.11 * b);
				        r = gry;
				        g = gry;
				        b = gry;
				        r = r + (sepiaDepth * 2);
				        g = g + sepiaDepth;
				        b = b - sepiaIntensity;
				        if (r>255) r=255;
				        if (g>255) g=255;
				        if (b>255) b=255;
				        
				        if (r<0) r=0;
				        if (g<0) g=0;
				        if (b<0) b=0;
					// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

					pixels[pos] = (0xFF<<24) | (r<<16) | (g<<8) |b;
				}
			}
		}
		
		if (method.equals("Sechs Farben")) {
					RGB [] Colors = {
							new RGB(29,33,32),
							new RGB(72,71,68),
							new RGB(52,104,140),
							new RGB(116,101,90),
							new RGB(151,149,149),
							new RGB(206,208,208)};
						
						for (int y=0; y<height; y++) {
							for (int x=0; x<width; x++) {
								int pos = y*width + x;
								int argb = origPixels[pos];  // Lesen der Originalwerte 

								int r = (argb >> 16) & 0xff;
								int g = (argb >>  8) & 0xff;
								int b =  argb        & 0xff;
								
								double distance = 500;
								double newDistance;
								RGB closeColor = Colors[0];
								for(RGB E: Colors) {
									newDistance = Math.sqrt(Math.pow(r-E.getR(),2) + Math.pow(g-E.getG(),2) + Math.pow(b-E.getB(),2));
									if(newDistance<distance) {
										distance = newDistance;
										closeColor = E;
									}						
								}

								// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden

								pixels[pos] = (0xFF<<24) | (closeColor.getR()<<16) | (closeColor.getG()<<8) | closeColor.getB();
				}
			}
		}
	}
} // CustomWindow inner class
} 

