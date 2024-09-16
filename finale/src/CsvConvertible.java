public interface CsvConvertible<T> {
    String toCsv(T object); // Convert an object to a CSV string
    T fromCsv(String csvLine); // Convert a CSV line to an object
}

