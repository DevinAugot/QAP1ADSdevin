package DoublyLinkedList;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/** NOTE: I put an overabundance of comments in the code to help me logically understand what exactly is going on
 * in the program for later use, it also helped me work through the general logic i was trying to implement during
 * the project */

public class BrowserHistory {
    private class Node {
        private URL url;
        private Node previous;
        private Node next;

        public Node(URL url) {
            this.url = url;
        }
    }

    private Node currentUrl;
    private List<Node> history;

    public BrowserHistory() {
        history = new ArrayList<>(); // ArrayList to store all URLs , more realistic use in my opinion
    }

    public void visit(URL url) {
        Node newNode = new Node(url);

        if (currentUrl != null) {
            currentUrl.next = newNode;
            newNode.previous = currentUrl;
        }

        currentUrl = newNode;
        history.add(newNode);
    }

    public URL goBack() {
        if (currentUrl == null || currentUrl.previous == null) { // if there is no URL check current and previous
            System.out.println("No previous URL.");
            return null;
        }

        currentUrl = currentUrl.previous;
        return currentUrl.url;
    }

    public URL goForward() {
        if (currentUrl == null || currentUrl.next == null) { // if there is no URL check current and next
            System.out.println("No next URL.");
            return null;
        }

        currentUrl = currentUrl.next; // if there is a URl assign the next URL to the Current URL
        return currentUrl.url;
    }

    public static void main(String[] args) throws Exception {
        BrowserHistory browserHistory = new BrowserHistory();

        // URLs visited
        browserHistory.visit(new URL("https://www.bing.org"));
        browserHistory.visit(new URL("https://www.google.com"));
        browserHistory.visit(new URL("https://www.devintheprogrammer.me"));
        browserHistory.visit(new URL("https://www.apple.com"));

        // Print the current URL
        System.out.println("Current URL: " + browserHistory.currentUrl.url);

        // Go back and print the previous URL
        URL previousURL = browserHistory.goBack();
        System.out.println("Previous URL: " + previousURL);

        // Go forward and print the next URL
        URL nextURL = browserHistory.goForward();
        System.out.println("Next URL: " + nextURL + "\n");

        // Print the complete browser history
        System.out.println("Complete Browser History :");
        for (Node node : browserHistory.history) {
            System.out.println(node.url);
        }
    }
}
