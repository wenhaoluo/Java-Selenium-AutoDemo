package Config;

public class Constants {
	 // 这里定义为public static的类型，方便其他任何类进行访问和调用  
    public static final String URL = "https://www.baidu.com";  
    public static final String Path_TestData = ".//dataEngine//dataEngine.xlsx";  
    public static final String File_TestData = "dataEngine.xlsx";  
    // OR(对象仓库)文件路径  
    public static final String Path_OR =".//OR";  
  
    // dataEngine.xlsx -> Test Steps 中一些单元格的索引值  
    public static final int Col_TestCaseID = 0;  
    public static final int Col_TestScenarioID = 1;  
    public static final int Col_PageObject = 3 ;  
    public static final int Col_ActionKeyword = 4 ;
    public static final int Col_DataSet = 5 ;
    public static final int Col_TestStepResult = 6 ;
    
    // dataEngine.xlsx -> Test Cases 第一个是测试用例结果标记列的索引，第二个是测试步骤里面的结果列索引 
    public static final int Col_RunMode = 2 ;
    public static final int Col_Result = 3 ;
  
    // DataEngmine.excel中case表名称  
    public static final String Sheet_TestSteps = "Test Steps";  
    // DataEngmine.excel中case suit表名称 
    public static final String Sheet_TestCases = "Test Cases";
    
    // 结果状态标记 
    public static final String KEYWORD_PASS = "PASS";
    public static final String KEYWORD_FAIL = "FAIL";  
  
    // 测试登录用到的用户数据  
    public static final String UserName = "xxxxxx";  
    public static final String Password = "xxxxxxx";
}  
