package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Config.Constants;
import Demo.DriverScript;  

public class ExcelUtils {
	 
    private static XSSFSheet ExcelWSheet;  
    private static XSSFWorkbook ExcelWBook;  
    private static XSSFCell Cell;  
    private static XSSFRow Row;  
  
  
    // ����Excel�ļ�·���������ȡ���ļ�  
    public static void setExcelFile(String Path) throws Exception {  
        try {  
            FileInputStream ExcelFile = new FileInputStream(Path);  
            ExcelWBook = new XSSFWorkbook(ExcelFile);  
        } catch (Exception e){  
            Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());  
            DriverScript.bResult = true;  
        }  
    }  
  
    // ��ȡExcel�ļ���Ԫ������  
    // ����sheet name�����������Ϳ���ȥ��ȡTest Steps��Test Cases����������ĵ�Ԫ������  
    public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception{  
        try{  
            ExcelWSheet = ExcelWBook.getSheet(SheetName);  
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);  
            String CellData = Cell.getStringCellValue();
            return CellData;  
        }catch (Exception e){  
            Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());  
            DriverScript.bResult = true;  
            return"";  
        }  
    }  
  
    //�õ�һ������������  
    public static int getRowCount(String SheetName){  
        int iNumber=0;  
        try {  
            ExcelWSheet = ExcelWBook.getSheet(SheetName);  
            iNumber=ExcelWSheet.getLastRowNum()+1;  
        } catch (Exception e){  
            Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());  
            DriverScript.bResult = true;  
        }  
        return iNumber;  
    }  
  
    //�õ������������к�  
    public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception{  
        int iRowNum=0;  
        try {  
            //ExcelWSheet = ExcelWBook.getSheet(SheetName);  
            int rowCount = ExcelUtils.getRowCount(SheetName);  
            for (; iRowNum<rowCount; iRowNum++){  
                if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName)){  
                    break;  
                }  
            }  
        } catch (Exception e){  
            Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());  
            DriverScript.bResult = true;  
        }  
        return iRowNum;  
    }  
  
    //����һ�����������ж��ٸ�����  
    public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception{  
        try {  
            for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){  
                if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){  
                    int number = i;  
                    return number;  
                }  
            }  
            ExcelWSheet = ExcelWBook.getSheet(SheetName);  
            int number=ExcelWSheet.getLastRowNum()+1;  
            return number;  
        } catch (Exception e){  
            Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());  
            DriverScript.bResult = true;  
            return 0;  
        }  
    }  
  
    // ����һ������Ԫ��д���ݵķ�����������д���pass����fail  
    public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    {  
        try{  
  
            ExcelWSheet = ExcelWBook.getSheet(SheetName);  
            Row  = ExcelWSheet.getRow(RowNum);  
            Cell = Row.getCell(ColNum);  
            if (Cell == null) {  
                Cell = Row.createCell(ColNum);  
                Cell.setCellValue(Result);  
            } else {  
                Cell.setCellValue(Result);  
            }  
            // Constant variables Test Data path and Test Data file name  
            FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);  
            ExcelWBook.write(fileOut);  
            //fileOut.flush();  
            fileOut.close();  
            ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));  
        }catch(Exception e){  
            DriverScript.bResult = true;  
        }  
    }  
  
}   