import java.util.*;

public class Scheduler {
       
    public class Job {
        private String name;
        private int time;

        public Job(String name,int time){
            this.name = name;
            this.time = time;
        }

        public String getName(){
            return this.name;
        }

        public int getTime(){
            return this.time;
        }

        public void setName(String name){
            this.name = name;
        }

        public void setTime(int time){
            this.time = time;
        }

        public String toString() {
            return name + " (Remaining Time: " + time + ")";
        }
    }

    private Queue<Job>[] cpuQueues;
    private ArrayList<String> logs;
    private int numCpus;
    private int cpuSpeed;
    private int timeQuantum;
    private int schedulerTime;
   
    public void init(int numCpus, int cpuSpeed, int timeQuantum) {
        this.numCpus = numCpus;
        this.cpuSpeed = cpuSpeed;
        this.timeQuantum = timeQuantum;
        this.schedulerTime = 1;

        cpuQueues = new LinkedList[numCpus];
        logs = new ArrayList<>();

        for(int i=0; i<numCpus; i++){
            cpuQueues[i] = new LinkedList<>();
        }
    }

    public boolean allJobsComplete() {
        for (Queue<Job> queue : cpuQueues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void addJob(Job job) {
        int minimum = cpuQueues[0].size();
        int index = 0;
    
        for (int i = 0; i < cpuQueues.length; i++) {
            if(cpuQueues[i].size() < minimum) {
                minimum = cpuQueues[i].size();
                index = i;
            } 
            else if(cpuQueues[i].size() == minimum && i < index){
                minimum = cpuQueues[i].size();
                index = i;
            }
        }
    
        cpuQueues[index].add(job);
        String string = "Job " + job.getName() + " arrived at time " + schedulerTime;
        logs.add(string);

        Collections.sort(logs);
    }

/**
 * Run method for the Scheduler class. This method simulates the execution of jobs on the CPUs.
 * 
 * This method iterates through each CPU queue, simulating the execution of jobs. For each CPU,
 * it checks if the queue is empty. If not, it retrieves the job at the front of the queue and 
 * calculates the execution time based on the minimum of the job's remaining time and the CPU speed.
 * Then, it updates the job's remaining time accordingly.
 * 
 * After updating the job's remaining time, it generates a log message indicating the state of the CPU
 * and adds it to the logs ArrayList. If the job's remaining time becomes zero, it removes the job from
 * the queue and generates a completion log message.
 * 
 * Additionally, if the current scheduler time is a multiple of the time quantum, the job is preempted
 * and added to the end of the queue.
 * 
 * Finally, the scheduler time is incremented for the next iteration.
 */

    public void run() {
        //iterate through each cpu
        for (int i = 0; i < numCpus; i++) {
            Queue<Job> queue = cpuQueues[i];
            //check if the cpu queue is not empty
            if (queue.isEmpty() == false) {
                //retrieve the job at the front of the queue
                Job current = queue.peek();
                //calculate the execution time based on the minimum of the jobs remaining time and cpu speed
                int executionTime = Math.min(current.getTime(), cpuSpeed);
                //update the jobs remaining time
                current.setTime(current.getTime() - executionTime);
        
                String log = "Scheduler dump at time " + schedulerTime + ":\n";
                log = log + "CPU " + i + ": " + current.toString() + "\n";
                //add the log message to the logs arraylist
                logs.add(log);
        
                //check if the jobs remaining time is zero
                if (current.getTime() == 0) {
                    //remove the job from the queue
                    queue.poll();
                    //generate a completion log message
                    String completionLog = "Job " + current.getName() + " finished at time " + (schedulerTime+1);
                     //add the completion log message to the logs ArrayList
                    logs.add(completionLog);
                } 
                //check if the current scheduler time is a multiple of the time quantum
                else if (schedulerTime % timeQuantum == 0) {
                    //preempt the job and add it to the end of the queue
                    queue.poll();
                    queue.add(current);
                }
            }
        }
    
        //preempt the job and add it to the end of the queue
        schedulerTime++; 
    }     

    public void timeline() {
        System.out.println("Timeline:");
        for(String log : logs) {
            if (log.contains("Job") && (log.contains("arrived") || log.contains("started") || log.contains("finished"))) {
                System.out.println(log);
            }
        }
        System.out.println();
        System.out.println("Process finished with exit code 0");
    }

    @Override
    public String toString() {
        String string = "";

        string += "Scheduler dump at time " + schedulerTime + ":\n";

        for (int i = 0; i < cpuQueues.length; i++) {
            string += "CPU " + i + ": ";
            Queue<Job> queue = cpuQueues[i];
            if (!queue.isEmpty()) {
                string += queue.peek();
                for (Job job : queue) {
                    if (job != queue.peek()) {
                        string += " " + job;
                    }
                }
            }
            string += "\n";
        }

        return string;
    }
}