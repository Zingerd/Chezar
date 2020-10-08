package com.company;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args)throws FileNotFoundException {

        String path="text.txt";
        File file=new File (path);
        Scanner scanner = new Scanner(file);
        Scanner input = new Scanner(System.in);

        System.out.println("Key encrypt :");
        int key = input.nextInt();
        String line="";

        while (scanner.hasNext()) {
            line+=scanner.nextLine();
        }

        String output=Chezar(line,key);
        outChezar("Encrypted code :\n"+output);
        System.out.println(Chezar(line, key));


        System.out.println(deshif(output,key));
        String out=deshif(output,key);
        outDeshif(out);
        scanner.close();
        frequency_analysis(output);
    }

    public static String Chezar(String msg, int shift) {
        String s = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = (char) (msg.charAt(i) + shift%33);

            if (c > 'я')
                s += (char) (msg.charAt(i) - (33 % shift));
            else
                s += (char) (msg.charAt(i) + shift);
        }
        return s;
      }

    public  static String outChezar(String msg){
          try(FileWriter fw= new FileWriter("Chezar.txt")){
              for(int i=0;i<msg.length();i++) {
                  fw.write(msg.charAt(i));
              }
          } catch (IOException ex) {
              ex.printStackTrace(System.out);
          }
          return  "";
      }

    public static String deshif(String msg, int shift) {
        String s = "";

        for (int i = 0; i < msg.length(); i++) {
            char c = (char) (msg.charAt(i) + shift%33);


            if (c > 'я')
                s += (char) (msg.charAt(i) + (33 %shift));


            else
                s += (char) (msg.charAt(i) - shift);
        }
        return s;
    }

    public  static void outDeshif(String msg) {
        try(FileWriter fw= new FileWriter("Deshifrator.txt")) {
            for(int i=0;i<msg.length();i++) {
                fw.write(msg.charAt(i));
            }
        } catch (IOException ex){
            ex.printStackTrace(System.out);
        }
    }

public static  String frequency_analysis(String text) {
    text = text.toLowerCase();
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < text.length(); i++) {
        char ch = text.charAt(i);

        if (ch >= 'а' && ch <= 'я') {
            map.compute(ch, ((character, integer)
                    -> integer == null ? 1 : integer + 1));
        }
    }
    ArrayList<Map.Entry<Character, Integer>> entries =
            new ArrayList<>(map.entrySet());
    entries.sort((o1, o2) -> Character.compare(o1.getKey(), o2.getKey()));
    for (Map.Entry<Character, Integer> entry : entries) {
        System.out.println(entry.getKey() + " :" + (double)entry.getValue());//(text.length()-1));

    }
    return text;
 }
}




