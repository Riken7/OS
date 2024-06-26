import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class PageReplacement {

    public static void main(String[] args) {        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of frames: ");
        int frames = sc.nextInt();
        System.out.println("Enter the number of pages: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.println("Enter the pages: ");
        for(int i=0 ; i<n ; i++){
            pages[i] = sc.nextInt();
        }
        System.out.println("Select the page replacement algorithm: ");
        System.out.print("1. FIFO \n2. Optimal \n3. LRU \n4. LFU \n");
        int choice = sc.nextInt();
        switch(choice){
             case 1:
                 FIFO(pages, frames);
                 break;
             case 2:
                 Optimal(pages, frames);
                 break;
             case 3:
                 LRU(pages, frames);
                 break;
             case 4:
                 LFU(pages, frames);
                 break;
             default:
                 System.out.println("Invalid choice");
         }
    }
    //finish the code here
    public static void FIFO(int[] pages , int frames){
        ArrayList<Integer> frameList = new ArrayList<>(frames);
        ArrayList<String> hitOrMissList = new ArrayList<>();
        int pageFaults = 0;
        int pageHit = 0;
        int i=0;
        System.out.println("Page Replacement Algorithm: FIFO");
        System.out.println("-----------------------------------");
        System.out.println("F1  F2  F3\t\tHit/Miss");
        System.out.println("-----------------------------------");

        while(frameList.size()!=frames){
            if(frameList.contains(pages[i])) {
                hitOrMissList.add("Hit");
                pageHit++;
            } else {
                frameList.add(pages[i]);
                hitOrMissList.add("Miss");
                pageFaults++;
            }
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X   ");
            }
            System.out.println("\t\t" + hitOrMissList.get(i));
            i++;
        }
        int pointer = 0;
        for(;i<pages.length;i++){
            if(frameList.contains(pages[i])){
                hitOrMissList.add("Hit");
                pageHit++;
            }else{
                hitOrMissList.add("Miss");
                pageFaults++;
                frameList.remove(pointer);
                frameList.add(pointer,pages[i]);
                pointer = (pointer+1)%frames;
            }
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            
            System.out.println("\t\t" + hitOrMissList.get(i));
            
        }
        
        System.out.println("Total page faults: "+pageFaults);
        System.out.println("Total page hits: "+pageHit);

        double hitRatio = ((double)pageHit/pages.length)*100;
        double missRatio = ((double)pageFaults/pages.length)*100;

        System.out.println("Hit Ratio: "+hitRatio+"% \n" + "Miss Ratio: "+missRatio+"%");
    }
    public static void Optimal(int[] pages, int frames){
        ArrayList<Integer> frameList = new ArrayList<>(frames);
        ArrayList<String> hitOrMissList = new ArrayList<>();
        int pageFaults = 0;
        int pageHit = 0;
        int i=0;
        System.out.println("Page Replacement Algorithm: OPTIMAL");
        System.out.println("-----------------------------------");
        System.out.println("F1  F2  F3\t\tHit/Miss");
        System.out.println("-----------------------------------");

        while(frameList.size()!=frames){
            if(frameList.contains(pages[i])) {
                hitOrMissList.add("Hit");
                pageHit++;
            } else {
                frameList.add(pages[i]);
                hitOrMissList.add("Miss");
                pageFaults++;
            }
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X   ");
            }
            System.out.println("\t\t" + hitOrMissList.get(i));
            i++;
        }
        int pointer = 0;           
        
        for(;i<pages.length;i++){
            int breakPoint = 0;
            int j=i;
            if(frameList.contains(pages[i])){
                hitOrMissList.add("Hit");
                pageHit++;
            }else{
                ArrayList<Integer> temp = new ArrayList<>();
                while(j<pages.length){
                    if(frameList.contains(pages[j]) && !temp.contains(pages[j])){
                        temp.add(pages[j]);
                        ++breakPoint;
                        if(breakPoint==frames-1){
                            break;
                        }
                    }
                    j++;
                }
            
                for(int k=0;k<frameList.size();k++){
                    if(!temp.contains(frameList.get(k))){
                        pointer = k;
                        break;
                    }
                }
                hitOrMissList.add("Miss");
                pageFaults++;
                frameList.remove(pointer);
                frameList.add(pointer,pages[i]);
            }
            for(int k=0;k<frameList.size();k++){
                System.out.print(frameList.get(k)+"   ");
            }
            
            System.out.println("\t\t" + hitOrMissList.get(i));
        }
        System.out.println("Total page faults: "+pageFaults);
        System.out.println("Total page hits: "+pageHit);

        double hitRatio = ((double)pageHit/pages.length)*100;
        double missRatio = ((double)pageFaults/pages.length)*100;

        System.out.println("Hit Ratio: "+hitRatio+"% \n" + "Miss Ratio: "+missRatio+"%");
    
    }
    public static void LRU(int[] pages,int frames){
        ArrayList<Integer> frameList = new ArrayList<>(frames);
        ArrayList<String> hitOrMissList = new ArrayList<>();
        ArrayList<Integer> counter = new ArrayList<>(frames);
        int pageFaults = 0;
        int pageHit = 0;
        int i=0;
        System.out.println("Page Replacement Algorithm: LRU");
        System.out.println("-----------------------------------");
        System.out.println("F1  F2  F3\t\tHit/Miss");
        System.out.println("-----------------------------------");
        while(frameList.size()!=frames){
            if(frameList.contains(pages[i])) {
                hitOrMissList.add("Hit");
                pageHit++;
            } else {
                frameList.add(pages[i]);
                hitOrMissList.add("Miss");
                pageFaults++;
                counter.add(pages[i]);
            }
            
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X   ");
            }
            System.out.println("\t\t" + hitOrMissList.get(i));
            i++;
        }
        int pointer = 0;
        for(;i<pages.length;i++){
            if(frameList.contains(pages[i])){
                hitOrMissList.add("Hit");
                pageHit++;
                counter.remove(counter.indexOf(pages[i]));
                counter.add(pages[i]);
                
            }else{
                hitOrMissList.add("Miss");
                pageFaults++;
                pointer = frameList.indexOf(counter.remove(0));
                counter.add(pages[i]);
                frameList.remove(pointer);  
                frameList.add(pointer,pages[i]); 
                
            }
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            
            System.out.println("\t\t" + hitOrMissList.get(i));
            
        }
        
        System.out.println("Total page faults: "+pageFaults);
        System.out.println("Total page hits: "+pageHit);

        double hitRatio = ((double)pageHit/pages.length)*100;
        double missRatio = ((double)pageFaults/pages.length)*100;

        System.out.println("Hit Ratio: "+hitRatio+"% \n" + "Miss Ratio: "+missRatio+"%");
    }
    
    public static void LFU(int[] pages, int frames){
        ArrayList<Integer> frameList = new ArrayList<>(frames);
        ArrayList<String> hitOrMissList = new ArrayList<>();
        HashMap<Integer,Integer> freqMap = new HashMap<>();
        int pageFaults = 0;
        int pageHit = 0;
        int i=0,flag= 0 ;
        System.out.println("Page Replacement Algorithm: LFU");
        System.out.println("----------------------------------------");
        System.out.println("F1  F2  F3  F4\t\t\tHit/Miss");
        System.out.println("----------------------------------------");
        
        while (frameList.size() != frames) {
            if(frameList.contains(pages[i])) {
                hitOrMissList.add("Hit");
                pageHit++;
                freqMap.put(pages[i], freqMap.getOrDefault(pages[i], 0) + 1);
            } else {
                frameList.add(pages[i]);
                hitOrMissList.add("Miss");
                pageFaults++;
                freqMap.put(pages[i], freqMap.getOrDefault(pages[i], 0) + 1);
            }
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X   ");
            }
            System.out.println("\t\t" + hitOrMissList.get(i));

            i++;
        }
        while(i < pages.length) {
            if(frameList.contains(pages[i])) {
                pageHit++;
                hitOrMissList.add("Hit");
                freqMap.put(pages[i], freqMap.getOrDefault(pages[i], 0) + 1);
            } else {
                int minFreq = getLeastFreq(freqMap, frameList);
                while(freqMap.get(pages[flag]) != minFreq) {
                    flag++;
                }
                int index = frameList.indexOf(pages[flag]);
                freqMap.put(pages[flag], 0);
                frameList.remove(index);
                frameList.add(index, pages[i]);
                freqMap.put(pages[i], freqMap.getOrDefault(pages[i], 0) + 1);
                
                flag++;
                pageFaults++;
                hitOrMissList.add("Miss");
            }
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"   ");
            }
            
            System.out.println("\t\t" + hitOrMissList.get(i));
            
            i++;
        }


        System.out.println("Total page faults: "+pageFaults);
        System.out.println("Total page hits: "+pageHit);

        double hitRatio = ((double)pageHit/pages.length)*100;
        double missRatio = ((double)pageFaults/pages.length)*100;

        System.out.println("Hit Ratio: "+hitRatio+"% \n" + "Miss Ratio: "+missRatio+"%");
    }
    public static int getLeastFreq(HashMap<Integer, Integer> freqMap, ArrayList<Integer> frameList) {
        int minFreq = Integer.MAX_VALUE;
        for(int i = 0; i < frameList.size(); i++) {
            if(freqMap.get(frameList.get(i)) < minFreq) {
                minFreq = freqMap.get(frameList.get(i));
            }
        }
        return minFreq;
    }
}
