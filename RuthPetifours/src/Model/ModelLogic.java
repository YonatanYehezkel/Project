package Model;

import java.io.IOException;
import java.io.Serializable;

public class ModelLogic implements I_ModelLogic , Serializable{
	
	/**
	 * the serialVersionUID is for serialization
	 */
	private static final long serialVersionUID = 1L;
	
	private static I_ModelLogic instance;
	/**SysData reference pointer*/
	public static SysData sData; 
		
	public SysData getsData() {
		return sData;
	}
	
	public void Serialize(){
		SysData.Serialize(sData);
	}
	
	public void inputSerialize(){
		sData=SysData.inputSerialize();
	}
	
	public static I_ModelLogic getInstance() {
		return instance;
	}
	
	public ModelLogic() {
		//to complete;
	}
	
	public static I_ModelLogic CreateInstance() throws IOException {
		if(instance == null){
			instance  = new ModelLogic();
			return instance;
		}
		else{
			return instance;
		}
	}

}
