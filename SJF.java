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
        List<Process> temp = new ArrayList<>(size);
        sc.close();
        float currentTime = 0,finishTime = 0;
        float turnAroundTime = 0,waitingTime = 0;
        float avgTurnAroundTime = 0,avgWaitingTime = 0;
        for(int k = 0 ; k<processes.size(); k++ ){
            System.out.println("Process " + processes.get(k).pid + " : Arrival Time : " + processes.get(k).arrivalTime + " Burst Time : " + processes.get(k).burstTime);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("|PID\t|Arrival|Burst\t|Finish\t|Turn Around\t|Waiting\t|");
        System.out.println("-----------------------------------------------------------------");
        while(!processes.isEmpty() || !temp.isEmpty()){
            for(int k=0 ; k<processes.size(); k++){
                Process add = processes.remove(k);
                if(add.arrivalTime <= currentTime){
                    temp.add(add);
                    // temp.sort(Comparator.comparingInt((Process p) -> p.arrivalTime).thenComparingInt(p -> p.burstTime));
                    temp.sort(Comparator.comparingInt(p -> p.burstTime));
                }else{
                    processes.add(k,add);
                }
            }
            if(temp.isEmpty()){
                currentTime++;
                continue;
            }
            Process current = temp.remove(0);
            finishTime = currentTime + current.burstTime;
            turnAroundTime = finishTime - current.arrivalTime;
            waitingTime = turnAroundTime - current.burstTime;
            System.out.println("|"+current.pid + "\t|" + current.arrivalTime + "\t|" + current.burstTime + "\t|" + finishTime + "\t|" + turnAroundTime + "\t\t|" + waitingTime + "\t\t|");
            avgTurnAroundTime += turnAroundTime; 
            avgWaitingTime += waitingTime; 
            currentTime = finishTime;
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