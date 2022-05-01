import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Status implements Commands {
    private App app;
    private Boolean isFound = false;

    public Status(App app) {
        this.app = app;
    }

    @Override
    public void execute() throws MalformedURLException {
        ArrayList<Thread> processes = app.getProcesses();
        ArrayList<Downloader> runnables = app.getRunnable();
        Iterator<Thread> threadIterator = processes.iterator();
        Iterator<Downloader> runnableIterator = runnables.iterator();
        int index = 1;

        System.out.println("Current downloads");
        while (threadIterator.hasNext()) {
            Thread thread = threadIterator.next();
            Downloader runnable = runnableIterator.next();
            System.out.println(index + ". " + runnable.getFile().getName() + " downloaded: " + runnable.getBytesDownloaded()
                    + " " + getPercent(runnable.getBytesDownloaded(), runnable.getUrl()) + " status: " + thread.getState());
            isFound = true;
            index++;
        }
        if (!isFound) {
            System.out.println("Nothing is downloading");
        }
    }
    private String getPercent(int bytes, URL url){
        long totalLen = 0;
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("HEAD");
            totalLen = http.getContentLength();
            http.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ((double) bytes / totalLen) * 100 + "";
    }
}
