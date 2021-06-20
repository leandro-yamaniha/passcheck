package com.lsy.passcheck.archtest;

import com.lsy.passcheck.archtest.annotation.ArchTest;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTest
@DisplayName("padrão de código fonte quando é exception")
class ArchExceptionTest {
	
    @Test
    @DisplayName("verificando classes no pacote exception terminadas com exception ...")
    void shouldHaveSimpleNameEndingWithException(final JavaClasses classes) {
        classes()
                .that().resideInAPackage("..exception..")
                .should().haveSimpleNameEndingWith("Exception")
                .check(classes);
    }
}
