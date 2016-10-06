package bd.backup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SimpleBackuper implements Backuper {

	@Override
	public void backup(String fileName, String DBFileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(DBFileName)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			String strRead = null;
			while ((strRead = br.readLine()) != null) {
				bw.write(strRead);
				bw.newLine();
			}
			bw.close();
			br.close();
		} catch (Exception e) {
			System.err.println("Error file");
		}
	}

	@Override
	public void reestablishFrom(String fileName, String DBFileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(DBFileName)));
			String strRead = null;
			while ((strRead = br.readLine()) != null) {
				bw.write(strRead);
				bw.newLine();
			}
			bw.close();
			br.close();
		} catch (Exception e) {
			System.err.println("Error file");
		}
	}

}
