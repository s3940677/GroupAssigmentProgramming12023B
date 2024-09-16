public interface ICrudOperations<T> {
    void create(T entity);        // Method to create an entity
    T read(String id);            // Method to read an entity by ID
    void update(T entity);        // Method to update an entity
    void delete(String id);       // Method to delete an entity (soft delete could be applied)
}
