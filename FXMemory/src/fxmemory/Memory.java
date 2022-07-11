package fxmemory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.*;
import sun.audio.*;
import java.applet.*;
import java.io.File;
import java.net.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 *
 * @author paul
 */
public class Memory {
    private final Generator generatie;
    private final String fruitArray[];
    private final GridPane kaartPane;
    private final Gelijkregel gelijkregel;
    private final Pane bovenpane;
    private final Text naamtekst, aantalfouttekst, starttekst;
    private final Rectangle naamrect, foutrect, startrect;
    private final Locatie locatie;
    private Kaart kaart;
    private int kaartaantal, xas, yas, xpadding, ypadding, kaartnr, knopnr, 
    aantalfout;
    private String naam;
    private Boolean dicht;
    private Button startknop, launchknop;
    
/**
 * Maakt het memory spel venster
 * @param p
 * @param naam
 * @param kaartaantal
 * @param radio
 * @param geluid 
 */
    public Memory(VBox p, String naam, int kaartaantal, int radio, Boolean geluid) {
        aantalfout = 0;

        //Creeeren van het memory venster (knoppen, rechthoeken en teksten) 
        naamrect = new Rectangle( 100, 10, 120, 30 );
        naamrect.setFill(Color.BLACK);
        naamrect.setStroke(Color.WHITE);
        naamrect.setStrokeWidth(3);
        naamrect.setArcWidth(10);
        naamrect.setArcHeight(10);
        naamtekst = new Text( naam );
        naamtekst.relocate( 110, 17 );
        naamtekst.setFill(Color.WHITE);
        naamtekst.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));

        foutrect = new Rectangle( 804, 10, 120, 30 );
        foutrect.setFill(Color.BLACK);
        foutrect.setStroke(Color.WHITE);
        foutrect.setStrokeWidth(3);
        foutrect.setArcWidth(10);
        foutrect.setArcHeight(10);
        aantalfouttekst = new Text( "fout: " + aantalfout );
        aantalfouttekst.relocate( 814, 17 );
        aantalfouttekst.setFill(Color.WHITE);
        aantalfouttekst.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));
        
        startrect = new Rectangle( 452, 10, 120, 30 );
        startrect.setFill(Color.BLACK);
        startrect.setStroke(Color.WHITE);
        startrect.setStrokeWidth(3);
        startrect.setArcWidth(10);
        startrect.setArcHeight(10);
        starttekst = new Text( "Start" );
        starttekst.relocate( 482, 17 );
        starttekst.setFill(Color.WHITE);
        starttekst.setFont(Font.font("Monospaced", FontWeight.BOLD, 20));       
        startknop = new Button();
        startknop.setPrefSize(120,30);
        startknop.relocate( 452, 10 );
        startknop.setStyle("-fx-background-color:TRANSPARENT");

        //creeren van een Pane en het toevoegen van knoppen, teksten en 
        //rechthoeken aan dit Pane
        bovenpane = new Pane();
        bovenpane.getChildren().addAll(naamrect, naamtekst, startrect, 
        starttekst, startknop, foutrect, aantalfouttekst);
       
        //Het creeren van een nieuwe GridPane met een tussenruimte tussen de 
        //Grid velden
        kaartPane = new GridPane();
        kaartPane.setVgap(20);
        kaartPane.setHgap(20);

        gelijkregel = new Gelijkregel();

        //Het toeveogen van de de Pane's aan het p Pane
        p.getChildren().addAll(bovenpane, kaartPane);

        //Het genereren van een nieuwe kaartset
        generatie = new Generator( kaartaantal );
        fruitArray = generatie.getFruitArray();
        
        //Het laten berekenen van de x en y padding (tussenruimte aan de 
        //zijkanten)
        locatie = new Locatie();
        locatie.setBreedte(kaartaantal);
        xpadding = locatie.getXpadding();
        ypadding = locatie.getYpadding();
        kaartPane.setPadding(new Insets(ypadding, xpadding, 
        ypadding, xpadding));
        
        //Het weergeven van voorkant van de kaarten aan de hand van het
        //totale kaartaantal
        for (kaartnr = 0; kaartnr < kaartaantal ; kaartnr++){
            kaart = new Kaart();
            kaart.setVoorkant(kaartPane, kaartnr, generatie, locatie);                
        }

        //Het afhandelen van het klikken op de startknop
        startknop.setOnAction( event -> {        
            //Het weergeven van achterkant van de kaarten aan de hand van het
            //totale kaartaantal
            for (kaartnr = 0; kaartnr < kaartaantal ; kaartnr++){
                kaart.setAchterkant(kaartPane, kaartnr, generatie, 
                locatie, radio);
            }

            //Het initialiseren van de knopppen aan de hand van het totale
            //aantal kaarten
            for (knopnr = 0; knopnr < kaartaantal ; knopnr++){
                kaart.setKnop(kaartPane, knopnr, generatie, gelijkregel, naam, 
                locatie, aantalfouttekst, radio, geluid);
            }

            //Het "disablen" van de knop nadat er op geklikt is en het 
            //afspelen van een geluid
            startknop.setVisible(false);
                try {
                    if (geluid == true){
                        AudioClip clip = Applet.newAudioClip(new URL
                        ("http://www.rkeene.org/archive/quakeworld/quakeforge/"
                        + "fortress/sound/effects/rocket.wav"));
			clip.play();
                    }
 		} 
		catch (MalformedURLException murle) {
			System.out.println("error");
		}
        });
    }
}