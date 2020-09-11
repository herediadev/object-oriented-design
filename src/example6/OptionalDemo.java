package example6;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptionalDemo {

    private static abstract class Maybe<T> {

        public abstract <TResult> Maybe<TResult> map(Function<T, TResult> transform);

        public abstract <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform);

        public abstract T orElse(T value);

        public abstract boolean isPresent();

        public abstract T get();

    }

    private static class Some<T> extends Maybe<T> {

        private final T content;

        public Some(T content) {
            this.content = content;
        }

        @Override
        public <TResult> Maybe<TResult> map(Function<T, TResult> transform) {
            return new Some<>(transform.apply(this.content));
        }

        @Override
        public <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform) {
            return transform.apply(this.content);
        }

        @Override
        public T orElse(T substitute) {
            return this.content;
        }

        @Override
        public boolean isPresent() {
            return true;
        }

        @Override
        public T get() {
            return this.content;
        }
    }

    private static class None<T> extends Maybe<T> {

        @Override
        public <TResult> Maybe<TResult> map(Function<T, TResult> transform) {
            return new None<>();
        }

        @Override
        public <TResult> Maybe<TResult> flatMap(Function<T, Maybe<TResult>> transform) {
            return new None<>();
        }

        @Override
        public T orElse(T substitute) {
            return substitute;
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @Override
        public T get() {
            throw new IllegalStateException();
        }
    }

    private void display(Maybe<String> value) {
        System.out.println(value.map(String::toUpperCase).orElse("Nothing to show"));

        System.out.println(value.flatMap(valueParam-> new Some<>(valueParam).map(String::toLowerCase)).orElse("Nothing to show"));
    }

    private void displayAsSquare(Maybe<String> value){
        System.out.println(System.lineSeparator());
        this.display(this.toSquare(value));
    }

    private Maybe<String> toSquare(Maybe<String> value) {
        return value.flatMap(this::toSquare);
    }

    private Maybe<String> toSquare(String value) {
        return this.tryToSquare(value.length())
                .map(columns -> this.toMatrix(value,(int) Math.ceil(columns)));
    }

    private Maybe<Double> tryToSquare(int value) {
        return value < 0 ? new None<Double>()
                : new Some<>(Math.sqrt(value));
    }

    private String toMatrix(String value, int columns) {
        return this.toMatrix(value, columns, (value.length() + columns -1) / columns);
    }

    private String toMatrix(String value, int columns, int rows) {
        return IntStream.range(0,rows).map(row -> row * columns)
                .mapToObj(startIndex -> value.substring(startIndex,Math.min(startIndex + columns,value.length())))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private void run() {
        this.display(new None<>());
        this.display(new Some<>("Something"));
        this.display(new Some<>("Making your Java Code More Object-Oriented"));

        this.displayAsSquare(new Some<>("Making your Java Code More Object-Oriented"));
    }

    public static void main(String[] args) {
        new OptionalDemo().run();
    }
}
