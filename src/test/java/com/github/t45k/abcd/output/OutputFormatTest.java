package com.github.t45k.abcd.output;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputFormatTest {
    @Test
    public void createTest() {
        final OutputFormat jsonFormat = OutputFormat.create(Format.JSON);
        assertThat(jsonFormat).isInstanceOf(JsonFormat.class);

        final OutputFormat csvFormat = OutputFormat.create(Format.CSV);
        assertThat(csvFormat).isInstanceOf(CSVFormat.class);

        final OutputFormat xmlFormat = OutputFormat.create(Format.XML);
        assertThat(xmlFormat).isInstanceOf(XMLFormat.class);

        final OutputFormat textFormat = OutputFormat.create(Format.TXT);
        assertThat(textFormat).isInstanceOf(TextFormat.class);
    }


}