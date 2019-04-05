
package is.hi.vinnsla;

import java.util.LinkedHashSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Heldur utan um klukkustundir sem notandi getur valið úr
 * Klukkustundir eru fengnar úr gögnum um veðurspár
 * 
 * @author Ebba Þóra Hvannberg ebba@hi.is
 */
public class KlukkustundirModel  {
    private ObservableList<String> klsItems;    
    private static final String ALLAN_DAGINN = "allan daginn";
    /**
     * Smiður fyrir KlukkustundirModel. Tekur inn lista af veðurspám 
     * Síar út mengi af klukkustundum sem veðurspár eru gefnar fyrir 
     * @param dLidir fylki af dagskrárliðum 
     */
    public KlukkustundirModel(ObservableList<Nidurstodur.Vedurstod.Vedurspa> dLidir) {
        
        // Náum í klukkustundir 
        LinkedHashSet timar = new LinkedHashSet();
        final int BIL = 11; 
        timar.add(ALLAN_DAGINN);
        for (Nidurstodur.Vedurstod.Vedurspa r:dLidir) {
           String u = r.getFtime().substring(BIL,BIL+2);
           timar.add(u);
        }
   
        klsItems = FXCollections.observableArrayList();
        // Setjum klukkustundir í módelið 
        for (Object t:timar)
            klsItems.add(t.toString());
    }


    public ObservableList<String> getItems() {
       return klsItems;
    }
    
    public void setItems(ObservableList<String> o) {
       klsItems = o;
    }
    
}
