package br.com.aluraflix.domain.common;

import java.util.Optional;

@FunctionalInterface
public interface ValidationRule {

    Optional<String> validate();

}
