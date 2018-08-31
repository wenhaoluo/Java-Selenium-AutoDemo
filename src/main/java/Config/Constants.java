package Config;

public class Constants {
	 // ���ﶨ��Ϊpublic static�����ͣ����������κ�����з��ʺ͵���  
    public static final String URL = "https://www.baidu.com";  
    public static final String Path_TestData = ".//dataEngine//dataEngine.xlsx";  
    public static final String File_TestData = "dataEngine.xlsx";  
    // OR(����ֿ�)�ļ�·��  
    public static final String Path_OR =".//OR";  
  
    // dataEngine.xlsx -> Test Steps ��һЩ��Ԫ�������ֵ  
    public static final int Col_TestCaseID = 0;  
    public static final int Col_TestScenarioID = 1;  
    public static final int Col_PageObject = 3 ;  
    public static final int Col_ActionKeyword = 4 ;
    public static final int Col_DataSet = 5 ;
    public static final int Col_TestStepResult = 6 ;
    
    // dataEngine.xlsx -> Test Cases ��һ���ǲ��������������е��������ڶ����ǲ��Բ�������Ľ�������� 
    public static final int Col_RunMode = 2 ;
    public static final int Col_Result = 3 ;
  
    // DataEngmine.excel��case������  
    public static final String Sheet_TestSteps = "Test Steps";  
    // DataEngmine.excel��case suit������ 
    public static final String Sheet_TestCases = "Test Cases";
    
    // ���״̬��� 
    public static final String KEYWORD_PASS = "PASS";
    public static final String KEYWORD_FAIL = "FAIL";  
  
    // ���Ե�¼�õ����û�����  
    public static final String UserName = "xxxxxx";  
    public static final String Password = "xxxxxxx";
}  
