package gdm2;
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

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
     Opens an image window and adds a panel below the image
     
     y neu = (y alt -128)*contrast + 128 + h
     u neu = u alt *sat
     v neu = v alt*sat
     
     u neu = cos(phi)*u alt - sin (phi)*v alt
     v neu = sin (phi) * u alt + cos(phi)*v alt
     https://imi-gdm.github.io/uebungen/uebung2/uebung2.htm
*/

public class GRDM_U2_S0568624 implements PlugIn {

    ImagePlus imp; // ImagePlus object
	private int[] origPixels;
	private int width;
	private int height;
	
	
    public static void main(String args[]) {
		//new ImageJ();
    	IJ.open("S:/Eclipse/GDM_�bungen/src/gdm2/orchid.jpg");
    	//IJ.open("Z:/Pictures/Beispielbilder/orchid.jpg");

		RGB.transformationTest(100,99, 99);
    	
		GRDM_U2_S0568624 pw = new GRDM_U2_S0568624();
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
    
    
    class CustomWindow extends ImageWindow implements ChangeListener {
         
        private JSlider jSliderBrightness;
		private JSlider jSliderKontrast;
		private JSlider jSliderSaettigung;
		private JSlider jSliderHue;
		
		private double brightness = 0;
		private double Kontrast = 5;
		private double Saettigung = 1;
		private double Hue = 90;

		CustomWindow(ImagePlus imp, ImageCanvas ic) {
            super(imp, ic);
            addPanel(); 		
        }
    
        void addPanel() {
        	//JPanel panel = new JPanel();
        	Panel panel = new Panel();

            panel.setLayout(new GridLayout(4, 1));
            jSliderBrightness = makeTitledSilder("Helligkeit", -128, 128, 0);
            jSliderKontrast = makeTitledSilder("Kontrast", 0, 50, 25);
            jSliderSaettigung = makeTitledSilder("Saettigung", 0, 20, 10);
            jSliderHue = makeTitledSilder("Hue", 0, 360, 180);
            panel.add(jSliderBrightness);
            panel.add(jSliderKontrast);
            panel.add(jSliderSaettigung);
            panel.add(jSliderHue);
            
            add(panel);
            
            pack();
         }
      
        private JSlider makeTitledSilder(String string, int minVal, int maxVal, int val) {
		
        	JSlider slider = new JSlider(JSlider.HORIZONTAL, minVal, maxVal, val );
        	Dimension preferredSize = new Dimension(width, 50);
        	slider.setPreferredSize(preferredSize);
			TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(), 
					string, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
					new Font("Sans", Font.PLAIN, 11));
			slider.setBorder(tb);
			slider.setMajorTickSpacing((maxVal - minVal)/10 );
			slider.setPaintTicks(true);
			slider.addChangeListener(this);
			
			return slider;
		}
        
        private void setSliderTitle(JSlider slider, String str) {
			TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(),
				str, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
					new Font("Sans", Font.PLAIN, 11));
			slider.setBorder(tb);
		}

		public void stateChanged( ChangeEvent e ){
			JSlider slider = (JSlider)e.getSource();

			if (slider == jSliderBrightness) {
				brightness = slider.getValue();
				String str = "Helligkeit " + brightness; 
				setSliderTitle(jSliderBrightness, str); 
			}
			
			if (slider == jSliderKontrast) {
				Kontrast = slider.getValue();
				String str = "Kontrast " + Kontrast/5; 
				setSliderTitle(jSliderKontrast, str); 
			}
			
			if (slider == jSliderSaettigung) {
				Saettigung = slider.getValue();
				String str = "Saettigung " + Saettigung/4; 
				setSliderTitle(jSliderSaettigung, str); 
			}
			
			if (slider == jSliderHue) {
				Hue = slider.getValue();
				String str = "Hue " + Hue; 
				setSliderTitle(jSliderHue, str); 
			}
			
			changePixelValues(imp.getProcessor());
			
			imp.updateAndDraw();
		}
		
	
		private void changePixelValues(ImageProcessor ip) {
			
			// Array fuer den Zugriff auf die Pixelwerte
			int[] pixels = (int[])ip.getPixels();
			
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					int pos = y*width + x;
					int argb = origPixels[pos];  // Lesen der Originalwerte 
					
					int r = (argb >> 16) & 0xff;
					int g = (argb >>  8) & 0xff;
					int b =  argb        & 0xff;

					// anstelle dieser drei Zeilen später hier die Farbtransformation durchführen,
					// die Y Cb Cr -Werte verändern und dann wieder zurücktransformieren

					RGB rgb = new RGB(r,g,b);
					YUV yuv = rgb.transformToYUV();
					
					yuv = yuv.changeBrightness(brightness);
					yuv = yuv.changeKontrast(Kontrast/5);
					yuv = yuv.changeSaettigung(Saettigung/4);
					yuv = yuv.changeHue(Hue);
					
					// Hier muessen die neuen RGB-Werte wieder auf den Bereich von 0 bis 255 begrenzt werden
					
					RGB rn = yuv.transformToRGB();
					pixels[pos] = (0xFF<<24) | (rn.getR()<<16) | (rn.getG()<<8) | rn.getB();
				
					/**
				     Opens an image window and adds a panel below the image
				     
				     y neu = (y alt -128)*contrast + 128 + h
				     u neu = u alt *sat
				     v neu = v alt*sat
				     
				     u neu = cos(phi)*u alt - sin (phi)*v alt
				     v neu = sin (phi) * u alt + cos(phi)*v alt
				*/
				}
			}
		}
		
		
		
		
		
    } // CustomWindow inner class
} 

