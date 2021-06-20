package com.lsy.passcheck.archtest;


import com.lsy.passcheck.archtest.annotation.ArchTest;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTest
@DisplayName("padrão de código fonte, interface service")
class ArchServiceTest {

    @Test
    @DisplayName("no pacote service deve ser codificado com o nome terminado com service")
    void shouldHaveSimpleNameEndingWithService(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".service..")
                .and().resideOutsideOfPackage("..impl")
                .should().haveSimpleNameEndingWith("Service")
                .check(classes);

    }

    @Test
    @DisplayName("no pacote service deve ser codificado somente interface")
    void shouldBeInterfacesService(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".service..")
                .and().resideOutsideOfPackage("..impl")
                .should().beInterfaces()
                .check(classes);
    }

    @Test
    @DisplayName("no pacote service deve ser acessado somente por listener ou service")
    void shouldOnlyBeAccessedByListenerAndServicePackage(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".service..")
                .should().onlyBeAccessed().byAnyPackage("..listener..", "..service..");
    }

}
