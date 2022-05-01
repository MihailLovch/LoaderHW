import java.net.MalformedURLException;

public class StatusManipulator implements Commands{
    private App app;
    public StatusManipulator(App app) {
        this.app = app;
    }
    @Override
    public void execute() throws MalformedURLException {
        Thread thread = null;
        String command = app.getCommandName();
        int num = Integer.parseInt(app.getCommandParameter());
        try{
            thread = app.getProcesses().get(num-1);
        }catch (IndexOutOfBoundsException ex){
            System.out.println("No such download");
        }
//        switch (thread.getState().toString()){
//            case "RUNNABLE" :
//                if (command.equals("stop")){
//                    thread.interrupt();
//                    System.out.println("Interrupted");
//                }else{
//                    System.out.println("Already running");
//                }
//                break;
//            case "WAITING":
//                break;
//            case "TERMINATED":
//                System.out.println("Already terminated");
//                break;
//        }
        switch (command){
            case "stop":
                if (thread.isAlive()){
                    thread.interrupt();
                }else if (thread.isInterrupted()){
                    System.out.println("Already interrupted");
                }
                break;
            case "continue":
                if (thread.isAlive()){
                    System.out.println("Already downloading");
                }else if (thread.isInterrupted()){
                    thread.start();
                }
                break;
        }
    }
}
