public class StepPerSecond {
    public static volatile boolean isStopped = false;

    public static void main(String[] args) throws InterruptedException {
        Runner ivanov = new Runner("Ivanov", 4);
        Runner petrov = new Runner("Petrov", 2);
        ivanov.start();
        petrov.start();
        Thread.sleep(2000);
        isStopped = true;
        Thread.sleep(1000);

    }

    public static class Stopwatch extends Thread {
        private Runner owner;
        private int stepNumber;

        public Stopwatch(Runner owner) {
            this.owner = owner;
        }

        @Override
        public void run() {
            try {
                while (!isStopped) {
                    soSeveralSteps();
                }
            } catch (InterruptedException e) {

            }

        }

        private void soSeveralSteps() throws InterruptedException {
            stepNumber++;
            Thread.sleep(((long) (1000 / owner.getSpeed())));
            System.out.println(owner.getName() + " makes a move #" + stepNumber + "!");
        }
    }

    public static class Runner {
        private String name;
        private double speed;
        Stopwatch stopwatch;

        public Runner(String name, double speed) {
            this.name = name;
            this.speed = speed;
            this.stopwatch = new Stopwatch(this);
        }

        public String getName() {
            return name;
        }

        public double getSpeed() {
            return speed;
        }

        public void start() {
            stopwatch.start();
        }
    }
}
