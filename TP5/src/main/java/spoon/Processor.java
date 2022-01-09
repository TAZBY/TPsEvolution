package spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

import java.io.Serializable;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Set;

public class Processor extends AbstractProcessor<CtPackage> {



	public void process(CtPackage myPack) {

		//Main.wrappedPackages.put(myPack.getQualifiedName(),myPack);
		Factory factory = getFactory();

      System.out.println(myPack.getSimpleName());
		if(myPack.getSimpleName().equals("loggers")){
			CtClass lpsModel = factory.createClass(myPack,"LpsModel");
			lpsModel.setSimpleName("LpsModel");
			lpsModel.setVisibility(ModifierKind.PUBLIC);
			Set<CtTypeReference> interfaces = new HashSet<>();
			interfaces.add(getFactory().Code().createCtTypeReference(Serializable.class));
			lpsModel.setSuperInterfaces(interfaces);
			CtClass myJsonFormatter =factory.createClass(myPack,"MyJsonFormatter");
			myJsonFormatter.setSimpleName("MyJsonFormatter");
			myJsonFormatter.setVisibility(ModifierKind.PUBLIC);
			myJsonFormatter.setSuperclass(getFactory().Code().createCtTypeReference(Formatter.class));
			CtClass readLogger =factory.createClass(myPack,"ReadLogger");
			readLogger.setSimpleName("ReadLogger");
			readLogger.setVisibility(ModifierKind.PUBLIC);
			readLogger.setSuperclass(getFactory().Code().createCtTypeReference(Exception.class));
		}

	}

}
