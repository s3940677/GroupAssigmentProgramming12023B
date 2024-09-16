public class WelcomeScreen {
    public void display() {
        System.out.println("COSC2081 GROUP ASSIGNMENT");
        System.out.println("AUTO136 CAR DEALERSHIP MANAGEMENT SYSTEM");
        System.out.println("Instructor: Mr. Minh Vu & Mr. Dung Nguyen");
        System.out.println("Group: 12");
        System.out.println("s3940677, Vu Phat Dai");
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearConsole();
    }

    // Simulate clearing the console
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
       
    


