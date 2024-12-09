package gregsjourney.utils;

public class StringUtil {
    public static String splitAtOccurrence(String input, String splitString, int occurrence, boolean returnBefore) {
        if (input == null || occurrence == 0) {
            throw new IllegalArgumentException("Invalid input or occurrence number.");
        }

        char splitChar = splitString.charAt(0);

        int index = -1;
        int occurrenceCount = 0;

        if (occurrence > 0) {
            // Loop forward to find the nth occurrence from the beginning
            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == splitChar) {
                    occurrenceCount++;
                    if (occurrenceCount == occurrence) {
                        index = i;
                        break;
                    }
                }
            }
        } else {
            // Loop backward to find the nth occurrence from the end (occurrence is negative)
            index = input.length();
            occurrence = -occurrence;  // Convert to positive for counting
            while (occurrenceCount < occurrence && index != -1) {
                index = input.lastIndexOf(splitChar, index - 1);
                occurrenceCount++;
            }
        }

        // If the occurrence isn't found, return the original string
        if (index == -1) {
            return input;
        }

        // If returnBefore is true, return the part before the found character
        if (returnBefore) {
            return input.substring(0, index);
        } else {
            // Otherwise, return the part after the found character
            return input.substring(index + 1);
        }
    }
}
