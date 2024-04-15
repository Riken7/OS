import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FCFS {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 0, 10));
        processes.add(new Process(2, 1, 6));
        processes.add(new Process(3, 3, 2));

        processes.sort(Comparator.comparingInt(p -> p.burstTime));
        float arrivalTime = 0;
        float finishTime = 0;
        float turnAroundTime = 0;
        float waitingTime = 0;
        float avgTurnAroundTime = 0;
        float avgWaitingTime = 0;
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
        this.burstTime = burstTime;}
}