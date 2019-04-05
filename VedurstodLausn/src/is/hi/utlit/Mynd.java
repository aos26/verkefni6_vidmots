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
    Slydda("Slydda", "https://www.vedur.is/media/vedurtakn/ritn7mht.png"),
    Slydduel("Slydduél", "https://www.vedur.is/media/vedurtakn/6d7adrut.png"),
    Skystrokar("Skýstrókar", "https://www.vedur.is/media/vedurtakn/cbuwp6yb.png"),
    Moldrok("Moldrok", "https://www.vedur.is/media/vedurtakn/a67gbuvn.png"),
    Skafrenningur("Skafrenningur", "https://www.vedur.is/media/vedurtakn/hkumd2yn.png"),
    Thoka("Þoka", "https://www.vedur.is/media/vedurtakn/nrcuu2cc.png"),
    LitilshattarSuld("Lítils háttar súld", "https://www.vedur.is/media/vedurtakn/nmjhcwid.png"),
    Suld("Súld", "https://www.vedur.is/media/vedurtakn/iamt8hky.png"),
    Frostrigning("Frostrigning", "https://www.vedur.is/media/vedurtakn/xx7f2xgg.png"),
    Hagl("Hagl", "https://www.vedur.is/media/vedurtakn/3qinx4mp.png"),
    LitilshattarThrumuvedur("Lítils háttar þrumuveður", "https://www.vedur.is/media/vedurtakn/kkj26ft8.png"),
    
    Clear("Clear sky","https://www.vedur.is/media/vedurtakn/xqjxmmx8.png"),
    Overcast("Overcast","https://www.vedur.is/media/vedurtakn/azdavqrh.png"),
    PartlyCloudy("Partly Cloudy", "https://www.vedur.is/media/vedurtakn/d9hxxay9.png"),
    Snow("Snow", "https://www.vedur.is/media/vedurtakn/hqbar2z7.png"),
    Cloudy("Cloudy", "https://www.vedur.is/media/vedurtakn/4pt8h2pr.png"),
    LightSnow("Light snow", "https://www.vedur.is/media/vedurtakn/mgzhcaap.png"),
    LightSleet("Light sleet", "https://www.vedur.is/media/vedurtakn/r2aeqe3m.png"),
    SnowShowers("Snow showers","https://www.vedur.is/media/vedurtakn/pfwxwgw9.png"),
    LightRain("Light rain", "https://www.vedur.is/media/vedurtakn/7f3rxum6.png"),
    Rain("Rain", "https://www.vedur.is/media/vedurtakn/hnrqk944.png"),
    RainShowers("Rain showers", "https://www.vedur.is/media/vedurtakn/24wvnrvw.png"),
    Sleet("Sleet", "https://www.vedur.is/media/vedurtakn/ritn7mht.png"),
    SleetShowers("Sleet showers", "https://www.vedur.is/media/vedurtakn/6d7adrut.png"),
    DustDevil("Dust devil", "https://www.vedur.is/media/vedurtakn/cbuwp6yb.png"),
    DustStorm("Dust storm", "https://www.vedur.is/media/vedurtakn/a67gbuvn.png"),
    BlowingSnow("Blowing snow", "https://www.vedur.is/media/vedurtakn/hkumd2yn.png"),
    Fog("Fog", "https://www.vedur.is/media/vedurtakn/nrcuu2cc.png"),
    LightDrizzle("Light drizzle", "https://www.vedur.is/media/vedurtakn/nmjhcwid.png"),
    Drizzle("Drizzle", "https://www.vedur.is/media/vedurtakn/iamt8hky.png"),
    FreezingRain("Freezing rain", "https://www.vedur.is/media/vedurtakn/xx7f2xgg.png"),
    Hail("Hail", "https://www.vedur.is/media/vedurtakn/3qinx4mp.png"),
    LightThunder("Light thunder", "https://www.vedur.is/media/vedurtakn/kkj26ft8.png"),
    ;
    
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
