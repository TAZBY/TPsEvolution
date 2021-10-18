package step2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.internal.utils.FileUtil;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class ReponsesQuestions {
	
	public static final String projectPath = "C:\\Users\\zaino\\OneDrive\\Bureau\\M2 GL\\evolution\\Tps\\CorrectionTP1_Partie1\\CodeTD2Etape2 (6)\\step2";
	public static final String projectSourcePath = projectPath + "\\src";
	public static final String jrePath = "C:\\Program Files\\Java\\jre1.8.0_291\\lib\\rt.jar";


	public static void main(String[] args) throws IOException {
		
		System.out.println("**************Reponses aux Questions du TP************\n");
				
		// read java files
		final File folder = new File(projectSourcePath);
		ArrayList<File> javaFiles = listJavaFilesForFolder(folder);
		
		//Q1 : Nombre de classes de l'application
		int nbrClasse = javaFiles.size();
		System.out.println("Question 1 : Nombre total de classes : "+nbrClasse);
		
		
		// Q2 :  Nombre de lignes de l'application
		int nbLignes = 0;
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			 
			String[] fileContent = content.split("\n");
			for(int i=0;i<fileContent.length;i++) {
				nbLignes++;
			}
			

		}
		System.out.println("\nQuestion 2 : Nombre de ligne total : "+nbLignes);
		
		//Q 3 : Nombre total de methodes de l'application
		int nbrMethodTotal = 0;
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);

			CompilationUnit parse = parse(content.toCharArray());
			nbrMethodTotal += nbrMethode(parse);
			
		}
		System.out.println("\nQuestion 3 : Nombre total de methodes : "+nbrMethodTotal);
		
		//Q 4 : nombre total de packages de l'application
		int nbrPackage = 0;
		ArrayList<String> packageList = new ArrayList();
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			 
			String[] fileContent = content.split(" ");
			String firstPackage = fileContent[1];
			for(int i=0;i<fileContent.length;i++) {
				if(fileContent[i].toLowerCase().equals("package")) {
					if(!packageList.contains(firstPackage)) {
						packageList.add(fileContent[i+1]);
					}
				}
			}
			
		}
		
		int nbrPackageTotal = 0;
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);

			CompilationUnit parse = parse(content.toCharArray());
			nbrPackageTotal += nbrPackage(parse);
			
		}
		System.out.println("\nQuestion 4 : Nombre total de packages : "+nbrPackageTotal);
		
		// Q 5 : nombre moyen de methodes par classe
		
		System.out.println("\nQuestion 5 : Nombre moyen de methodes par classe : "+nbrMethodTotal/nbrClasse);
		
		//Q 6 : nombre moyen de methodes par classe
		
		int nbrLignemethod = 0;
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			 
			String[] fileContent = content.split("\n");
			for(int i=0;i<fileContent.length;i++) {
				//nbLignes++;
			}
		}
		System.out.println("\nQuestion 6 : Nombre moyen de lignes par Methode : "+nbLignes/nbrMethodTotal);
		
		// Q 7 : nombre moyen d'attributs par classe
		int nbrAttribut = 0;
		
		for (File fileEntry : javaFiles) {
			String content = FileUtils.readFileToString(fileEntry);
			// System.out.println(content);
			
			CompilationUnit parse = parse(content.toCharArray());
			nbrAttribut += nbrAttribut(parse);
		
		}
		System.out.println("\nQuestion 7 : Nombre moyen d'attributs par classe : "+nbrAttribut/nbrClasse);
		
		
		// Q8 Les	10%	des	classes	qui	possèdent	le	plus	grand	nombre	de	méthodes.
		ClassDeclarationVisitor visitor1 = new ClassDeclarationVisitor();
		System.out.println("Question 8 : Les 10% des classes qui possèdent le plus grand nombre de méthodes");

		for (File fileEntry : javaFiles) {
			
			String content = FileUtils.readFileToString(fileEntry);
			CompilationUnit parse = parse(content.toCharArray());
			parse = parse(content.toCharArray());
			parse.accept(visitor1);
			
		}
		System.out.println(visitor1.getClasses().stream().sorted(new Compare())
				.limit((10 * nbrMethodTotal) / 100).map(x -> x.getName().toString())
				.collect(Collectors.toList()));
		
		//Q9 Les	10%	des	classes	qui	possèdent	le	plus	grand	nombre d’attributs.
		System.out.println("Question 9 : Les 10%	des	classes	qui	possèdent le plus grand nombre d’attributs");

		for (File fileEntry : javaFiles) {
			
			String content = FileUtils.readFileToString(fileEntry);
			CompilationUnit parse = parse(content.toCharArray());
			parse = parse(content.toCharArray());
			parse.accept(visitor1);
			
		}
		System.out.println(visitor1.getClasses().stream().sorted(new Compare())
				.limit((10 * nbrAttribut) / 100).map(x -> x.getName().toString())
				.collect(Collectors.toList()));
		
		//Q10 . Les	classes	qui	font	partie	en	même	temps	des	deux	catégories	précédentes.
		System.out.println("Question 10 : Les classes qui font partie en même temps	des	deux catégories	précédentes");
		for (File fileEntry : javaFiles) {
			
			String content = FileUtils.readFileToString(fileEntry);
			CompilationUnit parse = parse(content.toCharArray());
			parse = parse(content.toCharArray());
			parse.accept(visitor1);
			
		}
		System.out.println(visitor1.getClasses().stream().sorted(new Compare())
				.limit((10 * nbrAttribut) / 100).map(x -> x.getName().toString())
				.collect(Collectors.toList()));
		//Q11 Les	classes	qui	possèdent plus	de	X	méthodes	(la	valeur	de X	est	donnée)
		// dans cet exemple nous avons fixés  X = 3. 
		System.out.println("Question 11 : Les classes qui possèdent plus de X méthodes la valeur de X est fixee ici à 3");
		for (File fileEntry : javaFiles) {
			
			String content = FileUtils.readFileToString(fileEntry);
			CompilationUnit parse = parse(content.toCharArray());
			parse = parse(content.toCharArray());
			parse.accept(visitor1);
			
		}
		System.out.println(visitor1.getClasses().stream().filter(c -> c.getMethods().length > 3)
				.map(x -> x.getName().toString()).collect(Collectors.toList()));
		
		
	
	}
		

	// read all java files from specific folder
	public static ArrayList<File> listJavaFilesForFolder(final File folder) {
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				javaFiles.add(fileEntry);
			}
		}

		return javaFiles;
	}
	
	//Le nombre de methodes de l'application
	public static int  nbrMethode(CompilationUnit parse) {
		int nbrMethod = 0;
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor();
		parse.accept(visitor);

		for (MethodDeclaration method : visitor.getMethods()) {
			nbrMethod++;
		}
		return nbrMethod;

	}
	
	//Le nombre de Packages de l'application
		public static int  nbrPackage(CompilationUnit parse) {
			int nbrPackage = 0;
			PackageDeclarationVisitor visitor = new PackageDeclarationVisitor();
			parse.accept(visitor);

			for (PackageDeclaration method : visitor.getPackage()) {
				nbrPackage++;
			}
			return nbrPackage;

		}
		//Le nombre d'attributs de l'application
				public static int  nbrAttribut(CompilationUnit parse) {
					int nbrVariable = 0;
					AttributeDeclarationVisitor visitor = new AttributeDeclarationVisitor();
					parse.accept(visitor);

					return visitor.nbrAttribut();

				}
		
		
		
		

	
	// Lignes de l'application
	public static int nbrLigneTotal(final File folder) {
		int nbrLigneTotal = 0;
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : javaFiles) {
			FileReader fr = null;
			try {
				fr = new FileReader(fileEntry);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      BufferedReader br = new BufferedReader(fr);  
		      String str;
		      try {
				while((str = br.readLine()) != null)
				  {
				     
					  nbrLigneTotal++;               
				        
				  }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return nbrLigneTotal;
	}
	
	public static void listVariables(final File folder,CompilationUnit parse ) {
		int nbrAtributs = 0;
		ArrayList<File> javaFiles = new ArrayList<File>();
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				//javaFiles.addAll(listJavaFilesForFolder(fileEntry));
			} else if (fileEntry.getName().contains(".java")) {
				VariableDeclarationFragmentVisitor visitor2 = new VariableDeclarationFragmentVisitor();
				parse.accept(visitor2);
				
				for (VariableDeclarationFragment variableDeclarationFragment : visitor2
						.getVariables()) {
					System.out.println("variable name: "
							+ variableDeclarationFragment.getName()
							+ " variable Initializer: "
							+ variableDeclarationFragment.getInitializer());
					nbrAtributs++;
				}
				javaFiles.add(fileEntry);
			}
		}

		
	}

	
	//create AST
	private static CompilationUnit parse(char[] classSource) {
		ASTParser parser = ASTParser.newParser(AST.JLS4); // java +1.6
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
 
		parser.setBindingsRecovery(true);
 
		Map options = JavaCore.getOptions();
		parser.setCompilerOptions(options);
 
		parser.setUnitName("");
 
		String[] sources = { projectSourcePath }; 
		String[] classpath = {jrePath};
 
		parser.setEnvironment(classpath, sources, new String[] { "UTF-8"}, true);
		parser.setSource(classSource);
		
		return (CompilationUnit) parser.createAST(null); // create and parse
	  }

}
