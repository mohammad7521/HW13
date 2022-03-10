package services;

public interface BaseServices<T> {

    public T add(T t);

    public T remove(long id);

    public void update (T t);

    public T showInfo(long id);
}
