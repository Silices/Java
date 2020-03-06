package gdm_3;

public class RGB {
	private int r;
	private int g;
	private int b;

	public RGB(double r, double g, double b){
		this.setR((int)Math.round(r));
		this.setG((int)Math.round(g));
		this.setB((int)Math.round(b));
	}
	
	public RGB(int r, int g, int b){
		this.setR(r);
		this.setG(g);
		this.setB(b);
	}
	

	public int bereichBegrenzung(int x){
		if(x<0){
			x=0;
		}
		if(x>255){
			x=255;
		}
		
		return x;	
	}
	
	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = bereichBegrenzung(r);
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = bereichBegrenzung(g);
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = bereichBegrenzung(b);
	}
}
