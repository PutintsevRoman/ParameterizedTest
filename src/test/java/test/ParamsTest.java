package test;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import pages.MainPage;
import java.util.stream.Stream;


public class ParamsTest extends TestBase {

    MainPage mainPage = new MainPage();

    @ParameterizedTest
    @ValueSource(strings = {"Git Pocket Guide", "Learning JavaScript Design Patterns"})
    public void firstTest(String book) {
        mainPage.openPage()
                .search(book)
                .checkBook(book);
    }

    /* @CsvSource(value = {
             "Git Pocket Guide, Richard E. Silverman",
             "Learning JavaScript Design Patterns, Addy Osmani"
     })*/
    @CsvFileSource(resources = "test_data/books.csv")
    @ParameterizedTest()
    public void secondTest(String searchData, String expectedResult) {
        mainPage.openPage()
                .search(searchData)
                .checkBook(expectedResult);
    }

    static Stream<Arguments> booksName() {
        return Stream.of(
                Arguments.of("Git Pocket Guide"),
                Arguments.of("Learning JavaScript Design Patterns")
        );
    }

    @MethodSource(value = "booksName")
    @ParameterizedTest()
    public void thirdTest(String searchData) {
        mainPage.openPage()
                .search(searchData)
                .checkBook(searchData);
    }

    @EnumSource(Authors.class)
    @ParameterizedTest
    void enumTest(Authors authors) {
        mainPage.openPage()
                .search(authors.desc)
                .checkBook(authors.desc);
    }

}
