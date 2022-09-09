package administration;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Transformer {
    public Color stringToColor(String colorAsString){
        colorAsString = colorAsString.toLowerCase();
        Color colorFromString;
        switch (colorAsString){
            case "rot":{colorFromString = Color.red;break;}
            case "grün":{colorFromString = Color.green;break;}
            case "blau":{colorFromString = Color.blue;break;}
            case "schwarz":{colorFromString = Color.black;break;}
            case "weiß":{colorFromString = Color.white;break;}
            case "gelb":{colorFromString = Color.yellow;break;}
            case "grau":{colorFromString = Color.gray;break;}
            default: throw new InputMismatchException("Die Farbe konnte nicht gefunden werden!");
        }
        return colorFromString;
    }

    public String colorToString(Color color){
        String colorAsString;
        if (Color.red.equals(color)) {colorAsString = "rot";}
        else if (Color.green.equals(color)) {colorAsString = "grün";}
        else if (Color.blue.equals(color)) {colorAsString = "blau";}
        else if (Color.black.equals(color)) {colorAsString = "schwarz";}
        else if (Color.white.equals(color)) {colorAsString = "weiß";}
        else if (Color.yellow.equals(color)) {colorAsString = "gelb";}
        else if (Color.gray.equals(color)) {colorAsString = "grau";}
        else {throw new InputMismatchException("Die Farbe konnte nicht gefunden werden!");}
        return colorAsString;
    }



    public Size stringToSize(String sizeAsString){
        sizeAsString = sizeAsString.toLowerCase();
        Size sizeFromString;
        switch (sizeAsString){
            case "xl":{sizeFromString = Size.XL;break;}
            case "l":{sizeFromString = Size.L;break;}
            case "m":{sizeFromString = Size.M;break;}
            default: throw new InputMismatchException("Die Größe konnte nicht gefunden werden!");
        }
        return sizeFromString;
    }

    public SystemOfDrive stringToSystemOfDrive(String systemAsString){
        systemAsString = systemAsString.toLowerCase();
        SystemOfDrive systemFromString;
        switch (systemAsString){
            case "1":{systemFromString = SystemOfDrive.DIESELMOTOR;break;}
            case "2":{systemFromString = SystemOfDrive.BENZINMOTOR;break;}
            case "3":{systemFromString = SystemOfDrive.ELEKTROMOTOR;break;}
            case "4":{systemFromString = SystemOfDrive.KEROSINMOTOR;break;}
            case "5":{systemFromString = SystemOfDrive.GRUENE_ENERGIE;break;}
            default: throw new InputMismatchException("Diese Antriebsart konnte nicht gefunden werden!");
        }
        return systemFromString;
    }

    public Date stringToDate(String dateAsString) {
        Date date;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            date = format.parse(dateAsString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }

}
