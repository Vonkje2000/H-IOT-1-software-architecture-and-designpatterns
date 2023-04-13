package InfrastructureLayer;

// #########################################################
// De klasse Subject:
// #########################################################
public interface Subject {
    static ChangeManager M = new ChangeManager();
    default public void insertObserver (Observer x) { M.register(this,x); }
    default public void removeObserver (Observer x) { M.unRegister(x); }
    default public void notifyObservers () { M.notifyObservers(this);}
    public static Subject get (Observer x) { return ChangeManager.get(x); }
}
