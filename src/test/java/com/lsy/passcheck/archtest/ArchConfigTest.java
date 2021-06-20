package com.lsy.passcheck.archtest;

import com.lsy.passcheck.archtest.annotation.ArchTest;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Configuration;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTest
@DisplayName("padrão de código fonte, config")
class ArchConfigTest {

    @Test
    @DisplayName("deve terminar com Configuration")
    void shouldHaveSimpleNameEndingWithConfiguration(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".config")
                .should().haveSimpleNameEndingWith("Configuration")
                .check(classes);
    }

    @Test
    @DisplayName("deve ter annotation Configuration")
    void shouldBeAnnotatedWithConfiguration(final JavaClasses classes) {
        classes()
                .that().resideInAPackage(".config")
                .should().beAnnotatedWith(Configuration.class)
                .check(classes);
    }
}
