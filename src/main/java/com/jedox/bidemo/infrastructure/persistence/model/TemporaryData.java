package com.jedox.bidemo.infrastructure.persistence.model;

public record TemporaryData (Long dimensionOneId, String dimensionNameOne, Long parentOneId,
                             Long dimensionTwoId, String dimensionNameTwo, Long parentTwoId,
                             Long dimensionThreeId, String dimensionNameThree, Long parentThreeId,
                             double data) {
}
