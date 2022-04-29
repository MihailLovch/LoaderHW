public abstract class AbstractApp {
    protected AbstractApp(){
        init();
        start();
    }
    protected abstract void init();
    protected abstract void start();
}
