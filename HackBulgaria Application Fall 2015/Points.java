import java.util.*;

import static java.util.Map.*;

public class Points {
    private static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void add(Point other) {
            this.x += other.x;
            this.y += other.y;
        }

        public void reverse() {
            this.x = - this.x;
            this.y = - this.y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    static Map<Character, Point> stringMap;

    static {
        stringMap = new HashMap<>();
        stringMap.put('<', new Point(-1, 0));
        stringMap.put('>', new Point(1, 0));
        stringMap.put('^', new Point(0, -1));
        stringMap.put('v', new Point(0, 1));
    }

    public static void reverseMap() {
        for (Iterator<Entry<Character, Point>> i = stringMap.entrySet().iterator(); i.hasNext(); ){
            Entry<Character, Point> currentEntry = i.next();
            currentEntry.getValue().reverse();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        Point start = new Point(startX, startY);
        line = scanner.nextLine();
        for (Character symbol: line.toCharArray()) {
            if (symbol == '~') {
                reverseMap();
                continue;
            }
            start.add(stringMap.get(symbol));
        }
        System.out.println(start);
    }
}
