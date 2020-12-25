package sample;

import javafx.scene.paint.Color;

public enum MyColor { // all colors
    BLACK(0,0,0), MOCCASIN(255,228,181), RED(255,0,0),
    LIME(0,255,0), YELLOW(255,255,0), LAVENDERLUSH(255,240,240),
    CYAN(0,255,255), MAGENTA(255,0,255), SILVER(192,192,192),
    GRAY(128,128,128), MAROON(128,0,0), OLIVE(128,128,0),
    GREEN(0,128,0), PURPLE(128,0,128), TEAL(0,128,128),
    BROWN(165,42,42), DARKORANGE(255,140,0), SKYBLUE(135,206,235),
    SALMON(250,128,114), KHAKI(240,230,140), YELLOWGREEN(154,205,50),
    DARKGREEN(0,100,0), DARKSEAGREEN(143,188,143), TURQUOISE(64,224,208),
    ROYALBLUE(65,105,225), SADDLEBROWN(139,69,19), GREENYELLOW(173,255,47),
    CRIMSON(220,20,60);
    private Color color;

    public Color getColor() {
        return color;
    }
    MyColor(int r, int g, int b) {
        color = Color.rgb(r,g,b);
    }
}