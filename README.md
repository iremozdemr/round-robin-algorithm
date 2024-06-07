# Scheduler Implementation in Java

## Overview

This project implements a Round-Robin (RR) scheduler using queue data structures. The scheduler manages CPU resources effectively and ensures fair and efficient execution of various jobs. The scheduler class is designed to simulate job scheduling in an operating system with a configurable number of CPUs, CPU speed, and time quantum.

## How It Works

The Round-Robin scheduler works by allocating a fixed time quantum (tk) to each job. If a job is not finished within the allocated time, it is moved back to the queue, and the next job in the queue is loaded into the CPU. This continues until all jobs are completed.

For example:
- Time Quantum (tk) = 4 units
- Job1 total time = 6 units
- Job2 total time = 3 units

Job1 is loaded into the CPU for 4 units of time, then moved back to the queue. Job2 is then loaded into the CPU and finishes within 3 units of time. Job1 is then loaded back into the CPU and finishes its remaining 2 units of time.

## Lab Work

In this lab, we simulate the job scheduling process with varying CPU counts and speeds. The scheduler uses a queue data structure for each CPU to manage job scheduling.

### Simulation Environment

The simulation is conducted in the `main` method, with a loop where each iteration represents a CPU time unit. During each time unit, a new job is read by the scheduler and added to the appropriate queue. Each CPU has its own queue, and jobs are executed using the Round-Robin method.

## Classes and Methods

### Scheduler Class

The `Scheduler` class manages the job queues for each CPU and handles the scheduling logic. Key methods include:

- `init(int numCpus, int cpuSpeed, int timeQuantum)`: Initializes the scheduler with the given number of CPUs, CPU speed, and time quantum.
- `addJob(Job job)`: Adds a job to the appropriate CPU queue.
- `run()`: Executes the scheduling simulation, performing job scheduling and handling time quantum expiration.
- `allJobsComplete()`: Checks if all jobs have been completed.
- `timeline()`: Logs the timeline of job events (arrival, start, finish).
- `toString()`: Outputs the current state of the CPU queues and running jobs.

### Job Class

The `Job` class represents a job with a name and total work time. Key methods include:

- `getName()`: Returns the job's name.
- `getTime()`: Returns the job's remaining time.
- `setName(String name)`: Sets the job's name.
- `setTime(int time)`: Sets the job's remaining time.
- `toString()`: Returns a string representation of the job.

## Assumptions

- Each line in the input file represents a job with a name and work time.
- The order of jobs in the input file represents their arrival time.
- Jobs are added to the CPU queue with the least number of jobs. If queues are equal, the job is added to the CPU with the smallest index.
- If a job is moved back to the queue due to time quantum expiration, it is added to the end of its original CPU's queue.
- A CPU with speed greater than 1 can still execute only one job per `run()` call, even if it has leftover capacity.

## Usage

1. Initialize the scheduler with the desired number of CPUs, CPU speed, and time quantum.
2. Add jobs to the scheduler using the `addJob()` method.
3. Call the `run()` method to start the simulation.
4. Use the `timeline()` method to print the timeline of job events.
5. Use the `toString()` method to print the current state of the CPU queues.

## Running the Project

To run the project, follow these steps:

1. Ensure you have a Java development environment set up.
2. Compile the `Scheduler.java` file.
3. Run the `Scheduler` class, ensuring your input file is correctly formatted and accessible.
4. Observe the scheduler output and timeline logs.

## Notes

- Ensure your input file is properly formatted and placed in the correct directory.
- Adjust the CPU count, speed, and time quantum as needed for different simulations.

## License

This project is licensed under the MIT License. See the LICENSE file for more details.

---

Enjoy experimenting with job scheduling using the Round-Robin method!