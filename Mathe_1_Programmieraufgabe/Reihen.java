/**
 * Aufgabe 4 Berechnung von Reihen
 * 
 * @author Viet Bartholomäus Hikari
 * @version 22.06.2019
 */
public class Reihen
{   
    /**
     * Constructor for objects of class Reihen
     */
    public Reihen()
    {        
           
    }
   
    /**
     * a.)
     */
    public double ReiheA(int n){
        double ergebnis = 0;
        for(double k = 1; k <=n; k++)
        {
            ergebnis= ergebnis + (1/k);
        }
        return ergebnis;
    }
    
    /**
     * b.)
     */
     public double ReiheB(int n)
    {
        double ergebnis = 0;
        for (double k = 1; k <= n; k++)
        {
            ergebnis = ergebnis + Math.pow(-1,(k-1)) * (1/k);
        }
        return ergebnis;
    }
    
    /**
     * c.)
     */
     public double ReiheC(int n)
    {
        double ergebnis = 0;
        for (double k = 1; k <= n; k++)
        {
            ergebnis = ergebnis + 1/(Math.pow(k,2));
        }
        
        return ergebnis;
    }
    
        
    /**
     * d.)
     */
    public double ReiheD(int n)
    {
        double ergebnis = 0;
        for (double k = 0; k <= n; k++)
        {
            ergebnis = ergebnis + 1/FakultätVon(k);
        }
        return ergebnis;
    }
    //gehört zu Aufgabe d
    private double FakultätVon (double k)
    {
        return k <= 1? 1 : k*FakultätVon(k-1);
    }
    
    /**
     * Test Methode für Aufgabe a
     */
    public void TestA() {
        System.out.println("Aufgabe a\nn-te Iteration  ");
        System.out.println("      1: " + ReiheA(1));
        System.out.println("     50: " + ReiheA(50));
        System.out.println("    100: " + ReiheA(100)); 
        System.out.println("    200: " + ReiheA(200));
        System.out.println("    500: " + ReiheA(500));
        System.out.println("   1000: " + ReiheA(1000));
        System.out.println("  10000: " + ReiheA(10000));
        System.out.println("1000000: " + ReiheA(1000000));
    }
    
    /**
     * Test Methode für Aufgabe b
     */
    public void TestB() {
        System.out.println("Aufgabe b\nn-te Iteration  0.69314718");
        System.out.println("      1: " + ReiheB(1));
        System.out.println("     50: " + ReiheB(50));
        System.out.println("    100: " + ReiheB(100)); 
        System.out.println("    200: " + ReiheB(200));
        System.out.println("    500: " + ReiheB(500));
        System.out.println("   1000: " + ReiheB(1000));
        System.out.println("  10000: " + ReiheB(10000));
        System.out.println("1000000: " + ReiheB(1000000));
    }
    
    /**
     * Test Methode für Aufgabe c
     */
    public void TestC(){
        System.out.println("Aufgabe c\nn-te Iteration  1.644934067");        
        System.out.println("      1: " + ReiheC(1));
        System.out.println("     50: " + ReiheC(50));
        System.out.println("    100: " + ReiheC(100)); 
        System.out.println("    200: " + ReiheC(200));
        System.out.println("    500: " + ReiheC(500));
        System.out.println("   1000: " + ReiheC(1000));
        System.out.println("  10000: " + ReiheC(10000));
        System.out.println("1000000: " + ReiheC(1000000));
    }
  
    /**
     * Test Methode für Aufgabe d
     */
    public void TestD(){
        System.out.println("Aufgabe 4\nn-te Iteration 2.718281828");
        System.out.println("     1: " + ReiheD(1));
        System.out.println("    10: " + ReiheD(10));
        System.out.println("    25: " + ReiheD(25)); 
        System.out.println("    50: " + ReiheD(50));     
    }
    
   
}
