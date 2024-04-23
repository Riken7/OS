import java.util.*;
public class RR {
    public static void main(String[] args) {
    List<Process> processes = new ArrayList<>();
    // processes.add(new Process(1,0,6));
    // processes.add(new Process(2,1,2));
    // processes.add(new Process(3,1,3));
    // processes.add(new Process(4,2,1));
    // processes.add(new Process(5,2,2));

    processes.add(new Process(1, 5, 5));
    processes.add(new Process(2, 4, 6));
    processes.add(new Process(3, 3, 7));
    processes.add(new Process(4, 1, 9));
    processes.add(new Process(5, 2, 2));
    processes.add(new Process(6, 6, 3));
    int qt = 3 , csgo = 0; 
    int size = processes.size();
    float finishTime = 0 , currentTime = 0;
    float turnAroundTime = 0,waitingTime = 0;
    float avgTurnAroundTime = 0,avgWaitingTime = 0;
    processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        
    Queue<Process> queue = new LinkedList<>();
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
                    
                }
                else{
                    currentTime += current.burstTime;
                    finishTime = currentTime;
                    
                    turnAroundTime = finishTime - current.arrivalTime;
                    waitingTime = turnAroundTime - current.bTime; // since burstTime was changing so we stored it in bTime which is constant 
                    System.out.println("---Process " + current.pid + "---");
                    System.out.println("Arrival Time : " + current.arrivalTime);
                    System.out.println("Burst Time : " + current.bTime);
                    System.out.println("Finish Time : " + finishTime);
                    System.out.println("Turn Around Time : " + turnAroundTime);
                    System.out.println("Waiting Time : " + waitingTime + "\n");

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

        System.out.println("Average Turn Around Time : " + avgTurnAroundTime + "\nAverage Waiting Time : " + avgWaitingTime);

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
