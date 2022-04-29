import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends AbstractApp {

    private final int AMOUNT_OF_INPUT = 2;
    private final int COMMAND = 0;
    private final int PARAMETER = 1;

    private ArrayList<Thread> processes;
    private ArrayList<Runnable> runnable;
    private Scanner sc;
    private String[] userInput;
    private Commands[] commands;
    private String[] commandNames;

    public static void main(String[] args) {
        App app = new App();
    }

    @Override
    protected void init() {
        processes = new ArrayList<>();
        runnable = new ArrayList<>();
        sc = new Scanner(System.in);
        commands = new Commands[]{
                new Load(this),
        };
        commandNames = new String[]{"load",};
    }

    @Override
    protected void start() {
        while (true) {
            try {
                userInput = sc.nextLine().split(" ");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            int indexOfExecute = 0;
            boolean found = false;
            for (String command : commandNames) {
                if (command.equals(userInput[COMMAND])) {
                    try {
                        commands[indexOfExecute].execute();
                    } catch (MalformedURLException e) {
                        System.out.println(e.getMessage());
                    }
                    found = true;
                    break;
                }
                indexOfExecute++;
            }
            if (!found) {
                System.out.println("Unknown command");
            }
        }
    }

    public String[] checkUserInput() {
        String[] userInput = sc.nextLine().split(" ");
        if (userInput.length != AMOUNT_OF_INPUT) {
            throw new IllegalArgumentException("Not enough arguments");
        } else {
            return userInput;
        }
    }

    public ArrayList<Thread> getProcesses() {
        return processes;
    }

    public ArrayList<Runnable> getRunnable() {
        return runnable;
    }
    public String getCommandParameter(){
        return userInput[PARAMETER];
    }
}
// commands :
// load; stop; continue; downloaded, %