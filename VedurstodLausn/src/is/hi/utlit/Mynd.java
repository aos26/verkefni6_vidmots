/*
Ebba Þóra Hvannberg ebba@hi.is
 */

package is.hi.utlit;

import javafx.scene.image.Image;

/**
 * Skilgreinir hvaða veðurmyndir eru til 
 * 
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * Háskóli Íslands
 */
public enum Mynd {
    Heidskirt("Heiðskírt","https://www.vedur.is/media/vedurtakn/xqjxmmx8.png"),
    Alskyjad("Alskýjað","https://www.vedur.is/media/vedurtakn/azdavqrh.png"),
    Lettskyjad("Léttskýjað", "https://www.vedur.is/media/vedurtakn/d9hxxay9.png"),
    Snjokoma("Snjókoma", "https://www.vedur.is/media/vedurtakn/hqbar2z7.png"),
    Skyjad("Skýjað", "https://www.vedur.is/media/vedurtakn/4pt8h2pr.png"),
    LitilshattarSnjokoma("Lítils háttar snjókoma", "https://www.vedur.is/media/vedurtakn/mgzhcaap.png"),
    LitilshattarSlydda("Lítils háttar slydda", "https://www.vedur.is/media/vedurtakn/r2aeqe3m.png"),
    Snjoel ("Snjóél","https://www.vedur.is/media/vedurtakn/pfwxwgw9.png"),
    LitilshattarRigning("Lítils háttar rigning", "https://www.vedur.is/media/vedurtakn/7f3rxum6.png"),
    Rigning("Rigning", "https://www.vedur.is/media/vedurtakn/hnrqk944.png"),
    Skurir("Skúrir", "https://www.vedur.is/media/vedurtakn/24wvnrvw.png"),
    
    Clear("Clear sky","https://www.vedur.is/media/vedurtakn/xqjxmmx8.png"),
    Overcast("Overcast","https://www.vedur.is/media/vedurtakn/azdavqrh.png"),
    PartlyCloudy("Partly Cloudy", "https://www.vedur.is/media/vedurtakn/d9hxxay9.png");
    /*("Snjókoma", "https://www.vedur.is/media/vedurtakn/hqbar2z7.png"),
    Skyjad("Skýjað", "https://www.vedur.is/media/vedurtakn/4pt8h2pr.png"),
    LitilshattarSnjokoma("Lítils háttar snjókoma", "https://www.vedur.is/media/vedurtakn/mgzhcaap.png"),
    LitilshattarSlydda("Lítils háttar slydda", "https://www.vedur.is/media/vedurtakn/r2aeqe3m.png"),
    Snjoel ("Snjóél","https://www.vedur.is/media/vedurtakn/pfwxwgw9.png"),
    LitilshattarRigning("Lítils háttar rigning", "https://www.vedur.is/media/vedurtakn/7f3rxum6.png"),
    Rigning("Rigning", "https://www.vedur.is/media/vedurtakn/hnrqk944.png");*/
    
    private final Image vedurMynd;
    private final String gildi;

    public String getGildi() {
        return gildi;
    }
    
    Mynd (String gildi, String url) {
        vedurMynd= new Image(url, true);
        this.gildi = gildi; 
    }
    
    public Image getVedurMynd() {
        return vedurMynd;
    }
}
