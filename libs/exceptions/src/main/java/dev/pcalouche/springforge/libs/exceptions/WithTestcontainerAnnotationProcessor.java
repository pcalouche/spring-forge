package dev.pcalouche.springforge.libs.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({ "dev.pcalouche.springforge.libs.exceptions.WithTestcontainer" })
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class WithTestcontainerAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		List<WithTestcontainer> annotationsToValidate = new ArrayList<>();
		for (Element element : roundEnv.getElementsAnnotatedWith(WithTestcontainer.class)) {
			annotationsToValidate.add(element.getAnnotation(WithTestcontainer.class));
		}
		return true;
	}

}
