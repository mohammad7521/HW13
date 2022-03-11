package services;

import java.util.List;

public interface BaseServices<T> {

    public T add(T t);

    public T remove(int id);

    public void update (T t);

    public T showInfo(int id);

    public List<T> showAll();

}
