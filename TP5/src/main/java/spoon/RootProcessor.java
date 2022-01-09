package spoon;

import spoon.processing.AbstractProcessor;
import spoon.reflect.CtModelImpl;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class RootProcessor extends AbstractProcessor<CtModelImpl.CtRootPackage> {


    @Override
    public void process(CtModelImpl.CtRootPackage element) {
        Factory factory = element.getFactory();
        CtPackage ctPackage = factory.createPackage();
        ctPackage.setSimpleName("loggers");
        element.addPackage(ctPackage);

    }
}
