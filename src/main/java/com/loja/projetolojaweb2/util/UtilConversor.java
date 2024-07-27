package com.loja.projetolojaweb2.util;

public class UtilConversor {

    public static Long convertToLong(String num){
        if(isNumeric(num)){
            //System.out.println("entrou");
            return Long.parseLong(num);
        }
        return null;
    }

    public static int convertToInt(String num){
        if(isNumeric(num)) {
            return Integer.parseInt(num);
        }
        //Arrumar esse return
        return 0;
    }

    public static  boolean isNumeric(String strNumber){
        if(strNumber == null) return false;

        return strNumber.matches("^[0-9]+$");
    }

}
