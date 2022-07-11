package fxmemory;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import java.applet.*;
import java.net.*;
import javafx.scene.control.Button;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
/**
 *
 * @author paul
 */
public class Kaart {
    private Kaart kaart;
    private int kaartnr, xas, yas, kaartenomgedraaid, kaart1nr, kaart2nr, 
    kaart3nr, gelijkaantal, kaartaantal, knopnr, aantalfout, i, 
    positie, scoresplitlengte, formule;
    private Button knop;
    private GridPane kaartPane; 
    private Rectangle voorkant, achterkant, voorkantplaatje, achterkantplaatje;
    private Boolean gelijk;
    private Text fruittekst, aantalfouttekst; 
    private Generator generatie;
    private Locatie locatie;    
    private Gelijkregel gelijkregel;
    private Image plaatje;
    private String score, naam, newscorepath, inputnaam, inhoud, scoresplit, 
    newscore, inputaantalfout, path, scoreinvoer, scoreuitvoer, sleutel;
    private static SecretKeySpec geheimSleutel;
    private static byte[] temp;
    private Alert alert5;

/**
 * Laat de voorkant van een kaart in het venster zien op de betreffende xas en 
 * yas.
 * @param kaartPane
 * @param kaartnr
 * @param generatie
 * @param locatie 
 */    
    public void setVoorkant( GridPane kaartPane, int kaartnr, 
        Generator generatie, Locatie locatie) {
        //De x en y as ophalen voor de betreffende kaart. En de voorkant van de 
        //kaart op het scherm tonen
        xas = locatie.getXas( kaartnr );
        yas = locatie.getYas( kaartnr );
        voorkant = new Rectangle (80, 80);
        voorkant.setFill(Color.WHITE);
        voorkant.setArcWidth(10);
        voorkant.setArcHeight(10);
            
        fruittekst = new Text (generatie.getFruit(kaartnr));

        //De voorkant van de betreffende kaart toevoegen aan het kaartPane op de
        //betreffende x en y as
        kaartPane.add(voorkant, xas, yas);
        kaartPane.add(fruittekst, xas, yas);
        GridPane.setHalignment(fruittekst, javafx.geometry.HPos.CENTER);
    }

/**
 * Laat de achterkant inclusief plaatje van een kaart in het venster zien
 * op de betreffende xas en yas
 * @param kaartPane
 * @param kaartnr
 * @param generatie
 * @param locatie
 * @param radio 
 */        
    public void setAchterkant( GridPane kaartPane, int kaartnr, 
        Generator generatie, Locatie locatie, int radio ) {
        //De x en y as ophalen voor de betreffende kaart. En de achterkant van 
        //de kaart op het scherm tonen inclusief ingesteld plaatje
        xas = locatie.getXas( kaartnr );
        yas = locatie.getYas( kaartnr );
        achterkantplaatje = new Rectangle (80, 80);
        achterkantplaatje.setFill(Color.BLUE);
        achterkantplaatje.setArcWidth(10);
        achterkantplaatje.setArcHeight(10);

        if (radio == 1){
            plaatje = new Image("https://s3.envato.com/files/142244306/"
            + "Satellite%20in%20Orbit%20Thumb.jpg");
        }
        if (radio == 2){
            plaatje = new Image("https://s3.envato.com/files/224524771/"
            + "Spaceship%20Leaving%20Earth%20thumb.jpg");
        }
        if (radio == 3){
            plaatje = new Image("https://s3.envato.com/files/250199797/"
            + "SATURN%20PLANET%204K%20t.jpg");
        }
        if (radio == 4){
            plaatje = new Image("https://s3.envato.com/files/243514347/"
            + "nasahu185.jpg");
        }

        //Maakt een patroon aan van het plaatje om het vierkant object mee te
        //vullen
        ImagePattern imagePattern = new ImagePattern(plaatje);
        achterkantplaatje.setFill(imagePattern);
        
        //Voegt de objecten toe aan het kaartPane op de betreffende x en y as
        kaartPane.add(achterkantplaatje, xas, yas);
    }
    
/**
 * Initialiseert een knop die doorzichtig is en bovenop de betreffende kaart 
 * wordt geplaatst. Handeld ook het gedeelte af wanneer er op deze knop geklikt
 * wordt.
 * @param kaartPane
 * @param knopnr
 * @param generatie
 * @param gelijkregel
 * @param naam
 * @param locatie
 * @param aantalfouttekst
 * @param radio
 * @param geluid 
 */
    public void setKnop( GridPane kaartPane, int knopnr, Generator generatie, 
        Gelijkregel gelijkregel, String naam, Locatie locatie, 
        Text aantalfouttekst, int radio, Boolean geluid ) {
        //vraagt x en y as locatie op voor de betreffende knop
        xas = locatie.getXas( knopnr );
        yas = locatie.getYas( knopnr );
    
        //Maakt nieuwe knop aan
        knop = new Button ();
        
        //Stelt de maat van de knop in en geeft deze de kleur transparent
        //(doorzichtbaar)
        knop.setPrefSize(80,80);
        knop.setStyle("-fx-background-color:TRANSPARENT");

        //Voegt de knop toe aan de kaartPane op de betreffende x en y as
        kaartPane.add(knop, xas, yas);
        
        //Het afhandelen van het klikken op de knop
        knop.setOnAction( event -> {
            kaart = new Kaart();                
            
                //Het afspelen van een geluid
                try {
                    if (geluid == true){
			AudioClip clip = Applet.newAudioClip(new URL("http://"
                        + "gamecodeschool.com/wp-content/uploads/2016/07/"
                        + "asteroids-ship-shoot.wav"));
			clip.play();
                    }
		} 
		catch (MalformedURLException murle) {
			System.out.println("error");
		}            
            
            //Het bijhouden van het aantal amgedraaide kaarten en het 
            //opvragen van de geklikte kaarten
            kaartenomgedraaid = gelijkregel.kaartOmgedraaid( knopnr );
            kaart1nr = gelijkregel.getKaart1nummer();
            kaart2nr = gelijkregel.getKaart2nummer();
            kaart3nr = gelijkregel.getKaart3nummer();

            //Als het omgedraaide aantal kaarten 3 is wordt de achterkant 
            //getoond van de 2 daarvoor omgedraaide kaarten en als deze 
            //kaarten niet gelijk waren worden hiervoor de knoppen opnieuw
            //geinitialiseerd, als deze kaarten wel gelijk waren blijft 
            //de voorkant van deze kaarten zichtbaar. Ook wordt het aantal
            //omgedraaide kaarten bij deze gereset
            if ( kaartenomgedraaid == 3 ){
                gelijk = gelijkregel.vergelijk( generatie );
                if ( gelijk == false ) {
                    kaart = new Kaart();
                    kaart.setAchterkant(kaartPane, kaart1nr, generatie, 
                    locatie, radio);
                    kaart.setAchterkant(kaartPane, kaart2nr, generatie, 
                    locatie, radio);
                    setKnop(kaartPane, kaart1nr, generatie, gelijkregel, 
                    naam, locatie, aantalfouttekst, radio, geluid);
                    setKnop(kaartPane, kaart2nr, generatie, gelijkregel, 
                    naam, locatie, aantalfouttekst, radio, geluid);
                }
                else {
                kaart.setVoorkant( kaartPane, kaart1nr, generatie, locatie );
                kaart.setVoorkant( kaartPane, kaart2nr, generatie, locatie );
                }
                gelijkregel.resetOmgedraaid( kaart3nr );
            }
            
            //De voorkant van de kaart wordt getoond
            kaart.setVoorkant( kaartPane, knopnr, generatie, locatie );

            //Als het aantal kaarten omgedraaid 2 is wordt er gekeken of deze
            //gelijk zijn en zo niet wordt er een punt bij het totaal aantal
            //fout omgedraaid bij geteld. Ook wordt er na twee omgedraaide 
            //kaarten de knoppen van deze kaarten opnieuw geinitialiseerd
            //zodat er op deze opnieuw geklikt kan worden
            if ( kaartenomgedraaid == 2 ){
                gelijk = gelijkregel.vergelijkfout( generatie );  
                aantalfout = gelijkregel.getAantalfout();
                aantalfouttekst.setText("fout :" + aantalfout);
                setKnop(kaartPane, kaart1nr, generatie, gelijkregel, naam, 
                locatie, aantalfouttekst, radio, geluid);
                setKnop(kaartPane, kaart2nr, generatie, gelijkregel, naam, 
                locatie, aantalfouttekst, radio, geluid);
            }
            
            gelijkaantal = gelijkregel.getGelijkaantal();
            kaartaantal = generatie.getKaartaantal();
            
            //Als de laatste 2 kaarten zijn omgedraaid (als het gelijk aantal
            //kaarten gelijk is aan het totale aantal kaarten), wordt er 
            //gekeken naar het aantal fouten dat is gemaakt en wordt er 
            //een nieuw hiscore gegenereerd en deze wordt vervolgens encrypted
            //opgeslagen in het hiscore.txt bestand. Ook wordt er een mededeling
            //gedaan van de behaalde plek in de hiscore
                System.out.println("kaartomgedraaid " + kaartenomgedraaid + "gelijk aantal " + gelijkaantal + " kaartaantal" + kaartaantal);
            if ( kaartenomgedraaid == 2 && gelijkaantal == kaartaantal) {
                aantalfout = gelijkregel.getAantalfout();
                score = getHiscore();
                setNaam( naam );
                setAantalfout( aantalfout );
                newscore = genereer( kaartaantal, score );

                slaOp( kaartaantal, newscore );
                
                alert5 = new Alert(AlertType.INFORMATION);
                alert5.setTitle("Gefeliciteerd");
                alert5.setHeaderText("U heeft alles omgedraaid");
                
                if (positie == 0){
                    alert5.setContentText("U feet de hiscore niet gehaald!");
                }
                else {
                    alert5.setContentText("U staat op nr " + positie 
                    + " in de hiscore!");
                }
                alert5.showAndWait();
            }
        });       
    }

/**
 * Slaat de nieuwe hiscore op in het bestand : hiscore.log (Deze hiscore is hier
 * al encrypted)
 * @param kaartaantal
 * @param newscore 
 */    
    public void slaOp ( int kaartaantal, String newscore ) {
        path = "hiscore.log";
        sleutel = "school";

        //Het encrypten van de ingevoerde String
        scoreinvoer = encrypt(newscore, sleutel) ;
        
        try {
            //Het in het bestand zetten van de String
            Files.write(Paths.get(path), scoreinvoer.getBytes());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

/**
 * Slaat de meegegeven naam op
 * @param naam 
 */
    public void setNaam ( String naam ) {
        this.naam = naam;
    }

/**
 * Stelt het aantal gemaakte fouten in
 * @param aantalfout 
 */
    public void setAantalfout ( int aantalfout ) {
        this.aantalfout = aantalfout;
    }
    
/**
 * Vraagt de hiscore op uit het bestand : hiscore.log en decrypt deze
 * @param kaartaantal
 * @return 
 */
    public String getHiscore (){
        sleutel = "school";
        
        try (BufferedReader br = new BufferedReader(new FileReader
        ("hiscore.log"))) {
            scoreuitvoer = "";
            String line;
            //Het uitlezen van het hiscore bestand
            while ((line = br.readLine()) != null) {
                scoreuitvoer += line;
            }

            } catch (IOException e) {
		e.printStackTrace();
            }
        //Het decrypten van de String
        score = decrypt(scoreuitvoer, sleutel) ;       
        
        return score;
    }

/**
 * Genereerd de nieuwe hiscore
 * @param kaartaantal
 * @param score
 * @return 
 */    
    public String genereer ( int kaartaantal, String score ){
        //Het splitsen van de string tussen elke ,
        String[] scoresplit = score.split(",");
        //formule om de locatie te bepalen van de betreffende hiscore
        formule = ((kaartaantal / 2) - 1) * 9;

        i = Integer.parseInt(scoresplit[formule + 8]);
        //Als het aantal fout minder is dan het aantal fout van de derde plaats
        //krijgt de positie waarde 3
        if ( aantalfout <= i ) {
            positie = 3;
        }   
                
        //Als het aantal fout minder is dan het aantal fout van de tweede plaats
        //krijgt de positie waarde 2
        i = Integer.parseInt(scoresplit[formule + 5]);
        if ( aantalfout <= i ) {
            positie = 2;
        }
        
        //Als het aantal fout minder is dan het aantal fout van de eerste plaats
        //krijgt de positie waarde 1
        i = Integer.parseInt(scoresplit[formule + 2]);
        if ( aantalfout <= i ) {
            positie = 1;
        }
              
        //Het formateren van de Strings
        inputnaam = String.format( "%-8s", naam );
        inputaantalfout = String.format( "%04d", aantalfout );

        //Als de positie geen waarde heeft gekregen (dus waarde 0 heeft 
        //behouden) blijft de score leeg
        if (positie != 0) {
        score = "";
        }
        
        //Als de positie waarde 1 heeft wordt de bijbehoorende hiscore
        //toegevoegt
        if ( positie == 1 ){
            for (i = 0; i < formule; i++){
                score += scoresplit[i] + ",";
            }
            score += "1" + "," + inputnaam + "," + inputaantalfout + "," 
            + "2" + "," + scoresplit[(((kaartaantal / 2) - 1) * 9) + 1] + "," + 
            scoresplit[(((kaartaantal / 2) - 1) * 9) + 2] + "," 
            + "3" + "," + scoresplit[(((kaartaantal / 2) - 1) * 9) + 4] + "," + 
            scoresplit[(((kaartaantal / 2) - 1) * 9) + 5] + ",";
            for (i = (formule + 9); i <= 179; i++){
                score += scoresplit[i] + ",";
            }
        }
        
        //Als de positie waarde 2 heeft wordt de bijbehoorende hiscore
        //toegevoegt
        if ( positie == 2 ){
            for (i = 0; i < (formule + 3); i++){
                score += scoresplit[i] + ",";
            }            
            score += inputnaam + "," + inputaantalfout + "," 
            + "3" + "," + scoresplit[(((kaartaantal / 2) - 1) * 9) + 4] + 
            "," + scoresplit[(((kaartaantal / 2) - 1) * 9) + 5] + ",";
            for (i = (formule + 9); i <= 179; i++){
                score += scoresplit[i] + ",";
            }
        }
        
        //Als de positie waarde 3 heeft wordt de bijbehoorende hiscore
        //toegevoegt
        if ( positie == 3 ){
            for (i = 0; i < (formule + 6); i++){
                score += scoresplit[i] + ",";
            }            
            score += inputnaam + "," + inputaantalfout + ",";
            for (i = (formule + 9); i <= 179; i++){
                score += scoresplit[i] + ",";
            }
        }
  
        return score; 
    }

/**
 * Initialiseerd de geheime sleutel die wordt gebruikt voor de encryptie en de
 * decryptie
 * @param mijnSleutel 
 */
    public static void setSleutel(String mijnSleutel)
    {
        MessageDigest sha = null;
        try {
            //Het creeren van de geheime sleutel (die nodig is voor de encryptie
            //en de decryptie)
            temp = mijnSleutel.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            temp = sha.digest(temp);
            temp = Arrays.copyOf(temp, 16);
            geheimSleutel = new SecretKeySpec(temp, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
/**
 * Encrypt de meegegeven String
 * @param strToEncrypt
 * @param geheim
 * @return 
 */
    public static String encrypt(String strToEncrypt, String geheim)
    {
        try
        {
            setSleutel(geheim);
            //Het encrypten van de meegegeven String
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, geheimSleutel);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error tijdens encryptie: " + e.toString());
        }
        return null;
    }
 
/**
 * Decrypt de meegegeven String
 * @param strToDecrypt
 * @param geheim
 * @return 
 */
    public static String decrypt(String strToDecrypt, String geheim)
    {
        try
        {
            setSleutel(geheim);
            //Het decrypten van de meegegeven String
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, geheimSleutel);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error tijdens decryptie: " + e.toString());
        }
        return null;
    }    
}