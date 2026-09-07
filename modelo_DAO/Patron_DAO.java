package modelo_DAO;

import java.util.ArrayList;

public interface Patron_DAO <TipoGen,k> {
	public boolean insertar (TipoGen t);
	public boolean borrar (k pk);
	public boolean actualizar (TipoGen t);
	
	public TipoGen buscar (Object pk);
	public ArrayList <TipoGen> listarTodos();

}