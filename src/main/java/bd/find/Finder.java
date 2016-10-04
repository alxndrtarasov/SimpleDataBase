package bd.find;

import java.util.List;

import objtype.Obj;

public interface Finder {
	public Obj findById(int id, String fileName);

	public List<Obj> find(String fieldName, String field, String fileName);
}
