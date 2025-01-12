package org.prebid.server.geolocation;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class CountryCodeMapperTest {

    private CountryCodeMapper target;

    @Before
    public void setUp() {
        target = new CountryCodeMapper("UA, UKR", "402, UA");
    }

    @Test
    public void creationShouldThrowErrorInvalidCountryCodesResourceFile() {
        // when and then
        assertThatIllegalArgumentException().isThrownBy(() -> new CountryCodeMapper("invalid_resource", "401, UA"));
    }

    @Test
    public void creationShouldThrowErrorInvalidMccCountryCodesResourceFile() {
        // when and then
        assertThatIllegalArgumentException().isThrownBy(() -> new CountryCodeMapper("UA, UKR", "invalid_resource"));
    }

    @Test
    public void mapToAlpha3ShouldCorrectlyMapAlpha2Code() {
        // when and then
        assertThat(target.mapToAlpha3("UA")).isEqualTo("UKR");
    }

    @Test
    public void mapToAlpha3ShouldTolerateInvalidCaseAlpha2Code() {
        // when and then
        assertThat(target.mapToAlpha3("uA")).isEqualTo("UKR");
    }

    @Test
    public void mapToAlpha3ShouldReturnNullOnEmptyAlpha2Code() {
        // when and then
        assertThat(target.mapToAlpha3("")).isNull();
    }

    @Test
    public void mapToAlpha2ShouldCorrectlyMapAlpha3Code() {
        // when and then
        assertThat(target.mapToAlpha2("UKR")).isEqualTo("UA");
    }

    @Test
    public void mapToAlpha2ShouldTolerateInvalidCaseAlpha3Code() {
        // when and then
        assertThat(target.mapToAlpha2("uKr")).isEqualTo("UA");
    }

    @Test
    public void mapToAlpha2ShouldReturnNullOnEmptyAlpha3Code() {
        // when and then
        assertThat(target.mapToAlpha2("")).isNull();
    }

    @Test
    public void mapMccToAlpha2ShouldCorrectlyMapAlpha2Code() {
        // when and then
        assertThat(target.mapMccToAlpha2("402")).isEqualTo("UA");
    }

    @Test
    public void mapMccToAlpha2ShouldReturnNullOnEmptyAlpha2Code() {
        // when and then
        assertThat(target.mapMccToAlpha2("")).isNull();
    }
}
