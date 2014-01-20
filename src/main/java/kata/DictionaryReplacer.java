package kata;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Preconditions;

public class DictionaryReplacer {

	public String replace(String input, Map<String, String> dictionary) {

		Preconditions.checkNotNull(input);
		if (dictionary != null) {
			input = replaceKeysFromDictionary(input, dictionary);
		}
		return input;
	}

	private String replaceKeysFromDictionary(String input, Map<String, String> dictionary) {
		for (Entry<String, String> dictionaryEntry : dictionary.entrySet()) {
			input = input.replace("$" + dictionaryEntry.getKey() + "$", dictionaryEntry.getValue());
		}
		return input;
	}
}
