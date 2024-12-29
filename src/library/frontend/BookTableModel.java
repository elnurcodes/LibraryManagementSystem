package library.frontend;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    private final List<library.backend.Book> books;
    private final String[] columnNames = {"Title", "Author", "ISBN", "Status"};

    public BookTableModel(List<library.backend.Book> books) {
        this.books = books;
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        library.backend.Book book = books.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> book.getTitle();
            case 1 -> book.getAuthor();
            case 2 -> book.getIsbn();
            case 3 -> book.isBorrowed() ? "Borrowed" : "Available";
            default -> null;
        };
    }
}
