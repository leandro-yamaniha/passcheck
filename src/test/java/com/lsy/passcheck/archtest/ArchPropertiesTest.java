package com.lsy.passcheck.archtest;

import com.lsy.passcheck.archtest.annotation.ArchTest;
import com.tngtech.archunit.core.domain.JavaClasses;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@ArchTest
@DisplayName("padrão de código fonte, properties")
class ArchPropertiesTest {

    @Test
    @DisplayName("deve ter o nome terminado com properties")
    void shouldHaveSimpleNameEndingWithProperties(final JavaClasses classes) {
        classes()
                .that().resideInAPackage("..properties..")
                .and().areNotMemberClasses()
                .should().haveSimpleNameEndingWith("Properties")
                .check(classes);
    }

    @Test
    @DisplayName("deve ter a annotation ConstructorBinding")
    void shouldBeAnnotatedWithConstructorBinding(final JavaClasses classes) {
        classes()
                .that().resideInAPackage("..properties..")
                .and().areNotMemberClasses()
                .should().beAnnotatedWith(ConstructorBinding.class)
                .check(classes);
    }

    @Test
    @DisplayName("deve ser a annotation ConfigurationProperties")
    void shouldBeAnnotatedWithConfigurationProperties(final JavaClasses classes) {
        classes()
                .that().resideInAPackage("..properties..")
                .and().areNotMemberClasses()
                .should().beAnnotatedWith(ConfigurationProperties.class)
                .check(classes);
    }
}
