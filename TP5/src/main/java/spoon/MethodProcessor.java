package spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtPackage;
import spoon.reflect.factory.Factory;

public class MethodProcessor extends AbstractProcessor<CtMethod> {



	public void process(CtMethod method) {
		Factory factory = getFactory();
		CtBlock b =method.getBody();
		switch (method.getSimpleName()){
			case "fetch":
				String codeFetch = "searchLogger.logger.info(genrateLog(p,\"fetch\"))";
				//System.out.println(method.getSimpleName());


				CtCodeSnippetStatement fetchStatement = factory
						.Code()
						.createCodeSnippetStatement(codeFetch);
				b.addStatement(b.getStatements().size()-1,fetchStatement);

				break;
			case "display":
				String codeDisplay = "readLogger.logger.info(genrateLog(null,\"display\"))";
				//System.out.println(method.getSimpleName());

				CtCodeSnippetStatement displayStatement = factory
						.Code()
						.createCodeSnippetStatement(codeDisplay);
				b.addStatement(b.getStatements().size(),displayStatement);
				break;
			case "add":
				String codeAdd = "writeLogger.logger.info(genrateLog(product,\"add\"))";
				//System.out.println(method.getSimpleName());

				CtCodeSnippetStatement addStatement = factory
						.Code()
						.createCodeSnippetStatement(codeAdd);
				b.addStatement(b.getStatements().size()-1,addStatement);

				break;
			case "delete":

				String codeDelete = "writeLogger.logger.info(genrateLog(product,\"delete\"));";
				//System.out.println(method.getSimpleName());

				CtCodeSnippetStatement deleteStatement = factory
						.Code()
						.createCodeSnippetStatement(codeDelete);
				b.addStatement(b.getStatements().size()-1,deleteStatement);
				break;

			case "update":
				String codeUpdate = "writeLogger.logger.info(genrateLog(product,\"delete\"));";
				//System.out.println(method.getSimpleName());

				CtCodeSnippetStatement updateStatement = factory
						.Code()
						.createCodeSnippetStatement(codeUpdate);
				b.addStatement(b.getStatements().size()-1,updateStatement);
				break;
		}
	}

}
