import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class WordGame {

    public static String reverse(String word) {
        StringBuilder builder = new StringBuilder();
        for (int i=word.length() - 1; i>=0; i--) {
            builder.append(word.charAt(i));
        }
        return builder.toString();
    }

    public static int countOccurrences(String line, String word1, String word2) {
        int result = 0;
        for (int i=0; i<line.length(); i++) {

            if (line.length() - i >= word1.length() && line.charAt(i) == word1.charAt(0)
                    && line.substring(i, i + word1.length()).equals(word1)) {
                result++;
            }

            if (line.length() - i >= word1.length() && line.charAt(i) == word2.charAt(0)
                    && line.substring(i, i+word2.length()).equals(word2)) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String word = line;
        line = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(line);
        String reversedWord = reverse(word);

        int m = Integer.parseInt(tokenizer.nextToken());
        int n = Integer.parseInt(tokenizer.nextToken());

        char[][] field = new char[n][m];

        for (int i=0; i<n; i++) {
            field[i] = scanner.nextLine().toCharArray();
        }
        int result = 0;

        for (int i=0; i<field.length; i++) {
            StringBuilder builder = new StringBuilder();
            result += countOccurrences(builder.append(field[i]).toString(), word, reversedWord);
        }

        for (int i=0; i<field[0].length; i++) {
            StringBuilder builder = new StringBuilder();
            for (int j=0; j<field.length; j++) {
                builder.append(field[j][i]);
            }
            result+=countOccurrences(builder.toString(), word, reversedWord);
        }

        for(int sum = 0; sum < m + n - 1;sum++){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i<m;i++){
                for(int j=0;j<n;j++){
                    if(i + j == sum){
                        builder.append(field[j][i]);
                    }
                }
            }result+=countOccurrences(builder.toString(), word, reversedWord);
        }

        for(int sum = -Math.min(m,n); sum<Math.max(m,n); sum++){
            StringBuilder builder = new StringBuilder();
            for(int i = 0;i<m;i++){
                for(int j = n-1; j>=0;j--){
                    if(i - j == sum){
                        builder.append(field[j][i]);
                    }
                }
            }result+=countOccurrences(builder.toString(), word, reversedWord);
        }
        System.out.println(result);
    }
}
