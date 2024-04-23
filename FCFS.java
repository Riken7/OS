import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 2, 2));
        processes.add(new Process(2, 5, 6));
        processes.add(new Process(3, 0, 4));
        processes.add(new Process(4, 0, 7));
        processes.add(new Process(5, 7, 4));
        processes.add(new Process(6, 3, 5));
        processes.add(new Process(7, 1, 10));

        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        for(Process p : processes){
            System.out.print(p.pid);
        }
        float arrivalTime = 0,finishTime = 0;
        float turnAroundTime = 0, waitingTime = 0;
        float avgTurnAroundTime = 0 , avgWaitingTime = 0;
        for(Process p : processes){
            if(p.arrivalTime > arrivalTime){
                arrivalTime = p.arrivalTime;
            }
            finishTime = arrivalTime + p.burstTime; 
            turnAroundTime = finishTime - p.arrivalTime; 
            waitingTime = turnAroundTime - p.burstTime; 
            System.out.println("---Process " + p.pid + "---");
            System.out.println("Arrival Time : " + p.arrivalTime);
            System.out.println("Burst Time : " + p.burstTime);
            System.out.println("Finish Time : " + finishTime);
            System.out.println("Turn Around Time : " + turnAroundTime);
            System.out.println("Waiting Time : " + waitingTime + "\n");

            avgTurnAroundTime += turnAroundTime; 
            avgWaitingTime += waitingTime; 

            arrivalTime = finishTime;

        }
        avgTurnAroundTime /= processes.size();
        avgWaitingTime /= processes.size();

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