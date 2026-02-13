/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fattura_inversa;

/**
 *      double speseN = this.speseNonEsenti;         //soggette a IVA (spese anticipate dal professionista es. viaggio, alloggio ecc.)
        double speseE = this.speseEsenti;            //non soggette a IVA 
	double scarto = this.totaleFattura * 0.8;    //diffrenza tra totale desiderato e totale calcolato 
	double onorari = this.totaleFattura;         //compenso professionista
        double percSpeseG = this.percSpeseGenerali;  //percentuale spese generali (tra 10% - 15%)
        double totale = this.totaleFattura;          //totale fattura desiderato
        double ritenuta = 0, iva = 0, speseG = 0, cnap = 0, risultato = 0;
        //ritenuta -> 20% se c'e' p. IVA;   iva -> 22% su imponibile;  speseG -> spese generali= onorari x percentuale
        //cnap -> Contributo previdenziale(4%) ;  risultato -> totale fattura calcolato; 
        
 * @author User
 */
public class Fattura {
    private static final double ALIQUOTA_RITENUTA = 0.20;
    private static final double ALIQUOTA_IVA = 0.22;
    private static final double ALIQUOTA_CNAP = 0.04;
    
    private double onorari;
    private double speseGenerali;
    private final double percSpeseGenerali;
    private final double speseNonEsenti;
    private double cnap;
    private double imponibile;
    private double ritenutaAcconto;
    private double iva;
    private final double speseEsenti;    
    private final String selettore;
    private double totaleFattura;
    private final int nFattura;
    
    public Fattura(double totaleFattura, double speseNonEsenti, double speseEsenti, int percSpeseGenerali, 
            String selettore, int nFattura){
        this.totaleFattura = totaleFattura;
        this.speseNonEsenti = speseNonEsenti;
        this.speseEsenti = speseEsenti;
        this.percSpeseGenerali = percSpeseGenerali;
        this.selettore = selettore;
        this.nFattura = nFattura;
        this.regimeOrdinario();        
    }
    
    public Fattura(double totaleFattura, double speseNonEsenti, double speseEsenti, int percSpeseGenerali, int nFattura){
        this.totaleFattura = totaleFattura;
        this.speseNonEsenti = speseNonEsenti;
        this.speseEsenti = speseEsenti;
        this.percSpeseGenerali = percSpeseGenerali;
        this.selettore = "";
        this.nFattura = nFattura;
        this.regimeForfettario();        
    }
    
    private void regimeOrdinario(){
        double speseN = this.speseNonEsenti;         //soggette a IVA (spese anticipate dal professionista es. viaggio, alloggio ecc.)
        double speseE = this.speseEsenti;            //non soggette a IVA 
	double scarto = this.totaleFattura * 0.8;    //diffrenza tra totale desiderato e totale calcolato 
	double onorari = this.totaleFattura;         //compenso professionista
        double percSpeseG = this.percSpeseGenerali;  //percentuale spese generali (tra 10% - 15%)
        double totale = this.totaleFattura;          //totale fattura desiderato
        double ritenuta = 0, iva = 0, speseG = 0, cnap = 0, risultato = 0;
        //ritenuta -> 20% se c'e' p. IVA;   iva -> 22% su imponibile;  speseG -> spese generali= onorari x percentuale
        //cnap -> Contributo previdenziale(4%) ;  risultato -> totale fattura calcolato; 
        
        
        while(Math.abs(scarto) > 1e-7){
            if(this.selettore.length() == 11){
                ritenuta = (onorari + speseG + speseN) * Fattura.ALIQUOTA_RITENUTA;
            }
            speseG = onorari * percSpeseG / 100;
            cnap = (onorari + speseG + speseN) * Fattura.ALIQUOTA_CNAP;
            iva = (onorari + speseG + speseN + cnap) * Fattura.ALIQUOTA_IVA;
            risultato = onorari + speseG + speseN + cnap + iva + speseE - ritenuta;
            scarto = totale - risultato;
            onorari = onorari + scarto;	            
        }
        this.onorari = onorari;
        this.speseGenerali = speseG;
        this.cnap = cnap;
        this.imponibile = onorari + speseG + speseN + cnap;
        this.ritenutaAcconto = ritenuta;
        this.iva = iva;
        this.totaleFattura = risultato;
    }
    
    private void regimeForfettario(){
        double speseN = this.speseNonEsenti;
        double speseE = this.speseEsenti;
	double scarto = this.totaleFattura * 0.8;
	double onorari = this.totaleFattura;
        double percSpeseG = this.percSpeseGenerali;
        double totale = this.totaleFattura;
        double speseG = 0, cnap = 0, risultato = 0;
        
        while(Math.abs(scarto) > 1e-7){            
            speseG = onorari * percSpeseG / 100;
            cnap = (onorari + speseG + speseN) * Fattura.ALIQUOTA_CNAP;
            risultato = onorari + speseG + speseN + cnap + speseE;
            scarto = totale - risultato;
            onorari = onorari + scarto;	            
        }
        this.onorari = onorari;
        this.speseGenerali = speseG;
        this.cnap = cnap;
        this.imponibile = onorari + speseG + speseN + cnap;
        this.totaleFattura = risultato;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("\n\n%27s%4d\n", "Fattura N.", this.nFattura));
        sb.append(String.format("\n%-27s%7.2f", "Onorari:", this.onorari));
        sb.append(String.format("\n%-27s%7.2f", "Spese Generali:", this.speseGenerali));
        sb.append("\nex art 13 (15% su Onorari)");
        if (this.speseNonEsenti != 0) {
            sb.append(String.format("\n%-27s%7.2f", "Spese non esenti:", this.speseNonEsenti));
        }
        sb.append(String.format("\n%-27s%7.2f", "C.N.A.P.:", this.cnap));
        sb.append("\n------------------------------------");
        sb.append(String.format("\n%-27s%7.2f", "Imponibile:", this.imponibile));
        if(this.iva != 0){
            sb.append(String.format("\n%-27s%7.2f", "I.V.A 22%:", this.iva));
        }
        if (this.speseEsenti != 0) {
            sb.append(String.format("\n%-27s%7.2f", "Spese esenti:", this.speseEsenti));
        }
        if (this.ritenutaAcconto != 0) {
            sb.append(String.format("\n%-27s%7.2f%s", "Ritenuta d'acconto:", this.ritenutaAcconto, "-"));
            sb.append("\n(20% su imponibile)");
        }
        sb.append("\n------------------------------------");
        sb.append(String.format("\n%-27s%7.2f\n\n\n", "Totale fattura:", this.totaleFattura));
        
        return sb.toString();
    }
}