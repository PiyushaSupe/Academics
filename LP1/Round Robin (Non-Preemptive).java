import java.util.Scanner;

public class RoundRobinNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        System.out.print("Enter time quantum: ");
        int quantum = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
        }

        int time = 0, completed = 0;
        String ganttChart = "";

        while (completed < n) {
            boolean processExecuted = false;

            for (int i = 0; i < n; i++) {
                if (remainingTime[i] > 0) {
                    if (remainingTime[i] > quantum) {
                        time += quantum;
                        remainingTime[i] -= quantum;
                        ganttChart += "P" + (i + 1) + " ";
                    } else {
                        time += remainingTime[i];
                        remainingTime[i] = 0;
                        ganttChart += "P" + (i + 1) + " ";
                        completed++;
                        waitingTime[i] = time - burstTime[i];
                        turnaroundTime[i] = time;
                    }
                    processExecuted = true;
                }
            }

            if (!processExecuted) {
                ganttChart += "idle ";
                time++;
            }
        }

        // Print Gantt Chart
        System.out.println("Gantt Chart: " + ganttChart);

        // Calculate average waiting time and turnaround time
        int totalWT = 0, totalTAT = 0;
        System.out.println("Process\tBurst\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            totalWT += waitingTime[i];
            totalTAT += turnaroundTime[i];
            System.out.println("P" + (i + 1) + "\t" + burstTime[i] + "\t" + waitingTime[i] + "\t" + turnaroundTime[i]);
        }

        System.out.println("Average Waiting Time: " + (totalWT / (float) n));
        System.out.println("Average Turnaround Time: " + (totalTAT / (float) n));
        sc.close();
    }
}
