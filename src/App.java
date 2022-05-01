import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class App extends AbstractApp {

    private final int MAX_AMOUNT_OF_INPUT = 2;
    private final int MIN_AMOUNT_OF_INPUT = 1;
    private final int COMMAND = 0;
    private final int PARAMETER = 1;

    private ArrayList<Thread> processes;
    private ArrayList<Downloader> runnable;
    private Scanner sc;
    private String[] userInput;
    private Commands[] commands;
    private String[] commandNames;

    public static void main(String[] args) throws MalformedURLException {
        App app = new App();
        Downloader downloader = new Downloader("12");
    }

    @Override
    protected void init() {
        processes = new ArrayList<>();
        runnable = new ArrayList<>();
        sc = new Scanner(System.in);
        commands = new Commands[]{
                new Load(this),
                new Status(this),
                new StatusManipulator(this),
                new StatusManipulator(this)
        };
        commandNames = new String[]{"load","status","stop","continue"};
    }

    @Override
    protected void start() {
        while (true) {
            try {
                userInput = checkUserInput();
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
        if (userInput.length > MAX_AMOUNT_OF_INPUT || userInput.length < MIN_AMOUNT_OF_INPUT) {
            throw new IllegalArgumentException("Not enough arguments");
        } else {
            return userInput;
        }
    }

    public ArrayList<Thread> getProcesses() {
        return processes;
    }

    public ArrayList<Downloader> getRunnable() {
        return runnable;
    }
    public String getCommandParameter(){
        return userInput[PARAMETER];
    }
    public String getCommandName(){
        return userInput[COMMAND];
    }
}
// commands :
// load; stop;status ;continue