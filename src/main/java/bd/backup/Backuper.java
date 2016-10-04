package bd.backup;

public interface Backuper {
	public void backup(String fileName, String DBFileName);

	public void reestablishFrom(String fileName, String DBFileName);
}
