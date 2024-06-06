package DH.ClinicaOdontologica.dao;

import java.util.List;

public interface iDao<T> {
    //todo el crud
    T guardar(T t);
    T buscarPorId(Integer id);
    void eliminar(T t);
    void actualizar(T t);
    List<T> buscarTodos();
    T buscarPorString(String string);

}
