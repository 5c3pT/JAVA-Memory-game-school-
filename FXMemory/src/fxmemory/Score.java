package fxmemory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
/**
 *
 * @author paul
 */
public class Score {
private final Text nrtekst, naamtekst, fouttekst, hiscoretekst, nr1tekst, 
nr2tekst, nr3tekst, naam1tekst, naam2tekst, naam3tekst, fout1tekst, 
fout2tekst, fout3tekst;
private int hiscore, formule;
private Pane ScorePane;
private String score, scoresplit;

/**
 * Creeert een scorevenster met daarin de opgevraagde hiscore
 * @param ScorePane
 * @param score
 * @param hiscore 
 */
    public Score(Pane ScorePane, String score, int hiscore) {
        //Het splitsen van de hiscore string
        String[] scoresplit = score.split(",");        
        //Het maken van een formule voor de locatie van de betreffende hiscore
        formule = ((hiscore / 2) - 1) * 9;
        
        //Het creeren van het hiscore venster (tekst)
        hiscoretekst = new Text (" - Hiscore " + hiscore + " - ");
        hiscoretekst.relocate(150, 50);

        nrtekst = new Text ("nr:");
        nrtekst.relocate(100, 100);
        naamtekst = new Text ("naam:");
        naamtekst.relocate(150, 100);
        fouttekst = new Text ("fout:");
        fouttekst.relocate(250, 100);


        nr1tekst = new Text (scoresplit[formule + 0]);
        nr1tekst.relocate(100, 150);
        nr2tekst = new Text (scoresplit[formule + 3]);
        nr2tekst.relocate(100, 200);
        nr3tekst = new Text (scoresplit[formule + 6]);
        nr3tekst.relocate(100, 250);
        
        naam1tekst = new Text (scoresplit[formule + 1]);
        naam1tekst.relocate(150, 150);
        naam2tekst = new Text (scoresplit[formule + 4]);
        naam2tekst.relocate(150, 200);
        naam3tekst = new Text (scoresplit[formule + 7]);
        naam3tekst.relocate(150, 250);        
       
        fout1tekst = new Text (scoresplit[formule + 2]);
        fout1tekst.relocate(250, 150);
        fout2tekst = new Text (scoresplit[formule + 5]);
        fout2tekst.relocate(250, 200);
        fout3tekst = new Text (scoresplit[formule + 8]);
        fout3tekst.relocate(250, 250);        
        
    //Het toevoegen van de teksten aan het ScorePane
    ScorePane.getChildren().addAll(hiscoretekst, nrtekst, naamtekst, fouttekst, 
    nr1tekst, nr2tekst, nr3tekst, naam1tekst, naam2tekst, naam3tekst, 
    fout1tekst, fout2tekst, fout3tekst);    
    }    
}
