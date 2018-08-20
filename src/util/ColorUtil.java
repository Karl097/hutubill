package util;

import java.awt.*;

//与GUIUtil一样，关于颜色也有很多重用的地方，都会被抽象到一个类里面来，方便共用
public class ColorUtil {
    public static Color blueColor=Color.decode("#3399FF");//淡蓝色
    public static Color grayColor=Color.decode("#999999");//灰色
    public static Color backgroundColor=Color.decode("#eeeeee");//背景色
    public static Color warningColor=Color.decode("#FF3333");//警告红色

    //根据百分比设置颜色
    public static Color getByPercentage(int per){
        if (per>100)
            per=100;
        int r = 51;
        int g = 255;
        int b = 51;
        float rate = per / 100f;
        r = (int) ((255 - 51) * rate + 51);
        g = 255 - r + 51;
        Color color = new Color(r, g, b);
        return color;
    }
}
