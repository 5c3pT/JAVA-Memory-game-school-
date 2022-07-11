package fxmemory;
/**
 *
 * @author paul
 */
public class Gelijkregel {
private int kaartenomgedraaid, kaart1nr, kaart2nr, kaart3nr, 
gelijkaantal, aantalfout;    
private Generator generatie;
private String kaart1, kaart2;
private Boolean gelijk;

/**
 * Houd het aantal omgedraaide kaarten bij
 * @param knopnr
 * @return 
 */
public int kaartOmgedraaid( int knopnr ) {
        //Het bijtellen van het aantal omgedraaide kaarten
        kaartenomgedraaid++;
        
        //Het toewijzen van de kaartnummers aan de hand van het geklikte 
        //knop nummer
        if (kaartenomgedraaid == 1){
            kaart1nr = knopnr;
        }
        if (kaartenomgedraaid == 2){
            kaart2nr = knopnr;
        }
        if (kaartenomgedraaid == 3){
            kaart3nr = knopnr;
        }
        
        return kaartenomgedraaid;
    }

/** 
 * Reset het aantal omgedraaide kaarten en zorgt ervoor dat de laatst 
 * omgedraaide kaart, kaart nummer 1 wordt
 * @param knopnr 
 */
    public void resetOmgedraaid( int knopnr ) {
        kaartenomgedraaid = 1;
        kaart1nr = knopnr;
    }

/**
 * vergelijkt de twee omgedraaide kaarten en houd daarbij ook het aantal gelijk
 * omgedraaide kaarten bij en het aantal fouten dat is gemaakt
 * @param generatie
 * @return 
 */    
    public Boolean vergelijkfout( Generator generatie ){
        //De fruitsoorten van beide omgedraaide kaarten opvragen
        kaart1 = generatie.getFruit( kaart1nr );
        kaart2 = generatie.getFruit( kaart2nr );

        //Het vergelijken van de twee fruitsoorten en het bijstellen van het
        //gelijkaantal en het aantal fouten
        if ( kaart1 == kaart2 ){
            gelijk = true;
            gelijkaantal += 2;
        }
        else {
            gelijk = false;
            aantalfout += 1;
        }
        
        return gelijk;
    }

/**
 * Vergelijkt de twee omgedraaide kaarten
 * @param generatie
 * @return 
 */    
    public Boolean vergelijk( Generator generatie ){   
        kaart1 = generatie.getFruit( kaart1nr );
        kaart2 = generatie.getFruit( kaart2nr );

        if ( kaart1 == kaart2 ){
            gelijk = true;
        }
        else {
            gelijk = false;
        }
        
        return gelijk;
    }   

/**
 * Geeft de waarde van kaart nummer 1 terug
 * @return 
 */
    public int getKaart1nummer( ){
        return kaart1nr;
    }

/**
 * Geeft de waarde van kaart nummer 2 terug
 * @return 
 */
    public int getKaart2nummer( ){
        return kaart2nr;
    }

/**
 * Geeft de waarde van kaart nummer 3 terug
 * @return 
 */
    public int getKaart3nummer( ){
        return kaart3nr;
    }

/**
 * Geeft de waarde van het aantal gelijk omgedraaide kaarten terug
 * @return 
 */
    public int getGelijkaantal( ) {
        return gelijkaantal;
    }
    
/**
 * Geeft de waarde van het aantal gemaakte fouten terug
 * @return 
 */
    public int getAantalfout( ) {
        return aantalfout;
    }
}
