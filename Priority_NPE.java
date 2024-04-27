import java.util.*;
//lower priority value means higher priority
public class Priority_NPE {
    public static void main(String[] args) {

        System.out.println("Enter the number of processes : ");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        
        List<Process> processes = new ArrayList<>(size);
        for(int i = 0 ; i < size ; i++){
            System.out.println("Enter the arrival time, burst time and priority of process " + (i+1) + " : ");
            int arrivalTime = sc.nextInt();
            int burstTime = sc.nextInt();
            int priority = sc.nextInt();
            processes.add(new Process(i+1, arrivalTime, burstTime, priority));
        }
        sc.close();

        float arrivalTime = 0,finishTime= 0;
        float turnAroundTime = 0,waitingTime = 0;
        float avgTurnAroundTime = 0,avgWaitingTime = 0;
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|PID\t|Arrival|Burst\t|Priority|Finish|Turn Around\t|Waiting\t|");
        System.out.println("-------------------------------------------------------------------------");
    
        processes.sort(Comparator.comparingInt((Process p) -> p.arrivalTime).thenComparingInt(p -> p.priority));
        while(!processes.isEmpty()){
            Process current = processes.remove(0);
            if(current.arrivalTime > arrivalTime){
                processes.add(current);
                arrivalTime++;
            }
            else {
                finishTime = arrivalTime + current.burstTime;
                turnAroundTime = finishTime - current.arrivalTime;
                waitingTime = turnAroundTime - current.burstTime;
                System.out.println("|"+current.pid + "\t|" + current.arrivalTime + "\t|" + current.burstTime + "\t|"+ current.priority + "\t  |" + finishTime + "\t|" + turnAroundTime + "\t\t|" + waitingTime + "\t\t|");
                avgTurnAroundTime += turnAroundTime; 
                avgWaitingTime += waitingTime; 

                arrivalTime = finishTime;
                processes.sort(Comparator.comparingInt((Process p) -> p.priority));
                
            }
        }
        avgTurnAroundTime /= size;
        avgWaitingTime /= size;

        System.out.println("Average Turn Around Time : " + avgTurnAroundTime + "\nAverage Waiting Time : " + avgWaitingTime);
    }
    
}

class Process{
    int pid;
    int arrivalTime; 
    int burstTime; 
    int priority;

    public Process(int pid , int arrivalTime , int burstTime, int priority){
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}
