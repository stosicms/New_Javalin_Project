package app.util;

public class Path {
    public static class Web {
        public static final String BOOKS = "/books";
        public static final String ONE_BOOK = "/isbn/{isbn}";
        public static final String USERNAMES = "/usernames";
        public static final String USERNAME = "/username/{username}";
        public static final String ADD_BOOK = "/addBook/{title}, {author}, {isbn}";
    }
}
