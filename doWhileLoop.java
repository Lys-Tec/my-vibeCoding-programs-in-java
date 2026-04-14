public class doWhileLoop {
    public static void main(String[] args) {
        int i = 0;
        do {
            System.out.println(i);
            i++;
            try {
                Thread.sleep(100); // 100ms delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } while (true);
    }
}
