package kata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.Collections;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

public class DictionaryReplacerTest {

	private static final Map<String, String> EMPTY_DICTIONARY = Collections.emptyMap();
	private String input;
	private Map<String, String> dictionary;
	private String output;

	@DataProvider
	Object[][] testCases() {
		return new Object[][] {
				// input, dictionary, expected-output
				{ "$temp$", EMPTY_DICTIONARY, "$temp$" },
				{ "", EMPTY_DICTIONARY, "" },
				{ "$temp$", null, "$temp$" },
				{ "$temp$", dictionaryWithEntry("temp", "temporary"), "temporary" },
				{ "xx$temp$yy", EMPTY_DICTIONARY, "xx$temp$yy" },
				{ "xx$temp$yy", dictionaryWithEntry("temp", "temporary"), "xxtemporaryyy" },
				{ "$temp$ here comes the name $name$", dictionaryWithTwoEntries("temp", "temporary", "name", "John Doe"),
						"temporary here comes the name John Doe" },
		};
	}

	private Map<String, String> dictionaryWithTwoEntries(String key1, String value1, String key2, String value2) {
		Map<String, String> dict = dictionaryWithEntry(key1, value1);
		dict.put(key2, value2);
		return dict;
	}

	private Map<String, String> dictionaryWithEntry(String key, String value) {
		Map<String, String> dict = Maps.newHashMap();
		dict.put(key, value);
		return dict;
	}

	@Test(dataProvider = "testCases")
	public void test_with_data_provider(String input, Map<String, String> dictionary, String expectedOutput) {

		givenAnInputStringAndDictionary(input, dictionary);
		whenReplacementIsDone();
		thenStringIsReturned(expectedOutput);
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void should_raise_NullPointerException_when_input_is_null() {

		givenAnInputStringAndDictionary(null, EMPTY_DICTIONARY);
		whenReplacementIsDone();
	}

	private void thenStringIsReturned(String string) {
		assertThat(output, is(string));
	}

	private void givenAnInputStringAndDictionary(String string, Map<String, String> dictionary) {
		this.dictionary = dictionary;
		input = string;
	}

	private void whenReplacementIsDone() {
		output = new DictionaryReplacer().replace(input, dictionary);
	}

}
