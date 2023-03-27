package com.testngAI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class LocatorsStorage {
	private static String LocatorsSheetName = "src\\test\\java\\com\\Locators\\StudentPageLocators.xlsx";

	public static void inputLocator(String htmlinput, String sheetName, String tagName)
			throws EncryptedDocumentException, IOException {

		FileInputStream inputStream = new FileInputStream(new File(LocatorsSheetName));
		Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheet(sheetName);

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		data.put("0", new Object[] { "Loc1", "Loc2", "Loc3", "Loc4", "Loc5", "Loc6", "Loc7" });

		String htmlsplit[] = htmlinput.split("<" + tagName + " ");
		for (int i = 0; i < htmlsplit.length; i++) {
			String htmlsplit2[] = htmlsplit[i].split("\" ");

			for (int ii = 1; ii < htmlsplit2.length; ii++) {
//    			if(ii==0) {
//    		     data.put(""+(i+1), new Object[] { ""+htmlsplit2[0].replace(">", "")+"\"", "", "", "", "", "", ""});
//    			}
				if (ii == 1) {
					data.put("" + (i + 1), new Object[] { "" + htmlsplit2[0].replace(">", "") + "\"",
							"" + htmlsplit2[1].replace(">", "") + "\"", "", "", "", "", "" });
				}
				if (ii == 2) {
					data.put("" + (i + 1),
							new Object[] { "" + htmlsplit2[0].replace(">", "") + "\"",
									"" + htmlsplit2[1].replace(">", "") + "\"",
									"" + htmlsplit2[2].replace(">", "") + "\"", "", "", "", "" });
				}
				if (ii == 3) {
					data.put("" + (i + 1), new Object[] { "" + htmlsplit2[0].replace(">", "") + "\"",
							"" + htmlsplit2[1].replace(">", "") + "\"", "" + htmlsplit2[2].replace(">", "") + "\"",
							"" + htmlsplit2[3].replace(">", "") + "\"", "", "", "" });
				}
			}
		}

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.getRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(LocatorsSheetName));
			workbook.write(out);
			out.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

}