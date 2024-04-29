import java.util.*;
public class SJF {
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
        sc.close();
        float arrivalTime = 0,finishTime = 0;
        float turnAroundTime = 0,waitingTime = 0;
        float avgTurnAroundTime = 0,avgWaitingTime = 0;
        processes.sort(Comparator.comparingInt(p -> p.burstTime));
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|PID\t|Arrival|Burst\t|Finish\t|Turn Around\t|Waiting\t|");
        System.out.println("-----------------------------------------------------------------");
    
        while(!processes.isEmpty()){
            
            Process current = processes.remove(0);
            if(current.arrivalTime > arrivalTime){ 
                processes.add(current);
                processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
                arrivalTime = processes.get(0).arrivalTime; // Advance the time to the arrival of the earliest arriving process
    
            }
            else{
                finishTime = arrivalTime + current.burstTime;
                turnAroundTime = finishTime - current.arrivalTime;
                waitingTime = turnAroundTime - current.burstTime;
                System.out.println("|"+current.pid + "\t|" + current.arrivalTime + "\t|" + current.burstTime + "\t|" + finishTime + "\t|" + turnAroundTime + "\t\t|" + waitingTime + "\t\t|");
                avgTurnAroundTime += turnAroundTime; 
                avgWaitingTime += waitingTime; 

                arrivalTime = finishTime;
                processes.sort(Comparator.comparingInt(p -> p.burstTime));
            }
        }
        avgTurnAroundTime /= size;
        avgWaitingTime /= size;

        System.out.println("\nAverage Turn Around Time : " + avgTurnAroundTime + "\nAverage Waiting Time : " + avgWaitingTime);
    }
    
}
class Process{
    int pid;
    int arrivalTime; //Atime
    int burstTime; //Btime

    public Process(int pid , int arrivalTime , int burstTime){
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}