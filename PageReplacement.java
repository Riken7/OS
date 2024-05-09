import java.util.ArrayList;
public class PageReplacement {

    public static void main(String[] args) {
        int[] pages = {4, 7 , 6 , 1 , 7 , 6 , 1  , 2 ,7 ,2 };
        // int[] pages = {5 , 0 , 1 , 2 , 0 , 3 , 2 , 0 , 3 , 4 , 1 , 0 , 5 , 0 ,4,3,2,1,2,0,1 };
        int frames = 3;

        // FIFO(pages, frames);
        // Optimal(pages, frames);
        LRU(pages, frames);
    }
    public static void FIFO(int[] pages , int frames){
        ArrayList<Integer> frameList = new ArrayList<>(frames);
        ArrayList<String> hitOrMissList = new ArrayList<>();
        int pageFaults = 0;
        int pageHit = 0;
        int i=0;
        System.out.println("Page Replacement Algorithm: FIFO");
        System.out.println("-----------------------------------");
        System.out.println("Frame List\t\tHit/Miss");
        System.out.println("-----------------------------------");

        while(frameList.size()!=frames){
            frameList.add(pages[i]);
            hitOrMissList.add("Miss");
            pageFaults++;
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"  ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X  ");
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
                System.out.print(frameList.get(j)+"  ");
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
        System.out.println("Page Replacement Algorithm: FIFO");
        System.out.println("-----------------------------------");
        System.out.println("Frame List\t\tHit/Miss");
        System.out.println("-----------------------------------");

        while(frameList.size()!=frames){
            frameList.add(pages[i]);
            hitOrMissList.add("Miss");
            pageFaults++;
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"  ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X  ");
            }
            System.out.println("\t\t" + hitOrMissList.get(i));
            i++;
        }
        int pointer = 0;
        int j=i;
        int breakPoint = 0;
        for(;i<pages.length;i++){
            if(frameList.contains(pages[i])){
                hitOrMissList.add("Hit");
                pageHit++;
            }else{
                while(j<pages.length){
                    if(frameList.contains(pages[j])){
                        pointer = frameList.indexOf(pages[j]);
                        breakPoint++;
                        pointer = (pointer+1)%frames;
                        if(breakPoint==frames-1){
                            break;
                        }
                    }
                    j++;
                }
                hitOrMissList.add("Miss");
                pageFaults++;
                frameList.remove(pointer);
                frameList.add(pointer,pages[i]);
                pointer = (pointer+1)%frames;

            }
            for(int k=0;k<frameList.size();k++){
                System.out.print(frameList.get(k)+"  ");
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
        System.out.println("Page Replacement Algorithm: FIFO");
        System.out.println("-----------------------------------");
        System.out.println("Frame List\t\tHit/Miss");
        System.out.println("-----------------------------------");

        while(frameList.size()!=frames){
            frameList.add(pages[i]);
            hitOrMissList.add("Miss");
            pageFaults++;
            counter.add(pages[i]);
            for(int j=0;j<frameList.size();j++){
                System.out.print(frameList.get(j)+"  ");
            }
            for(int j=0;j<frames-frameList.size();j++){
                System.out.print("X  ");
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
                System.out.print(frameList.get(j)+"  ");
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
        
    }
}
