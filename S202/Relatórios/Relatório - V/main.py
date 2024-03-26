from database import Database
from model import Book

db = Database(database="library", collection="book")
db.resetDatabase()
book = Book(db)

id_book = book.create_book("Assasinato no expresso oriente", "Agatha Christie", 1920)

book.read_book_by_id(id_book)
book.update_book(id_book, "Assasinato no expresso oriente", "Agatha Christie" , 1930)
book.delete_book(id_book)