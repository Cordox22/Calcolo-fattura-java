/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fattura_inversa;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Fattura_inversa {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String selettore = "", scelta;
        int n_fattura, perc;
        double totale, non_esenti, esenti;
        
        System.out.println("Calcolo di una fattura a partire dal totale");
        System.out.print("Regime ordinario(ord)  o regime forfettario(for)? ");
        scelta = input.nextLine();        
        
        System.out.print("Inserire il numero di fattura: ");
        n_fattura = Integer.parseInt(input.nextLine());
        
        System.out.print("Inserire la percentuale delle spese generali (MAX 15%): ");
        perc = Integer.parseInt(input.nextLine());
        if (perc > 15){
            perc = 15;
            System.out.print("\033[1A"); // sposta il cursore su
            System.out.println("Percentuale accettata: " + perc + "%");
        }
        System.out.print("Inserire il totale delle spese non esenti iva: ");
        non_esenti = Double.parseDouble(input.nextLine());
        System.out.print("Inserire il totale delle spese esenti iva(ex art.15 DPR 633/72): ");
        esenti = Double.parseDouble(input.nextLine());
        if(scelta.equals("ord")){
            System.out.print("Inserire la partita iva o il codice fiscale del cliente: ");
            selettore = input.nextLine();
        }
        System.out.print("Inserire il totale: ");
        totale = Double.parseDouble(input.nextLine());
        
        if(scelta.equals("ord")){
            Fattura Quartobravo = new Fattura(totale, non_esenti, esenti, perc, selettore, n_fattura);
            System.out.println(Quartobravo);
        }
        else if(scelta.equals("for")){        
            Fattura Quarto = new Fattura(totale, non_esenti, esenti, perc, n_fattura);
            System.out.println(Quarto);
        }
    }
}