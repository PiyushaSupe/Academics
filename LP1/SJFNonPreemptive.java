import java.util.Scanner;

class SJFNProcess {
    int pid, burstTime, arrivalTime, waitingTime, turnaroundTime;

    public SJFNProcess(int pid, int burstTime, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }
}

public class SJFNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        SJFNProcess[] processes = new SJFNProcess[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time and burst time for process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes[i] = new SJFNProcess(i + 1, bt, at);
        }

        // Sort processes by arrival time and burst time
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (processes[j].burstTime > processes[j + 1].burstTime) {
                    SJFNProcess temp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = temp;
                }
            }
        }

        int totalTime = 0, totalWT = 0, totalTAT = 0;

        for (SJFNProcess p : processes) {
            p.waitingTime = totalTime - p.arrivalTime;
            totalTime += p.burstTime;
            p.turnaroundTime = p.waitingTime + p.burstTime;
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        // Print Gantt Chart
        System.out.println("Gantt Chart: ");
        for (SJFNProcess p : processes) {
            System.out.print("P" + p.pid + " ");
        }
        System.out.println("\n");

        // Print process details
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (SJFNProcess p : processes) {
            System.out.println("P" + p.pid + "\t" + p.arrivalTime + "\t" + p.burstTime + "\t" + p.waitingTime + "\t" + p.turnaroundTime);
        }

        System.out.println("Average Waiting Time: " + (totalWT / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));

        sc.close();
    }
}
