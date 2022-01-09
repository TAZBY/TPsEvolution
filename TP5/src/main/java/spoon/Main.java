package spoon;

import spoon.Launcher;
import spoon.MavenLauncher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.factory.Factory;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static Map<String,CtPackage> wrappedPackages = new HashMap<>();

	
	public static void main(String[] args) {
		
//		String prPath = "C:\\Users\\pc\\Desktop\\Study\\Master\\RestructionMaintenance\\TP5\\Product_CRUD_TP5\\src" ;
//		String prPathBin = "C:\\Users\\pc\\Desktop\\Study\\Master\\RestructionMaintenance\\TP5\\Product_CRUD_TP5\\target" ;
//		String prPathOutPut = "C:\\Users\\pc\\Desktop\\Study\\Master\\RestructionMaintenance\\TP5\\Product_CRUD_TP5\\spooned" ;
//		
		String prPath = "C:\\Users\\zaino\\eclipse-workspace\\TP5\\src" ;
		String prPathBin = "C:\\Users\\zaino\\eclipse-workspace\\TP5\\target" ;
		String prPathOutPut = "C:\\Users\\zaino\\eclipse-workspace\\TP5\\spooned" ;
		SpoonParser parser = new SpoonParser();
		parser.setLauncher(prPath, prPathBin,prPathOutPut);
		Launcher launcher =  parser.getLauncher();
		launcher.addProcessor(new RootProcessor());
		launcher.addProcessor(new Processor());
		launcher.addProcessor(new ClassProcessor());
		launcher.addProcessor(new MethodProcessor());
	    	launcher.run();
		System.out.println("Log generated successfully");
	}
     
  
    
	
}
