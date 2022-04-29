import java.util.ArrayList;
import java.util.Scanner;

public class App extends AbstractApp{

    private ArrayList<Thread> processes;
    private ArrayList<Runnable> runnable;
    private Scanner sc;
    private String[] userInput;
    private Commands[] commands ;
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
                new Load(),
        };
        commandNames = new String[]{"load"};
    }
    @Override
    protected void start() {
        userInput = sc.nextLine().split(" ");
    }

    public ArrayList<Thread> getProcesses() {
        return processes;
    }

    public ArrayList<Runnable> getRunnable() {
        return runnable;
    }

}
// commands :
// load; stop; continue; downloaded, %