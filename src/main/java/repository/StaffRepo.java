package repository;

public interface StaffRepo<T>{

    public long add(T t);

    public long remove (T t);

    public boolean update (T t);

    public T showInfo(long id);


}
