package step2;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class AttributeDeclarationVisitor extends ASTVisitor {
	List<FieldDeclaration> attributs = new ArrayList<FieldDeclaration>();
	
	public boolean visit(FieldDeclaration node) {
		attributs.add(node);
		return super.visit(node);
	}

	public List<FieldDeclaration> getAttributs() {
		return attributs;
	}
	public int nbrAttribut() {
		return attributs.size();
	}
	
}
