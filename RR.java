import java.util.*;
public class RR {
    public static void main(String[] args) {
    System.out.println("Enter the number of processes : ");
    Scanner sc = new Scanner(System.in);
    int size = sc.nextInt();

    List<Process> processes = new ArrayList<>(size);
    for(int i = 0 ; i < size ; i++){
        System.out.println("Enter the arrival time and burst time of process " + (i+1) + " : ");
        int arrivalTime = sc.nextInt();
        int burstTime = sc.nextInt();
        processes.add(new Process(i+1, arrivalTime, burstTime));
    }
    System.out.println("Enter the quantum time : ");
    int qt = sc.nextInt();
    System.out.println("Enter the context switch time : ");
    int csgo = sc.nextInt();
    sc.close();
    float finishTime = 0 , currentTime = 0;
    float turnAroundTime = 0,waitingTime = 0;
    float avgTurnAroundTime = 0,avgWaitingTime = 0;
    processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        
    Queue<Process> queue = new LinkedList<>();
    System.out.println("-----------------------------------------------------------------");
    System.out.println("|PID\t|Arrival|Burst\t|Finish\t|Turn Around\t|Waiting\t|");
    System.out.println("-----------------------------------------------------------------");
    while(!queue.isEmpty() || !processes.isEmpty()){
        while(!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime){
            queue.add(processes.remove(0));
        }
        if(!queue.isEmpty()){
            Process current = queue.poll();
                if(current.burstTime > qt){
                    currentTime += qt;
                    current.burstTime -= qt;
                    while(!processes.isEmpty() && processes.get(0).arrivalTime <= currentTime){
                        queue.add(processes.remove(0));
                    }
                    queue.add(current);
                    currentTime += csgo;
                }
                else{
                    currentTime += current.burstTime;
                    
                    finishTime = currentTime;
                    
                    turnAroundTime = finishTime - current.arrivalTime;
                    waitingTime = turnAroundTime - current.bTime;
                    System.out.println("|"+current.pid + "\t|" + current.arrivalTime + "\t|" + current.bTime + "\t|" + finishTime + "\t|" + turnAroundTime + "\t\t|" + waitingTime + "\t\t|");
                    avgTurnAroundTime += turnAroundTime; 
                    avgWaitingTime += waitingTime; 
                    currentTime += csgo;
                }
            }else{
                currentTime++;
            }
    }
        avgTurnAroundTime /= size;
        avgWaitingTime /= size;

        System.out.println("\nAverage Turn Around Time : " + avgTurnAroundTime + "\nAverage Waiting Time : " + avgWaitingTime);

}
    
}
class Process{
    int pid;
    int arrivalTime; 
    int burstTime; 
    final int bTime;
    public Process(int pid , int arrivalTime , int burstTime){
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.bTime = burstTime;
    }
}
