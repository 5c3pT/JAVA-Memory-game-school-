package fxmemory;
import java.lang.Math;
/**
 *
 * @author paul
 */
public class Locatie {
    private int kaartnr, xas, yas, breedte, hoogte, kaartaantal, 
    xpadding, ypadding;
    private double breedtedouble, kaartaantaldouble;

/**
 * berekend de breedte aantal en het hoogte aantal van het spelbord aan de 
 * hand van het totaal aantal kaarten
 * @param kaartaantal 
 */    
    public void setBreedte ( int kaartaantal ){
        //Het hoogte aantal kaarten en breedte aantal kaarten van het speelveld
        //berekenen door gebruik te maken van de wortel functie
        kaartaantaldouble = kaartaantal;
        breedtedouble = Math.sqrt( kaartaantaldouble );
        breedte = (int) breedtedouble;
        hoogte = breedte;
        if (breedtedouble - breedte > 0) {
            breedte += 1;
        }
        if ((breedte * hoogte) - kaartaantal < 0 ){
            hoogte += 1;
        }
    }

/**
 * Berekend en geeft de xpadding terug
 * @return 
 */
    public int getXpadding () {
        xpadding = (1024 - (breedte * 100)) / 2;
        return xpadding;    
    }
    
/**
 * Berekend en geeft de ypadding terug
 * @return 
 */
    public int getYpadding () {
        ypadding = (728 - (hoogte * 100)) / 2;
        return ypadding;
    }
    
/**
 * Berekend en geeft de xas terug van een bepaald kaart nummer
 * @param kaartnr
 * @return 
 */
    public int getXas( int kaartnr ){
        xas = kaartnr % breedte;
        return xas;
    }

/**
 * Berekend en geeft de yas terug van een bepaald kaart nummer
 * @param kaartnr
 * @return 
 */
    public int getYas( int kaartnr ){
        yas = kaartnr / breedte;
        return yas;            
    } 
}
