package com.github.t45k.abcd.output;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputTest {
    @Test
    public void createTest() {
        final Output jsonFormat = Output.create(Format.JSON);
        assertThat(jsonFormat).isInstanceOf(OutputJson.class);

        final Output csvFormat = Output.create(Format.CSV);
        assertThat(csvFormat).isInstanceOf(OutputCSV.class);

        final Output xmlFormat = Output.create(Format.XML);
        assertThat(xmlFormat).isInstanceOf(OutputXML.class);

        final Output textFormat = Output.create(Format.TXT);
        assertThat(textFormat).isInstanceOf(OutputText.class);
    }


}