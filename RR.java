import java.util.*;
public class RR {
    public static void main(String[] args) {
    List<Process> processes = new ArrayList<>();
    // processes.add(new Process(1,0,10));
    // processes.add(new Process(2,1,2));
    // processes.add(new Process(3,1,3));
    // processes.add(new Process(4,2,1));
    // processes.add(new Process(5,2,2));
    // //let's say Quantum time is 4ms and context switch overhead i0s 1ms
    processes.add(new Process(1, 0, 10));
    processes.add(new Process(2, 0, 6));
    processes.add(new Process(3, 0, 2));
    processes.add(new Process(4, 0, 4));
    processes.add(new Process(5, 0, 8));
    int qt = 2 , csgo = 0;
    int size = processes.size();
    float arrivalTime = 0,finishTime = 0;
    float turnAroundTime = 0,waitingTime = 0;
    float avgTurnAroundTime = 0,avgWaitingTime = 0;
    
    Queue<Process> queue = new LinkedList<>(processes);
    while(!queue.isEmpty()){
        Process current = queue.poll();

        if(current.arrivalTime > arrivalTime){
            queue.add(current);
        }
        else{
            if(current.burstTime > qt){
                current.burstTime -= qt;
                arrivalTime += qt + csgo;
                queue.add(current);
            }
            else{
                finishTime = arrivalTime + current.burstTime;
                turnAroundTime = finishTime - current.arrivalTime;
                waitingTime = turnAroundTime - current.bTime; // since burstTime was changing so we stored it in bTime which is constant 
                System.out.println("---Process " + current.pid + "---");
                System.out.println("Arrival Time : " + current.arrivalTime);
                System.out.println("Burst Time : " + current.burstTime);
                System.out.println("Finish Time : " + finishTime);
                System.out.println("Turn Around Time : " + turnAroundTime);
                System.out.println("Waiting Time : " + waitingTime + "\n");

                avgTurnAroundTime += turnAroundTime; 
                avgWaitingTime += waitingTime; 

                arrivalTime = finishTime + csgo;
            }
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
    int qt;
    final int bTime;
    public Process(int pid , int arrivalTime , int burstTime){
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.bTime = burstTime;
    }
}
