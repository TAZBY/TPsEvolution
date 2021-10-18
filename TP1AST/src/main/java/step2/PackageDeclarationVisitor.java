package step2;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;

public class PackageDeclarationVisitor extends ASTVisitor {
	List<PackageDeclaration> packages = new ArrayList<PackageDeclaration>();
	
	public boolean visit(PackageDeclaration node) {
		if(!packages.contains(node)) {
			packages.add(node);
			return super.visit(node);
		}else {
			return super.visit(node);
		}
		
		
	}

	public List<PackageDeclaration> getPackage() {
		return packages;
	}
	
}
