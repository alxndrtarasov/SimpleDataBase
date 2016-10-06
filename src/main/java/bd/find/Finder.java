package bd.find;

import java.util.List;

import javassist.NotFoundException;
import objtype.Obj;

public interface Finder {
	public Obj findById(int id, String fileName) throws NotFoundException;

	public List<Obj> find(String fieldName, String field, String fileName) throws NotFoundException;
}
