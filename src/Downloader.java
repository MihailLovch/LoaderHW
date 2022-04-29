import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Downloader implements Runnable{

    private final Path INSTALL_PATH = Paths.get("C:\\Users\\MihailLovch\\IdeaProjects\\LoaderHW\\downloadedFiles");

    private File file;
    private URL url;
    private int bytesDownloaded = 0;

    public Downloader(String path) throws MalformedURLException {
        try {
            url = new URL(path);
            file = new File(INSTALL_PATH.resolve(url.getFile()).toString());
        } catch (MalformedURLException e) {
            throw new MalformedURLException("Incorrect URL");
        }
    }

    @Override
    public void run() {
        createFile();
        try(BufferedInputStream in = new BufferedInputStream(url.openStream());
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            int byt;
            while ((byt = in.read()) != -1){
                bytesDownloaded++;
                out.write(byt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createFile(){
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Change INSTALL_PATH value in Downloader.java");
            e.printStackTrace();
        }
    }

    public int getBytesDownloaded() {
        return bytesDownloaded;
    }
}
