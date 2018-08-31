package Demo;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import Config.ActionsKeywords;
import Config.Constants;
import util.ExcelUtils;
import util.Log;

public class DriverScript {

    // ����һ��public static��������������ǿ�����main������Χ֮��ȥʹ�á�  
    public static ActionsKeywords actionsKeywords;  
    public static String sActionKeyword;  
    // �����Ƿ��������Ƿ����������õ�������  
    public static Method method[];  
    // �½�һ��Properties����  
    public static Properties OR;  
    public static String sPageObject;  
  
    public static int iTestStep;  
    public static int iTestLastStep;  
    public static String sTestCaseID;  
    public static String sRunMode;  
    public static boolean bResult;  
    public static String sData;  
  
    // �������ǳ�ʼ��'ActionsKeywords'���һ������  
    public DriverScript() throws NoSuchMethodException, SecurityException{  
        actionsKeywords = new ActionsKeywords();  
        method = actionsKeywords.getClass().getMethods();  
    }  
  
    public static void main(String[] args) throws Exception {  
        // ����һ��Ҫ�ӣ�����log4j��ʼ���ľ���  
        DOMConfigurator.configure("log4j.xml");  
        ExcelUtils.setExcelFile(Constants.Path_TestData);  
        // ����һ���ļ����������󣬲�����Դ�ⲿOR.txt�ļ�  
        FileInputStream fs = new FileInputStream(Constants.Path_OR);  
        // ����һ��Properties����  
        OR = new Properties(System.getProperties());  
        // ����ȫ������ֿ��ļ�  
        OR.load(fs);  
        // ִ������  
        DriverScript startEngine = new DriverScript();  
        startEngine.execute_TestCase();  
    }  
  
    private void execute_TestCase() throws Exception {  
        //��ȡ������������  
        int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);  
        //���forѭ�����ж��ٸ�����������ִ�ж��ٴ�ѭ��  
        for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){  
            //��Test Case���ȡ����ID  
            bResult = true;  
            sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases);  
            //��ȡ��ǰ����������Run Mode��ֵ  
            sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);  
            // Run Mode��ֵ���������Ƿ�ִ��  
            if (sRunMode.equals("Yes")){  
                // ֻ�е�Run Mode�ĵ�Ԫ��������Yes���������Żᱻִ��  
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);  
                iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);  
  
                bResult=true;  
                //�������forѭ���Ĵ����͵��ڲ��������Ĳ�����  
                for (;iTestStep <= iTestLastStep;iTestStep++){  
                    sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);  
                    sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);  
                    sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);  
                    execute_Actions();  
                    if(bResult==false){  
                        //If 'false' then store the test case result as Fail  
                        ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);  
                        //End the test case in the logs  
                        Log.endTestCase(sTestCaseID);  
                        //By this break statement, execution flow will not execute any more test step of the failed test case  
                        break;  
                    }  
                }  
  
                //This will only execute after the last step of the test case, if value is not 'false' at any step  
                if(bResult==true){  
                    //Storing the result as Pass in the excel sheet  
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);  
                    Log.endTestCase(sTestCaseID);  
                }  
            }  
        }  
    }  
  
    private static void execute_Actions() throws Exception {  
  
        for(int i=0;i<method.length;i++){  
  
            if(method[i].getName().equals(sActionKeyword)){  
                //This code will pass three parameters to every invoke method  
                method[i].invoke(actionsKeywords,sPageObject, sData);  
                if(bResult==true){  
                    ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);  
                    break;  
                }else{  
                    ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);  
                    ActionsKeywords.closeBrowser("","");  
                    break;  
                }  
            }  
        }  
    }  
  
}  