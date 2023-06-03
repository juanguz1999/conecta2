package com.colegios_peruanos.conectados.servicio;

import java.util.List;

public interface IServicio<T> {
    
    public List<T> listar();

    public void guardar(T item);

    public void eliminar(T item);

    public T buscar(T item);
    
    public T buscar(Integer id);

}
