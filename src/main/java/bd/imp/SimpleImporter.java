package bd.imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import bd.SimpleBDWorker;

public class SimpleImporter implements Importer {

	private SimpleBDWorker worker;

	public SimpleBDWorker getWorker() {
		return worker;
	}

	public void setWorker(SimpleBDWorker worker) {
		this.worker = worker;
	}

	@Override
	public void importTo(String fileName, String DBFileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(DBFileName)));
			FileOutputStream outFile = new FileOutputStream(new File(fileName));
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("Objs");
			Cell cell = null;
			// cell = sheet.getRow(1).getCell(2);
			// cell.setCellValue(cell.getNumericCellValue() * 2);

			String strRead = null;
			int i = 0;
			while ((strRead = br.readLine()) != null) {
				String[] inlineData = strRead.split(";");
				if (inlineData.length > 1) {
					Row row = sheet.createRow(i);

					cell = row.createCell(0);
					cell.setCellValue(inlineData[0].trim());

					cell = row.createCell(1);
					cell.setCellValue(inlineData[1].trim());

					cell = row.createCell(2);
					cell.setCellValue(inlineData[2].trim());

					cell = row.createCell(3);
					cell.setCellValue(inlineData[3].trim());

					i++;
				}
			}

			wb.write(outFile);
			outFile.close();
			br.close();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
