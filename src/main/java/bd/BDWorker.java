package bd;

import java.util.List;

import javassist.NotFoundException;
import objtype.Obj;

public interface BDWorker {
	public int getObjectsCount();

	public List<Obj> getAllObjects();

	public void add(Obj o);

	public List<Obj> delete(String fieldName, String fieldValue);

	public Obj deleteById(int id);

	public Obj findById(int id) throws NotFoundException;

	public List<Obj> find(String fieldName, String field) throws NotFoundException;

	public void change(int id, String fieldName, String fieldValue);

	public void backup(String fileName);

	public void reestablishFrom(String fileName);

	public void importTo(String fileName);

	public String getFileName();

	public int getLastId();
}
