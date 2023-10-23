class Solution {
    public int solution(String name) {
        int UpDown = 0;
        int LeftRight = name.length() - 1; // 0 -> last

        for (int i = 0; i < name.length(); i++) {
            UpDown += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
            int endA = i + 1;
            while (endA < name.length() && name.charAt(endA) == 'A') {
                endA++;
            }
            LeftRight = Math.min(LeftRight, i * 2 + (name.length() - endA)); // 0 -> i -> 0 -> last -> endA
            LeftRight = Math.min(LeftRight, (name.length() - endA) * 2 + i); // 0 -> last -> endA -> last -> 0 -> i
        }
        return UpDown + LeftRight;
    }
}