package com.algawors.algashop.ordering.domain.valueobject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class DocumentTest {

    @Nested
    class DocumentConstructor {

        @Test
        void given_newDocument_whenValueIsNotNull_shouldCreateNewDocument() {
            String documentNumbers = "255-08-0758";
            Document doc = new Document(documentNumbers);

            Assertions.assertThat(doc.value()).isEqualTo(documentNumbers);
        }

        @Test
        void given_newDocument_whenValueIsNull_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(NullPointerException.class)
                    .isThrownBy(() -> new Document(null));
        }

        @Test
        void given_newDocument_whenValueIsBlank_shouldGenerateException() {
            Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                    .isThrownBy(() -> new Document(""));
        }

    }

    @Test
    void given_newDocument_whenCallToString_shouldReturnValue() {
        String documentNumbers = "255-08-0758";
        Document doc = new Document(documentNumbers);

        Assertions.assertThat(doc).hasToString(documentNumbers);
    }

}