package com.github.t45k.abcd.output;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FormatTest {

    @Test
    public void testGetFormat() {
        final Format json = Format.getFormat("json");
        assertThat(json).isEqualTo(Format.JSON);

        final Format csv = Format.getFormat("csv");
        assertThat(csv).isEqualTo(Format.CSV);

        final Format xml = Format.getFormat("xml");
        assertThat(xml).isEqualTo(Format.XML);

        final Format txt = Format.getFormat("txt");
        assertThat(txt).isEqualTo(Format.TXT);

        assertThatThrownBy(() -> Format.getFormat("hoge"))
                .isInstanceOfSatisfying(RuntimeException.class, e -> assertThat(e.getMessage()).isEqualTo("invalid format was specified"));
    }
}
