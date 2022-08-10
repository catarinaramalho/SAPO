package sapo.pessoa;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;


public class teste {
    public static void main(String[] args) {
    	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	Date date = new Date();
    	System.out.println(dateFormat.format(date));
 
    }
}
