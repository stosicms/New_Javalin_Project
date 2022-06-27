package app.book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.javalin.http.Context;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;


    public class BookController {

        static Gson gson = new Gson();

        private static <T> List<T> getListFromJson(String jsonArray, Class<T> clazz) {
            Type typeOfT = TypeToken.getParameterized(List.class, clazz).getType();
            return new Gson().fromJson(jsonArray, typeOfT);
        }

        static BookDao bookDao = new BookDao();
        public static void fetchAllBooks (Context ctx) {
            Map<String, Object> model = new HashMap<>();
            model.put("books", bookDao.getAllBooks());
            ctx.json(model);
        };

        public static void fetchOneBook (Context ctx ) {
            Map<String, Object> model = new HashMap<>();
            String isbn = ctx.pathParam("isbn");
            model.put("app/book", bookDao.getBookByIsbn(isbn));
            ctx.json(model);
        };

        public static void saveBook(Context ctx){
            Book requestBody = gson.fromJson(ctx.body(), Book.class);
            Book newBook = new Book(requestBody.title, requestBody.author, requestBody.isbn);
            bookDao.addBook(newBook);
            ctx.status(200);

        }
        public static void saveBooks(Context context) {
            List<Book> requestBody = getListFromJson(context.body(), Book.class);
            List<Book> newBooks = requestBody.stream().map(bookData -> new Book(bookData.title, bookData.author, bookData.isbn)).toList();
            bookDao.addBooks(newBooks);
            context.status(200);
        }
    }


