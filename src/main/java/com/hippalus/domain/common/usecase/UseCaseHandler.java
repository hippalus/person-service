package com.hippalus.domain.common.usecase;

import com.hippalus.domain.common.model.UseCase;

public interface UseCaseHandler<E,T extends UseCase> {

    E handle(T useCase);
}
