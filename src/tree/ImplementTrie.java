package tree;

/**
 * 208. 实现 Trie (前缀树)
 * @author KyleWang
 * @version 1.0
 * @date 2019/11/04
 */
public class ImplementTrie {

    /*
    实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。

    示例:
    Trie trie = new Trie();
    trie.insert("apple");
    trie.search("apple");   // 返回 true
    trie.search("app");     // 返回 false
    trie.startsWith("app"); // 返回 true
    trie.insert("app");
    trie.search("app");     // 返回 true

    说明:
    你可以假设所有的输入都是由小写字母 a-z 构成的。
    保证所有输入均为非空字符串。
    */


    public static void main(String[] args) {
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println(obj.search("apple"));
        System.out.println(obj.search("app"));
        System.out.println(obj.startsWith("app"));
        obj.insert("app");
        System.out.println(obj.search("app"));
        obj.insert("apps");
        System.out.println(obj.search("app"));

    }
}


class Trie {

    boolean isLast;
    Trie[] children;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        children = new Trie[26];
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie current = this;
        for (char c : word.toCharArray()) {
            Trie next = current.children[getIndex(c)];
            if (next == null) {
                next = new Trie();
                current.children[getIndex(c)] = next;
            }
            current = next;
        }
        current.isLast = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie current = this;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            Trie next = current.children[getIndex(aChar)];
            if (next == null) {
                return false;
            }
            current = next;
        }
        return current.isLast;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie current = this;
        char[] chars = prefix.toCharArray();
        for (char aChar : chars) {
            Trie next = current.children[getIndex(aChar)];
            if (next == null) {
                return false;
            }
            current = next;
        }
        return true;
    }


    private int getIndex(char c) {
        return c - 'a';
    }
}
