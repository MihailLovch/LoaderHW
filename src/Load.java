import java.net.MalformedURLException;

public class Load implements Commands {

    private final String PATH = "";
    private App app;

    public Load(App app) {
        this.app = app;
    }

    @Override
    public void execute() throws MalformedURLException {
        Runnable runnable = new Downloader(app.getCommandParameter());
        Thread thread = new Thread(runnable);
        app.getProcesses().add(thread);
        app.getRunnable().add(runnable);
        thread.start();
    }
}

