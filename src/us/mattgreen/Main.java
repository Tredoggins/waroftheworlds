package us.mattgreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();
    private static List<String> myList=new ArrayList<String>();
    private static Integer currentMost=0;
    private static Integer lastMost=0;

    
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]","")
                    .toLowerCase().trim();
           
            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }
            
            // Split string over one or more spaces
            words = line.split(" +");
            
            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                }
                else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                }

            }


        }
        // Loop over entries in the map, getting each key/value pair
        for(int i=0;i<3;i++) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                //System.out.println(entry.getKey() + " " + entry.getValue());
                if(i==0) {
                    if (entry.getValue().equals(currentMost)) {
                        myList.add(entry.getKey());
                    } else if (entry.getValue().compareTo(currentMost)>0) {
                        myList.clear();
                        myList.add(entry.getKey());
                        currentMost = entry.getValue();
                    }
                }
                else{
                    if (entry.getValue().equals(currentMost)) {
                        myList.add(entry.getKey());
                    } else if (entry.getValue().compareTo(currentMost)>0 && entry.getValue().compareTo(lastMost)<0) {
                        myList.clear();
                        myList.add(entry.getKey());
                        currentMost = entry.getValue();
                    }
                }
            }
            switch (i) {
                case 0:
                    System.out.println("\nWord(s) that Appear the Most at " + currentMost + " times:\n");
                    break;
                case 1:
                    System.out.println("\nWord(s) that Appear the Second Most at " + currentMost + " times:\n");
                    break;
                case 2:
                    System.out.println("\nWord(s) that Appear the Third Most at " + currentMost + " times:\n");
                    break;
            }
            for(String s:myList){
                System.out.println(s);
            }
            lastMost=currentMost;
            currentMost=0;
            myList.clear();
        }
        int appearOnce=0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue().equals(1)) {
                //System.out.println(entry.getKey());
                appearOnce++;
            }

        }
        System.out.println("\nNumber of Words that appear only once: "+appearOnce+"\n");
    }
    
}