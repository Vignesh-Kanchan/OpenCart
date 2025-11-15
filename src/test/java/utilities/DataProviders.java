package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name="logingData")
	String[][] getData() throws IOException{
		
		String path=".\\testData\\OpenCart_LoginData.xlsx";
		
		
		ExcelUtility xlutil=new ExcelUtility(path);
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("sheet1", 1);
		
		String loginData[][]=new String[totalrows][totalcols];
		
		for(int i=1; i<=totalrows; i++)
		{
			for(int j=0; j<totalcols; j++)
			{
					loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j); //i-1 =arrayindex starts from 0,
			}
		}
		return loginData;
	}
	
	
	
}
