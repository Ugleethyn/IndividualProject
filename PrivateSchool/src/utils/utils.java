/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Ugleethyn
 */
public class utils {
    
        public Scanner _reader = new Scanner(System.in);
    
        //This method is protecting the program from wrong input a dates
    public java.sql.Date correctDate() {
        LocalDate date = null;
        while (true) {
            String inputDate = _reader.nextLine();
            try {
                date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (date.getDayOfMonth() > 31 || date.getMonthValue() > 12) {

                } else {
                    break;
                }
            } catch (DateTimeParseException dtp) {
                System.out.println("Wrong input try again (Format : yyyy-mm-dd");
            }
        }
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        return sqlDate;
    }

    public int protectNumber() {
        int number = 0;
        while (true) {
            String x = _reader.nextLine();
            try {
                number = Integer.parseInt(x);
                if (number < 0) {
                    System.out.println("Negative numbers are not acceptable try again");
                } else {
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Wrong input try again");
            }
        }
        return number;
    }
    
}
