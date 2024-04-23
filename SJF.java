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
        // processes.add(new Process(1, 2, 6));
        // processes.add(new Process(2, 5, 2));
        // processes.add(new Process(3, 1, 8));
        // processes.add(new Process(4, 0, 3));
        // processes.add(new Process(5, 4, 4));
        float arrivalTime = 0,finishTime = 0;
        float turnAroundTime = 0,waitingTime = 0;
        float avgTurnAroundTime = 0,avgWaitingTime = 0;
        processes.sort(Comparator.comparingInt(p -> p.burstTime));
    
        while(!processes.isEmpty()){
            
            Process current = processes.remove(0);
            if(current.arrivalTime > arrivalTime){ 
                processes.add(current);
            }
            else{
                finishTime = arrivalTime + current.burstTime;
                turnAroundTime = finishTime - current.arrivalTime;
                waitingTime = turnAroundTime - current.burstTime;
                System.out.println("---Process " + current.pid + "---");
                System.out.println("Arrival Time : " + current.arrivalTime);
                System.out.println("Burst Time : " + current.burstTime);
                System.out.println("Finish Time : " + finishTime);
                System.out.println("Turn Around Time : " + turnAroundTime);
                System.out.println("Waiting Time : " + waitingTime + "\n");

                avgTurnAroundTime += turnAroundTime; 
                avgWaitingTime += waitingTime; 

                arrivalTime = finishTime;
                processes.sort(Comparator.comparingInt(p -> p.burstTime));
            }
        }
        avgTurnAroundTime /= size;
        avgWaitingTime /= size;

        System.out.println("Average Turn Around Time : " + avgTurnAroundTime + "\nAverage Waiting Time : " + avgWaitingTime);

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