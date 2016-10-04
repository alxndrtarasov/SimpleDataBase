package bd.delete;

import java.util.List;

import objtype.Obj;

public interface Deleter {
	public List<Obj> delete(String fieldName, String fieldValue, String fileName);

	public Obj deleteById(int id, String fileName);
}
