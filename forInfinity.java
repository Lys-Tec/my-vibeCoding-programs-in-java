public class forInfinity {
    public static void main(String[] args) {
        for (int i = 0; ; i += 100) {
            System.out.println(i);
            try {
                Thread.sleep(1000); // 1000ms delay same as 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}