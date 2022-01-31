package service;

public interface Service <T,I>{
    I save(T entity);
}
