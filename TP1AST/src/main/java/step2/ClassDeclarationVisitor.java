package step2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;


public class ClassDeclarationVisitor extends ASTVisitor {
	List<TypeDeclaration> classes = new ArrayList<TypeDeclaration>();
	
	public boolean visit(TypeDeclaration node) {
		if(!node.isInterface())
			classes.add(node);
		return super.visit(node);
	}
	
	public List<TypeDeclaration> getClasses(){
		return classes;
	}

}
