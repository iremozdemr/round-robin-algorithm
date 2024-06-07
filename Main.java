import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();

        scheduler.init(1, 1 , 4);  
        // 1 CPU, CPU speed is 1 unit per iteration, time quantum is 1 units

        try (BufferedReader reader = new BufferedReader(new FileReader("input1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Scheduler.Job job = parseJob(line, scheduler);
                scheduler.addJob(job);
                System.out.println(scheduler);
                scheduler.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Run until all jobs are complete
        while (!scheduler.allJobsComplete()) {
            System.out.println(scheduler);
            scheduler.run();
        }

        // Print the timeline
        scheduler.timeline();
    }

    private static Scheduler.Job parseJob(String line, Scheduler scheduler) {
        String[] parts = line.split(" ");
        String name = parts[0];
        int burstTime = Integer.parseInt(parts[1]);
        return scheduler.new Job(name, burstTime);
    }
}