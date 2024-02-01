package org.example;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("1. Реализуйте метод, который находит из трех чисел то, которое делится на 2 \n" +
                "остальных; или возвращает -1, если такого нет");
        Scanner sc = new Scanner(System.in);
       int [] numberUno = new int[3];
        for (int i = 0; i < numberUno.length; i++){
          numberUno[i] = sc.nextInt();
        }
        searchNumber(numberUno);

        ////////////////
        System.out.println("2. Реализуйте метод, который из двух массивов строк собирает третий, в \n" +
                "котором чередуются элементы первых двух");
      String strUno [] = new String[]{"Однажды", "студеную", "пору" };
      String strDos [] = new String[]{"в", "зимнюю", ""};
      int counter = 0;
      int counter2 = 0;
      String[] strResult = sumString(strUno, strDos, counter, counter2);

        for (int i = 0; i < strResult.length; i++){
          System.out.print(strResult[i]+ " ");
      }

        //////////////////
        System.out.println(" \n3. Возьмите любую программу, которую вы писали до этого.. какая сердцу \n" +
                "ближе. Отрефакторите ее (улучшите читабельность кода) - путем разбиения \n" +
                "крупных частей на методы поменьше. Стало лучше? (надеюсь, да)");
        ///////////////////
        System.out.println("4. Отрефакторите(улучшите читабельность) кода вашей реализации анализа \n" +
                 "курса валют. ");
        /////////////////
        System.out.println("5. Реализуйте метод, который возвращает случайную цитату Уолтера Уайта.\n");
      do {
          String result = getString();
        if(result.contains("Walter White")){
            System.out.println(result);
            break;  }
      }while (true);

        ///////////////////
        System.out.println("6. Реализуйте метод, который выводит explanation сегодняшнего снимка дня \n" +
                          "NASA");
        String key = "WgH82FSmI6M04geem07EWObXt2MbUuZGM2dmfYTg";
        String date = getDate();
        System.out.println("Сегодня " + date);
        String explanation = getStringDate(key, date);
      System.out.println(explanation);

        //////////////////
        System.out.println("7. Реализуйте метод, который возвращает explanation снимка дня NASA, в \n"+
                "качестве параметра принимайте LocalDate - дату, на которую нужно брать \n"+
                "снимок");

        LocalDate localDate = LocalDate.now(); // получаем текущую дату
        String date1 = localDate.toString();
        String explanation1 = getStringDate(key, date1);
        System.out.println("Сегодня " + date1);
        System.out.println(explanation1);

        //////////////////
        System.out.println("8. Реализуйте метод, который принимает два LocalDate, и сохраняет все \n" +
                "снимки дня NASA в указанный промежуток");
       String dateK = Arrays.toString(getBetweenDate());
        String dateX = extractedDate(dateK);
        safeImage(key, dateX);
        System.out.println("Готово!");
    }

    private static void safeImage(String key, String dateX) throws IOException {
        String []dates = dateX.split(" ");
        for (int i = 0; i < 7; i++){
            String date = dates[i];
            String page = downloadWebPage("https://api.nasa.gov/planetary/apod?api_key=" + key + "&date=" + date);
            int urlBegin = page.lastIndexOf("url");
            int urlEnd = page.lastIndexOf("}");
            String url = page.substring(urlBegin + 6, urlEnd - 1);

            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(date));
        }
    }

    private static String extractedDate(String date) {
        String date0 = date.replace("]", "");
        String date1 = date0.replace("[", "");
        String date2 = date1.replace(",", "");
        return date2;
    }

    private static String[] getBetweenDate() {
        String[] dates = new String[5];
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = startDate.minusDays(7);
            dates = new String[]{endDate.datesUntil(startDate).toList().toString()};
            return dates;
    }
    private static String getStringDate(String key, String date) throws IOException {
        String page = downloadWebPage("https://api.nasa.gov/planetary/apod?api_key=" + key + "&date=" + date);
        int urlBegin = page.lastIndexOf("explanation");
        int urlEnd = page.lastIndexOf("hdurl");
        String explanation = page.substring(urlBegin + 13, urlEnd - 2);
        return explanation;
    }
    private static String getDate() {
        LocalDate localDate = LocalDate.now(); // получаем текущую дату
        String date = localDate.toString();
        String data = date.toString();
        return data;
    }
    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (
                InputStream is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
    private static String downloadWEbPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        var urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
    private static String getString() throws IOException {
        String page = downloadWebPage("https://api.breakingbadquotes.xyz/v1/quotes");
        int quoteStart = page.lastIndexOf("quote");
        int quoteEnd = page.indexOf("author");
        int author_4 = page.indexOf("\"}]");
        String quote = page.substring(quoteStart + 7, quoteEnd - 2);
        String author = page.substring(quoteEnd + 9, author_4);
        return quote + author;
    }

    private static String[] sumString(String[] strUno, String[] strDos, int counter, int counter2) {
        String strResult[] = new String[strUno.length + strDos.length];
        for (int i = 0; i < strResult.length; i += 2) {
            strResult[i] = strUno[counter];
            counter++;
        }
        for (int i = 0; i < strResult.length; i += 2) {
            strResult[i + 1] = strDos[counter2];
            counter2++;
        }
        return strResult;
    }

    private static void searchNumber(int[] numberUno) {
        if ((numberUno[0] % numberUno[1] == 0) && (numberUno[0] % numberUno[2] == 0)) {
            System.out.println(numberUno[0]);
        } else if ((numberUno[1] % numberUno[2] == 0) && (numberUno[1] % numberUno[0] == 0)) {
            System.out.println(numberUno[1]);
        } else if ((numberUno[2] % numberUno[0] == 0) && (numberUno[2] % numberUno[1] == 0)) {
            System.out.println(numberUno[2]);
        } else {
            System.out.println(-1);
        }
    }

}
