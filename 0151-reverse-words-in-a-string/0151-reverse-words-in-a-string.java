class Solution {
    public String reverseWords(String s) {
        List<String> wordList = new ArrayList<>();
        int length = s.length();
      
        for (int index = 0; index < length;) {
            // Skip leading spaces
            while (index < length && s.charAt(index) == ' ') {
                index++;
            }
            if (index < length) {
                // Build the current word
                StringBuilder currentWord = new StringBuilder();
                int wordStartIndex = index;
              
                // Keep appending characters until we hit a space or end of string
                while (wordStartIndex < length && s.charAt(wordStartIndex) != ' ') {
                    currentWord.append(s.charAt(wordStartIndex));
                    wordStartIndex++;
                }
              
                // Add the completed word to our list
                wordList.add(currentWord.toString());
              
                // Move the main index to where we stopped
                index = wordStartIndex;
            }
        }
      
        // Reverse the order of words in the list
        Collections.reverse(wordList);
      
        // Join all words with a single space and return
        return String.join(" ", wordList);
    }
}
