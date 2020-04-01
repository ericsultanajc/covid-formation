package sopra.formation.persistence;

import java.util.List;

public interface IDao<T, PK> {
	
	List<T> findAll();

	T find(PK id);

	T create(T obj);

	T update(T obj);

	void delete(PK id);	
}
