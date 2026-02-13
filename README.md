# Fattura Inversa in Java

Implementazione didattica di un sistema per il calcolo automatico di una fattura a partire dal totale desiderato, sviluppato interamente in Java. Il progetto consente di ricostruire tutti i valori della fattura — onorari, spese, contributi, IVA e ritenuta — tramite un algoritmo iterativo preciso e affidabile, sia in regime ordinario che in regime forfettario.

## Funzionalità principali

- Calcolo della fattura partendo dal totale finale inserito dall’utente
- Supporto a due regimi fiscali:
  - Regime ordinario
  - Regime forfettario
- Gestione automatica di:
  - Spese generali (percentuale personalizzabile)
  - Spese non esenti IVA
  - Spese esenti IVA (art. 15 DPR 633/72)
  - Contributo previdenziale C.N.A.P. (4%)
  - IVA (22%)
  - Ritenuta d’acconto (20%) se presente P.IVA/CF cliente
- Algoritmo iterativo che corregge gli onorari fino a raggiungere esattamente il totale desiderato
- Output formattato in stile fattura professionale

## Struttura del progetto

### `Fattura.java` — Motore di calcolo della fattura
Classe centrale che implementa tutta la logica di ricostruzione della fattura: calcolo iterativo degli onorari, gestione delle spese generali, calcolo del C.N.A.P., dell’IVA, della ritenuta d’acconto, dell’imponibile e del totale fattura. Contiene due metodi distinti: `regimeOrdinario()` e `regimeForfettario()`.

### `Fattura_inversa.java` — Interfaccia testuale (Main)
Gestisce l’interazione con l’utente: scelta del regime fiscale, inserimento dei dati della fattura, creazione dell’istanza `Fattura` e stampa del risultato formattato.

## Esempio di utilizzo (Inserimento dati da tastiera)

Calcolo di una fattura a partire dal totale  
Regime ordinario(ord)  o regime forfettario(for)? ord  
Inserire il numero di fattura: 12  
Inserire la percentuale delle spese generali (MAX 15%): 15  
Inserire il totale delle spese non esenti iva: 50  
Inserire il totale delle spese esenti iva(ex art.15 DPR 633/72): 20  
Inserire la partita iva o il codice fiscale del cliente: 12345678901  
Inserire il totale: 1000  

## Esempio di output

                   Fattura N.  12

Onorari:                    713.45
Spese Generali:             107.02
ex art 13 (15% su Onorari)
Spese non esenti:            50.00
C.N.A.P.:                    34.57
------------------------------------
Imponibile:                 905.04
I.V.A 22%:                  199.11
Spese esenti:                20.00
Ritenuta d'acconto:         171.01-
(20% su imponibile)
------------------------------------
Totale fattura:            1000.00

## Note tecniche

- La ritenuta d’acconto viene applicata solo se il codice cliente contiene 11 caratteri (P.IVA).
- Le spese generali sono calcolate come percentuale sugli onorari.
- Il contributo previdenziale (C.N.A.P.) è pari al 4% dell’imponibile.
- L’IVA è calcolata al 22% sull’imponibile + C.N.A.P.
- L’algoritmo iterativo corregge progressivamente gli onorari fino a raggiungere il totale desiderato.
