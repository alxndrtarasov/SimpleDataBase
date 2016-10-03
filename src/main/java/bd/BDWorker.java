package bd;

import java.util.Collection;
import java.util.List;

import objtype.Obj;

public interface BDWorker {
	public int getObjectsCount();

	public List<Obj> getAllObjects();

	public abstract void add(Obj o);

	public abstract Collection<Obj> delete(String fieldName, String fieldValue);

	public abstract Obj deleteById(int id);

	public abstract void change(int id, String fieldName, String fieldValue);

	public abstract void backup(String fileName);

	public abstract void reestablishFrom(String fileName);

	public abstract void importTo(String fileName);

	public String getFileName();

	public int getLastId();
}
