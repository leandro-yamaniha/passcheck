package com.lsy.passcheck.archtest;

import com.lsy.passcheck.archtest.annotation.ArchTest;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTest
@DisplayName("padrão de código fonte, utils")
class ArchUtilsTest {

    @Test
    @DisplayName("deve ter somente atributos com final")
    void shouldhaveOnlyFinalFields(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".utils..")
                .should().haveOnlyFinalFields()
                .check(classes);
    }

    @Test
    @DisplayName("deve ter somente construtor privado")
    void shouldhaveOnlyPrivateConstructors(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".utils..")
                .should().haveOnlyPrivateConstructors()
                .check(classes);
    }

    @Test
    @DisplayName("deve terminar com o nome utils")
    void shouldHaveSimpleNameEndingWithUtils(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".utils..")
                .should().haveSimpleNameEndingWith("Utils")
                .check(classes);
    }
}
