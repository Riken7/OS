import java.util.*;
//Low priority number means high priority
public class Priority_NPE {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1,0,10,5));
        processes.add(new Process(2,1,6,4));
        processes.add(new Process(3,3,2,2));
        processes.add(new Process(4,5,4,0));
        
        int size = processes.size();
        float arrivalTime = 0,finishTime= 0;
        float turnAroundTime = 0,waitingTime = 0;
        float avgTurnAroundTime = 0,avgWaitingTime = 0;

        processes.sort(Comparator.comparingInt(p -> p.priority));
        while(!processes.isEmpty()){
            Process current = processes.remove(0);
            if(current.arrivalTime > arrivalTime){
                processes.add(current);
            }
            else {
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
                processes.sort(Comparator.comparingInt(p -> p.priority));
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
