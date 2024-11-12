package practical;

import java.util.Scanner;

public class SJFPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int[] bt = new int[n];
        int[] at = new int[n];
        int[] rt = new int[n];
        int[] wt = new int[n];
        int[] tat = new int[n];
        boolean[] completed = new boolean[n];

        System.out.println("Enter Arrival Time and Burst Time of the processes:");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (i + 1) + ": ");
            at[i] = sc.nextInt();
            bt[i] = sc.nextInt();
            rt[i] = bt[i]; // remaining time
        }

        int completedProcesses = 0, currentTime = 0, shortest = 0;
        boolean found;
        String ganttChart = "";

        while (completedProcesses < n) {
            found = false;
            for (int i = 0; i < n; i++) {
                if (!completed[i] && at[i] <= currentTime && (found == false || rt[i] < rt[shortest])) {
                    shortest = i;
                    found = true;
                }
            }

            if (found) {
                rt[shortest]--;
                ganttChart += "P" + (shortest + 1) + " ";
                currentTime++;
                if (rt[shortest] == 0) {
                    completed[shortest] = true;
                    completedProcesses++;
                    tat[shortest] = currentTime - at[shortest];
                    wt[shortest] = tat[shortest] - bt[shortest];
                }
            } else {
                currentTime++;
                ganttChart += "idle ";
            }
        }

        // Output Gantt Chart
        System.out.println("Gantt Chart: " + ganttChart);

        // Calculate Average WT and TAT
        float avgWT = 0, avgTAT = 0;
        System.out.println("Process\tArrival\tBurst\tWaiting\tTurnaround");
        for (int i = 0; i < n; i++) {
            avgWT += wt[i];
            avgTAT += tat[i];
            System.out.println("P" + (i + 1) + "\t" + at[i] + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i]);
        }

        avgWT /= n;
        avgTAT /= n;

        System.out.println("Average Waiting Time: " + avgWT);
        System.out.println("Average Turnaround Time: " + avgTAT);
        sc.close();
    }
}
