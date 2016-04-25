package ie;

import liuzhixiang.chrome.MyAiTestStationCreate;

 
public class TestYHJYForIE_99All  {
    public static void main(String[] args) throws Exception {
		TestYHJYForIE_01AddUser.main(null);
		TestYHJYForIE_02UserType.main(null);
		TestYHJYForIE_03UserType.main(null);
		TestYHJYForIE_04LoseJob.main(null);
		TestYHJYForIE_05Paper.main(null);
		TestYHJYForIE_07EmpHard.main(null);
		TestYHJYForIE_08EmpHardAppro.main(null);
		
		//单位新增公益性岗位
		MyAiTestStationCreate.main(args);
		//公益性岗位审核
		MyAiTestStationCreate.main(args);
    }
}