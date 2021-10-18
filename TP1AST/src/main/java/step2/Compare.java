package step2;

import java.util.Comparator;

import org.eclipse.jdt.core.dom.TypeDeclaration;

public class Compare implements Comparator<TypeDeclaration> {

	@Override
	public int compare(TypeDeclaration o1, TypeDeclaration o2) {
		return o2.getFields().length - o1.getFields().length;
	}
}