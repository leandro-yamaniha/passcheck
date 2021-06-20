package com.lsy.passcheck.archtest;


import com.lsy.passcheck.archtest.annotation.ArchTest;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTest
@DisplayName("padrão de código fonte, repository")
class ArchRepositoryTest {

    @Test
    @DisplayName("deve terminar com Repository")
    void shouldHaveSimpleNameEndingWithServiceImpl(final JavaClasses classes) {
        classes()
                .that().resideInAPackage("..repository..")
                .should().haveSimpleNameEndingWith("Repository")
                .check(classes);

    }

    @Test
    @DisplayName("deve ter a annotation Repository")
    void shouldBeAnnotatedWithService(final JavaClasses classes) {
        classes()
                .that().resideInAPackage("..repository..")
                .should().beAnnotatedWith(Repository.class)
                .check(classes);

    }


}
