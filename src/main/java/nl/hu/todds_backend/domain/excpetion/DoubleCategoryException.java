package nl.hu.todds_backend.domain.excpetion;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DoubleCategoryException extends RuntimeException {
    public DoubleCategoryException(String message) {
        super(message);
    }
}
