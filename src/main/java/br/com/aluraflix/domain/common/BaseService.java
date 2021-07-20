package br.com.aluraflix.domain.common;

import br.com.aluraflix.infraestructure.exception.ServiceException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService {

    public void validate(ValidationRule... rules) {
        if (rules.length == 0)
            return;

        List<String> errors = Arrays.stream(rules)
                .map(ValidationRule::validate)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        if (errors.size() == 1)
            throw new ServiceException(errors.get(0));
        if (!errors.isEmpty())
            throw new ServiceException(errors);
    }

}
