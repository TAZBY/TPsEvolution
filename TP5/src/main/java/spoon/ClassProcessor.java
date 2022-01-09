package spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.code.CtAssignmentImpl;
import spoon.support.reflect.declaration.CtImportImpl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class ClassProcessor extends AbstractProcessor<CtClass> {

	// prescondition de filtre


	public void process(CtClass myClass) {

		Factory factory = getFactory();
		switch (myClass.getSimpleName()){
			case "AccessRepo" :

			CtField<?> searchLoggerfield = factory.createField();
			searchLoggerfield.setSimpleName("searchLogger");
			searchLoggerfield.setVisibility(ModifierKind.PRIVATE);
			searchLoggerfield.addModifier(ModifierKind.STATIC);
			CtPackage ctPackage = factory.createPackage();
			ctPackage.setSimpleName("loggers");
			CtType readLogger = factory.createAnnotationType(ctPackage,"ReadLogger");
			searchLoggerfield.setType(readLogger.getReference());
			CtExpression searchExpression = factory.createCodeSnippetExpression("new ReadLogger(\"Searchlog.txt\")");
			searchLoggerfield.setAssignment(searchExpression);
			myClass.addFieldAtTop(searchLoggerfield);

			CtField<?> writeLoggerfield = factory.createField();
			writeLoggerfield.setSimpleName("writeLogger");
			writeLoggerfield.setVisibility(ModifierKind.PRIVATE);
			writeLoggerfield.addModifier(ModifierKind.STATIC);
			writeLoggerfield.setType(readLogger.getReference());
			CtExpression writeExpression = factory.createCodeSnippetExpression("new ReadLogger(\"Writelog.txt\")");
			writeLoggerfield.setAssignment(writeExpression);
			myClass.addFieldAtTop(writeLoggerfield);


			CtField<?> field = factory.createField();
			field.setSimpleName("readLogger");
			field.setVisibility(ModifierKind.PRIVATE);
			field.addModifier(ModifierKind.STATIC);
			field.setType(readLogger.getReference());
			CtExpression expression = factory.createCodeSnippetExpression("new ReadLogger(\"Readlog.txt\")");
			field.setAssignment(expression);
			myClass.addFieldAtTop(field);

				//create Method for logs

				CtMethod logsMethod = factory.createMethod();
				logsMethod.setVisibility(ModifierKind.PUBLIC);
				logsMethod.addModifier(ModifierKind.STATIC);
				logsMethod.setSimpleName("genrateLog");
				logsMethod.setType(factory.Type().stringType());

				CtParameter plogRecord = factory.createParameter();
				plogRecord.setSimpleName("product");
				CtPackage PackageModels = factory.createPackage();
				PackageModels.setSimpleName("models");
				CtType product2 = factory.createAnnotationType(PackageModels,"Product");
				plogRecord.setType(product2.getReference());
				logsMethod.addParameter(plogRecord);

				CtParameter opT = factory.createParameter();
				opT.setSimpleName("operation");
				opT.setType(factory.Type().stringType());
				logsMethod.addParameter(opT);

				CtAssignment ctAssignmentLps = factory.createAssignment();
				ctAssignmentLps.setAssigned(factory.createCodeSnippetExpression("LpsModel logObj"));
				CtType lpsModelType = factory.createAnnotationType(ctPackage,"LpsModel");
				ctAssignmentLps.setType(lpsModelType.getReference());
				//ctAssignmentLps.setType(factory.Type().dateType());
				CtAssignment dateAssignement = factory.createAssignment();
				dateAssignement.setAssigned(factory.createCodeSnippetExpression("Date now"));
				dateAssignement.setType(lpsModelType.getReference());
				dateAssignement.setType(factory.Type().dateType());
				dateAssignement.setAssignment(
						factory.createCodeSnippetExpression(
								"new Date(System.currentTimeMillis())")
				);


				ctAssignmentLps.setAssignment(
						factory.createCodeSnippetExpression(
								"new LpsModel(user" +
										",operation,product,df.format(now)))")
				);
				CtCodeSnippetStatement ignorePassword = getFactory()
						.Code()
						.createCodeSnippetStatement("User user = CLI.getLoggedUser(); \n" +
								"user.setPassword(\"*****\") ");
				CtBlock<?> ctBlockOflogsMethod = getFactory().Code().createCtBlock(ignorePassword);
				ctBlockOflogsMethod.addStatement(0,dateAssignement);
				ctBlockOflogsMethod.addStatement(ctBlockOflogsMethod.getStatements().size(),ctAssignmentLps);
				CtAssignment ctAssignmentstringLog = factory.createAssignment();
				ctAssignmentstringLog.setAssigned(factory.createCodeSnippetExpression("String stringLog"));
				ctAssignmentstringLog.setAssignment(
						factory.createCodeSnippetExpression("gson.toJson(logObj)")
				);
				ctBlockOflogsMethod.addStatement(ctBlockOflogsMethod.getStatements().size(),ctAssignmentstringLog);

				CtReturn ctReturn = factory.createReturn();
				ctReturn.setReturnedExpression(factory.createCodeSnippetExpression("stringLog"));
				ctBlockOflogsMethod.addStatement(ctBlockOflogsMethod.getStatements().size(),ctReturn);
				logsMethod.setBody(ctBlockOflogsMethod);
				myClass.addMethod(logsMethod);


			break;

			case "LpsModel":
				//user Field
				CtField<?> fieldUser = factory.createField();
				fieldUser.setSimpleName("user");
				fieldUser.setVisibility(ModifierKind.PRIVATE);
				CtPackage ctPackageModels = factory.createPackage();
				ctPackageModels.setSimpleName("models");
				CtType user = factory.createAnnotationType(ctPackageModels,"User");
				fieldUser.setType(user.getReference());
				myClass.addFieldAtTop(fieldUser);

				//Operation Type
				CtField<?> operationType = factory.createField();
				operationType.setSimpleName("operationType");
				operationType.setVisibility(ModifierKind.PRIVATE);
				CtTypeReference stringReference = getFactory().Code().createCtTypeReference(String.class);
				operationType.setType(stringReference);
				myClass.addFieldAtTop(operationType);



				//product
				CtField<?> fieldProduct = factory.createField();
				fieldProduct.setSimpleName("product");
				fieldProduct.setVisibility(ModifierKind.PRIVATE);
				CtType product = factory.createAnnotationType(ctPackageModels,"Product");
				fieldProduct.setType(product.getReference());
				myClass.addFieldAtTop(fieldProduct);

				//TimesTemps
				CtField<?> timeType = factory.createField();
				timeType.setSimpleName("timesTemp");
				timeType.setVisibility(ModifierKind.PRIVATE);
				timeType.setType(stringReference);
				myClass.addFieldAtTop(timeType);
				//constructeur1
				CtConstructor lpsConstructor1 = getFactory().createConstructor();
				CtCodeSnippetStatement lpsStatementInConstructor1 = getFactory()
						.Code()
						.createCodeSnippetStatement("super()");
				CtBlock<?> ctBlockOfConstructorLps = getFactory().Code().createCtBlock(lpsStatementInConstructor1);
				lpsConstructor1.setBody(ctBlockOfConstructorLps);
				lpsConstructor1.addModifier(ModifierKind.PUBLIC);
				myClass.addConstructor(lpsConstructor1);
				//constructeur2
				CtConstructor lpsConstructor2 = getFactory().createConstructor();
				CtCodeSnippetStatement lpsStatementInConstructor2 = getFactory()
						.Code()
						.createCodeSnippetStatement("" +
								"\tsuper();\n" +
								"\tthis.user = user;\n" +
								"\tthis.operationType = operationType;\n" +
								"\tthis.product = product;\n" +
								"\tthis.timesTemp = timesTemp");
				CtBlock<?> ctBlockOfConstructorLps2 = getFactory().Code().createCtBlock(lpsStatementInConstructor2);
				lpsConstructor2.setBody(ctBlockOfConstructorLps2);

				CtParameter parameterUser = factory.createParameter();
				parameterUser.setSimpleName("user");
				parameterUser.setType(user.getReference());
				//lpsConstructor2.addParameterAt(0,parameterUser);

				CtParameter parameterOp = factory.createParameter();
				parameterOp.setSimpleName("operationType");
				parameterOp.setType(stringReference);
				//lpsConstructor2.addParameterAt(1,parameterOp);

				CtParameter parameterProduct = factory.createParameter();
				parameterProduct.setSimpleName("product");
				parameterProduct.setType(product.getReference());
				//lpsConstructor2.addParameterAt(2,parameterProduct);

				CtParameter parametertimesTemp = factory.createParameter();
				parametertimesTemp.setSimpleName("timesTemp");
				parametertimesTemp.setType(stringReference);
				//lpsConstructor2.addParameterAt(3,parametertimesTemp);

				lpsConstructor2.addModifier(ModifierKind.PUBLIC);
				myClass.addConstructor(lpsConstructor2);

			break;

			case "MyJsonFormatter":
				//create field

				CtField<?> fieldDf = factory.createField();
				fieldDf.setSimpleName("df");
				fieldDf.setVisibility(ModifierKind.PRIVATE);
				fieldDf.addModifier(ModifierKind.STATIC);
				fieldDf.addModifier(ModifierKind.FINAL);
				//fieldDf.setType(getFactory().Code().createCtTypeReference(DateFormat.class));
				CtExpression expressionDf = factory.createCodeSnippetExpression("new SimpleDateFormat(\"dd/MM/yyyy hh:mm:ss.SSS\")");
				fieldDf.setAssignment(expressionDf);
				myClass.addFieldAtTop(fieldDf);

				//createMethod
				CtMethod formatMethod = factory.createMethod();
				formatMethod.setVisibility(ModifierKind.PUBLIC);
				formatMethod.setSimpleName("format");
				formatMethod.setType(factory.Type().stringType());

				CtParameter logRecord = factory.createParameter();
				logRecord.setSimpleName("record");
				logRecord.setType(factory.Annotation().createReference(LogRecord.class));
				formatMethod.addParameter(logRecord);

				CtAnnotation<Override> overrideAnnotation = factory.createAnnotation();
				overrideAnnotation.setAnnotationType(factory.Annotation().createReference(Override.class));
				formatMethod.addAnnotation(overrideAnnotation);

				CtCodeSnippetStatement formatStatement = factory
						.Code()
						.createCodeSnippetStatement("StringBuilder builder = new StringBuilder(1000);\n" +
								"\t\t String logMessage = formatMessage(record);\t\t \n" +
								"\t\t builder.append(logMessage+\"\\n\");\n" +
								"\t        return builder.toString();");

				formatMethod.setBody(formatStatement);
				myClass.addMethod(formatMethod);
				break;

			case "ReadLogger":
				CtField<?> fieldLogger = factory.createField();
				fieldLogger.setSimpleName("logger");
				fieldLogger.setVisibility(ModifierKind.PUBLIC);
				//fieldLogger.setType(getFactory().Code().createCtTypeReference(Logger.class));
				myClass.addFieldAtTop(fieldLogger);

				CtField<?> fieldFileHandler = factory.createField();
				fieldFileHandler.setSimpleName("fileHandler");
				fieldFileHandler.setVisibility(ModifierKind.PUBLIC);
				//fieldFileHandler.setType(getFactory().Code().createCtTypeReference(FileHandler.class));
				myClass.addFieldAtTop(fieldFileHandler);

				CtField<?> fieldConsoleHandler = factory.createField();
				fieldConsoleHandler.setSimpleName("consoleHandler");
				fieldConsoleHandler.setVisibility(ModifierKind.PUBLIC);
				//fieldConsoleHandler.setType(getFactory().Code().createCtTypeReference(ConsoleHandler.class));
				myClass.addFieldAtTop(fieldConsoleHandler);



				CtConstructor readLoggerConstructor = getFactory().createConstructor();
				CtCodeSnippetStatement readLoggerStatementConstructor = getFactory()
						.Code()
						.createCodeSnippetStatement(""+
								"\t\tif(!file.exists()) {\n" +
								"\t\t\ttry {\n" +
								"\t\t\t\tfile.createNewFile();\n" +
								"\t\t\t} catch (IOException e) {\n" +
								"\t\t\t\t// TODO Auto-generated catch block\n" +
								"\t\t\t\te.printStackTrace();\n" +
								"\t\t\t}\n" +
								"\t\t}\n" +
								"\t\t\n" +
								"\t\ttry {\n" +
								"\t\t\tfileHandler = new FileHandler(fileName,true);\n" +
								"\t\t\tconsoleHandler = new ConsoleHandler() ;\n" +
								"\t\t} catch (SecurityException e) {\n" +
								"\t\t\t// TODO Auto-generated catch block\n" +
								"\t\t\te.printStackTrace();\n" +
								"\t\t} catch (IOException e) {\n" +
								"\t\t\t// TODO Auto-generated catch block\n" +
								"\t\t\te.printStackTrace();\n" +
								"\t\t}\n" +
								"\t\tlogger=Logger.getLogger(fileName);\n" +
								"\t\t\n" +
								"\t\tMyJsonFormatter  formatter = new MyJsonFormatter(); \n" +
								"\t\t\n" +
								"\t\n" +
								"\t\tconsoleHandler.setFormatter(formatter);\n" +
								"\t\t\n" +
								"\t\tlogger.setUseParentHandlers(false);\n" +
								"\t\tlogger.addHandler(fileHandler);\n" +
								"\t\tlogger.addHandler(consoleHandler);\n" +
								"\t\t\n" +
								"\t\t\n" +
								"\t\tfileHandler.setFormatter(formatter)");
				CtAssignment ctAssignmentFile = factory.createAssignment();
				ctAssignmentFile.setAssigned(factory.createCodeSnippetExpression("File file"));
				ctAssignmentFile.setType(getFactory().Code().createCtTypeReference(File.class));
				ctAssignmentFile.setAssignment(factory.createCodeSnippetExpression("new File(fileName)"));



				CtBlock<?> ctBlockOfConstructorRead = getFactory().Code().createCtBlock(readLoggerStatementConstructor);
				//CtCodeElement ctCatch = factory.createCtCatch("IoExc",IOException.class,ctBlockOfConstructorRead);

				ctBlockOfConstructorRead.addStatement(0,ctAssignmentFile);
				CtParameter fileName = factory.createParameter();
				fileName.setSimpleName("fileName");
				fileName.setType(factory.Annotation().createReference(String.class));
				readLoggerConstructor.addParameter(fileName);

				readLoggerConstructor.setBody(ctBlockOfConstructorRead);

				myClass.addConstructor(readLoggerConstructor);
				break;
		}

	}

}
