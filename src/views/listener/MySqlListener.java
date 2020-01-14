package views.listener;

public interface MySqlListener {
    void onStarted();
    void onSuccess(boolean flag);
    void onFailed(String error);

}
