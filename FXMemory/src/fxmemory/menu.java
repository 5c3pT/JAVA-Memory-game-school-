package fxmemory;
import static fxmemory.Kaart.encrypt;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 *
 * @author paul
 */
public class menu {
private final RadioButton radio1, radio2, radio3, radio4;
private final Button memoryknop, hiscoreknop;
private final Text memorytekst, naamtekst, aantalkaartentekst, hiscoretekst, 
geluidtekst;
private final TextField naamtekstvak, aantalkaartentekstvak;
private final Rectangle vk1, vk2, vk3, vk4;
private final Line lijn;
private final ChoiceBox<String> hiscorebox;
private int radio, kaartaantal, hiscoreint;
private Pane menuPane;
private String naam, hiscoreinvoer, aantalkaarteninvoer, naaminvoer, score, 
path, scoreinvoer;
private Image plaatje1, plaatje2, plaatje3, plaatje4;
private ImagePattern patroon1, patroon2, patroon3, patroon4;
private Kaart kaart;
private Alert alert1, alert2, alert3, alert4;
private CheckBox checkbox;
private Boolean geluid, hiscorebestaat;
private File hiscorefile;
/**
 * Creeerd een menu venster inclusief de afhandeling van het klikken op de 
 * bij het menu horende knoppen. Zorgt ook voor meldingen bij foutive 
 * invoer van de tekstvelden.
 * @param menuPane 
 */
    public menu(Pane menuPane) {
        //Creeeren van het menu vensten (teksten, knoppen, invoervakken, 
        //checkbox, radio knoppen en keuze box)
        memorytekst = new Text (" - Memory - ");
        memorytekst.relocate( 150, 10 );
        
        naamtekst = new Text ("naam : ");
        naamtekst.relocate( 50, 50 );
        
        aantalkaartentekst = new Text ("aantal kaarten : ");
        aantalkaartentekst.relocate( 50, 100 );

        naamtekstvak = new TextField ("");
        naamtekstvak.relocate( 150, 50 );

        aantalkaartentekstvak = new TextField ("16");
        aantalkaartentekstvak.relocate( 150, 100 );

        checkbox = new CheckBox("");
        checkbox.relocate( 320, 50 );
        checkbox.setSelected(true);
        
        geluidtekst = new Text ("geluid");
        geluidtekst.relocate ( 340, 50 );
        
        memoryknop = new Button ("memory");
        memoryknop.relocate( 320, 100 );

        //Het maken van een nieuwe toggle-groep waaraan de radio knoppen kunnen 
        //behoren
        ToggleGroup groep = new ToggleGroup();
        
        vk1 = new Rectangle (80, 80);
        vk1.relocate(60, 160);
        vk1.setFill(Color.BLUE);
        vk1.setArcWidth(10);
        vk1.setArcHeight(10);

        plaatje1 = new Image("https://s3.envato.com/files/142244306/"
        + "Satellite%20in%20Orbit%20Thumb.jpg");
        patroon1 = new ImagePattern(plaatje1);
        vk1.setFill(patroon1);
        
        radio1 = new RadioButton("1");
        radio1.relocate(60, 250);
        radio1.setToggleGroup(groep); 
        radio1.setSelected(true); 
        
        vk2 = new Rectangle (80, 80);
        vk2.relocate(200, 160);
        vk2.setFill(Color.BLUE);
        vk2.setArcWidth(10);
        vk2.setArcHeight(10);

        plaatje2 = new Image("https://s3.envato.com/files/224524771/"
        + "Spaceship%20Leaving%20Earth%20thumb.jpg");
        patroon2 = new ImagePattern(plaatje2);
        vk2.setFill(patroon2);
        
        radio2 = new RadioButton("2");
        radio2.relocate(200, 250);
        radio2.setToggleGroup(groep); 

        vk3 = new Rectangle (80, 80);
        vk3.relocate(60, 270);
        vk3.setFill(Color.BLUE);
        vk3.setArcWidth(10);
        vk3.setArcHeight(10);

        plaatje3 = new Image("https://s3.envato.com/files/250199797/"
        + "SATURN%20PLANET%204K%20t.jpg");
        patroon3 = new ImagePattern(plaatje3);
        vk3.setFill(patroon3);
        
        radio3 = new RadioButton("3");
        radio3.relocate(60, 360);
        radio3.setToggleGroup(groep); 
        
        vk4 = new Rectangle (80, 80);
        vk4.relocate(200, 270);
        vk4.setFill(Color.BLUE);
        vk4.setArcWidth(10);
        vk4.setArcHeight(10);

        plaatje4 = new Image("https://s3.envato.com/files/243514347/"
        + "nasahu185.jpg");
        patroon4 = new ImagePattern(plaatje4);
        vk4.setFill(patroon4);

        radio4 = new RadioButton("4");
        radio4.relocate(200, 360);
        radio4.setToggleGroup(groep);
       
        lijn = new Line (0, 400, 400, 400);
        
        hiscorebox = new ChoiceBox<>();
        for (int i = 2; i < 41; i += 2){
            hiscorebox.getItems().add("" + i);
        }
        hiscorebox.relocate( 150, 420 );
        hiscorebox.setValue("16");
        
        hiscoretekst = new Text ("hiscore : ");
        hiscoretekst.relocate( 50, 420 );

        hiscoreknop = new Button ("bekijk");
        hiscoreknop.relocate( 320, 420 );        
        
        //Kijken of het hiscore.log bestand bestaat
        hiscorefile = new File("hiscore.log");
        hiscorebestaat = hiscorefile.exists();

        //Als hiscore.log niet bestaat, deze aanmaken
        if ( hiscorebestaat == false ) {
        path = "hiscore.log";
        scoreinvoer = "";
        for ( int i = 0; i < 20; i++ ) {
            scoreinvoer += "aMB3W9O6JmMaoIq7MPKi7XkRSaEpqpOcdpb+jCf+UkxxBxMIG"
            + "z1ahp2yqezZuFQn";
            }
        scoreinvoer += "PVys0IemVR9Xyti7CwK1aA==";

        try {
            //Het in het bestand zetten van de String
            Files.write(Paths.get(path), scoreinvoer.getBytes());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        }
        
        //Het afhandelen van het klikken op de hiscore knop
        hiscoreknop.setOnAction( event -> {
            //Het opvragen van de betreffende hiscore
            hiscoreinvoer = hiscorebox.getValue();
            hiscoreint = Integer.parseInt(hiscoreinvoer);
            kaart = new Kaart();
            score = kaart.getHiscore();

            //Het openen van een nieuw venster (het hiscore venster)
            Stage scorestage = new Stage();
            Pane scorepane = new Pane();
            Scene scorescene = new Scene(scorepane, 400, 400);
            new Score(scorepane, score, hiscoreint);

            scorestage.setScene(scorescene);
            scorestage.show(); 
            
        });
   
        //Het afhandelen van het klikken op de memory knop
        memoryknop.setOnAction( event -> {
            naaminvoer = naamtekstvak.getText();
            aantalkaarteninvoer = aantalkaartentekstvak.getText();
            naam = naaminvoer;
            //Het inkorten van de ingevoerde naam tot maximaal 8 tekens
            if (naaminvoer.length() > 8){ 
                naam = naaminvoer.substring(0, 8);
            }
            
            try {
            kaartaantal = Integer.parseInt(aantalkaarteninvoer);
            //Het controleren of het ingevoerde kaartaantal en de ingevoerde 
            //naam juist zijn ingevoerd (zo ja begint het memory spel)
            if ( kaartaantal % 2 == 0 && kaartaantal > 1 && kaartaantal < 41 && 
            naam.contains(",") == false ) {
                //Kijken welke radio knop voor het achtergrond plaatje is
                //geselecteerd
                if (radio1.isSelected()) {
                    radio = 1;
                }
                if (radio2.isSelected()) {
                    radio = 2;
                }
                if (radio3.isSelected()) {
                    radio = 3;
                }
                if (radio4.isSelected()) {
                    radio = 4;
                }

                //Kijken op geluid is aangeklikt of niet
                if (checkbox.isSelected()) {
                    geluid = true;
                }
                else {
                    geluid = false;
                }

                //Nieuw venster openen (memory venster) met achtergrond plaatje
                Stage memorystage = new Stage();
                VBox memory = new VBox();
                memory.setStyle("-fx-background-image: url('https://"
                + "www.tcnorth.com/wp-content/uploads/2014/06/"
                + "Mindful-Space-1024x768.jpg')");
                Scene memoryscene = new Scene(memory, 1024, 768);
                new Memory(memory, naam, kaartaantal, radio, geluid);

                memorystage.setScene(memoryscene);
                memorystage.show();

            }
            else {
                // Als de naam een , bevat geeft deze een foutmelding
                if ( naam.contains(",") == true ) {
                    alert4 = new Alert(AlertType.ERROR);
                    alert4.setTitle("Error");
                    alert4.setHeaderText("Error Dialog");
                    alert4.setContentText("De naam mag geen , bevatten");
                    alert4.showAndWait();
                }
                else {
                    //Als het aantal kaarten niet goed is ingevoerd geeft deze
                    //een foutmelding
                    alert1 = new Alert(AlertType.ERROR);
                    alert1.setTitle("Error");
                    alert1.setHeaderText("Error Dialog");
                    alert1.setContentText("Het aantal kaarten moet even zijn "
                    + "en tussen de 1 en de 41");
                    alert1.showAndWait();
                }
            }
            
            }
            catch(NumberFormatException nfe) {
                if(aantalkaarteninvoer.equals("")){
                    //Als het aantal kaarten leeg is ingevoerd geeft deze een
                    //foutmelding
                    alert2 = new Alert(AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setHeaderText("Error Dialog");
                    alert2.setContentText("U heeft nog geen aantal "
                    + "kaarten ingevoerd");
                    alert2.showAndWait();                }
                else {
                    //Als er letters bij het ingevoerde aantal kaarten staan
                    //geeft deze een foutmelding
                    alert3 = new Alert(AlertType.ERROR);
                    alert3.setTitle("Error");
                    alert3.setHeaderText("Error Dialog");
                    alert3.setContentText("Het aantal kaarten moet een "
                    + "getal zijn (even, en tussen de 1 en de 41");
                    alert3.showAndWait();
                }
            }
        });
        
        //Het toevoegen van de objecten aan het menuPane
        menuPane.getChildren().addAll(memorytekst, checkbox, geluidtekst, 
        memoryknop, naamtekst, aantalkaartentekst, naamtekstvak, 
        aantalkaartentekstvak, vk1, radio1, vk2, radio2, vk3, radio3, vk4, 
        radio4, lijn, hiscoretekst, hiscorebox, hiscoreknop);        
    }
}
