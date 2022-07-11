package fxmemory;
/**
 *
 * @author paul
 */
public class Generator {
    private final String fruitArray[];
    private int random1, random2, kaartaantal;
    private String fruitsoort;
    private Boolean match, randommatch;
    
/**
 * Genereerd een nieuw set kaarten
 * @param kaartaantal 
 */
    public Generator( int kaartaantal ) {
        this.kaartaantal = kaartaantal;
        fruitArray = new String[kaartaantal];
        
        //Het geven van de standaard waarde "leeg" aan elke String binnen de 
        //fruitarray
        for (int i = 0; i < kaartaantal; i++) {
            fruitArray[i] = "leeg";
        }        
        
        //Een for loop creeren die begint bij 0 en eindigt als deze kleiner
        //is dan het totale aantal verschillende fruitsoorten
        for (int i = 0; i < (kaartaantal / 2); i++) {
            match = false;
            
            //Het vinden van 2 gelijke kaarten en deze een willekeurig nog
            //ongebruikt kaart nummer toewijzen. Ook de integer waarde van de
            //kaart omzetten naar een fruitsoort.
            while ( match == false ) {
                randommatch = true;                
                while ( randommatch == true ){
                    random1 = (int) ( kaartaantal * Math.random() );
                    random2 = (int) ( kaartaantal * Math.random() );
                    if (random1 != random2){
                        randommatch = false;
                    }
                    else {
                        randommatch = true;
                    }
                }
                    
                if (fruitArray[random1] == "leeg" && 
                    fruitArray[random2] == "leeg") {
                    if (i == 0){
                        fruitsoort = "Appel";
                    }
                    if (i == 1){
                        fruitsoort = "Kers";
                    }
                    if (i == 2){
                        fruitsoort = "Banaan";
                    }
                    if (i == 3){
                        fruitsoort = "Peer";
                    }
                    if (i == 4){
                        fruitsoort = "Mandarijn";
                    }
                    if (i == 5){
                        fruitsoort = "Druif";
                    }
                    if (i == 6){
                        fruitsoort = "Kiwi";
                    }
                    if (i == 7){
                        fruitsoort = "Perzik";
                    }
                    if (i == 8){
                        fruitsoort = "Mango";
                    }
                    if (i == 9){
                        fruitsoort = "Annanas";
                    }
                    if (i == 10){
                        fruitsoort = "Pruim";
                    }
                    if (i == 11){
                        fruitsoort = "sinas";
                    }
                    if (i == 12){
                        fruitsoort = "Nectarine";
                    }
                    if (i == 13){
                        fruitsoort = "Abrikoos";
                    }
                    if (i == 14){
                        fruitsoort = "Aardbij";
                    }
                    if (i == 15){
                        fruitsoort = "Bes";
                    }
                    if (i == 16){
                        fruitsoort = "Framboos";
                    }
                    if (i == 17){
                        fruitsoort = "Braam";
                    }
                    if (i == 18){
                        fruitsoort = "Meloen";
                    }
                    if (i == 19){
                        fruitsoort = "Citroen";
                    }
                    if (i == 20){
                        fruitsoort = "Grapefruit";
                    }
                    
                    fruitArray[random1] = fruitsoort;
                    fruitArray[random2] = fruitsoort;
                    match = true;
                }
                else {
                    match = false;
                }
            }
        }    
    }

/**
 * Geeft de fruitarray terug
 * @return 
 */
    public String[] getFruitArray() {
        return fruitArray;
    }

/**
 * Geeft het kaartaantal terug
 * @return 
 */    
    public int getKaartaantal() {
        return kaartaantal;
    }

/**
 * Geeft het fruitsoort terug aan de hand vann een kaartnummer
 * @param kaartnr
 * @return 
 */    
    public String getFruit( int kaartnr ) {
        return fruitArray[kaartnr];
    }
}
