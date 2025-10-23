package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Iservice.IBookService;
import com.nt.entity.BookModule;
import com.nt.model.ResponseMessage;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class BooksController {

    private static final Logger log = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    private IBookService bookSer;

    @Operation(summary = "Create User Customers", description = "E-commerce online book store register the users")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User books saved successfully"),
        @ApiResponse(responseCode = "400", description = "User books saved failure"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/savebooks")
    public ResponseEntity<ResponseMessage> createBooks(@RequestBody BookModule bookModule) {
        log.info("Received request to save book: {}", bookModule);

        try {
            if (bookModule.getName() == null || bookModule.getName().isEmpty()
                    || bookModule.getTitle() == null || bookModule.getTitle().isEmpty()) {
                log.warn("Validation failed: Book name or title is empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST,
                                Constants.FAILED, "books name and title cannot be empty"));
            }

            BookModule savedBook = bookSer.customerSaveBooks(bookModule);
            if (savedBook != null) {
                log.info("Book saved successfully: {}", savedBook);
                return ResponseEntity.ok(
                        new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
                                "customer books saved successfully", savedBook));
            } else {
                log.error("Book save failed for: {}", bookModule);
                return ResponseEntity.ok(
                        new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
                                "customer books save failed", savedBook));
            }
        } catch (Exception e) {
            log.error("Exception occurred while saving book", e);
            return ResponseEntity.ok(
                    new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,
                            Constants.FAILED, "Internal server error"));
        }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<ResponseMessage> getAllBooks() {
        log.info("Fetching all books...");
        try {
            List<BookModule> allBooks = bookSer.customerGetAllBooks();
            if (allBooks != null && !allBooks.isEmpty()) {
                log.info("Fetched {} books successfully", allBooks.size());
                return ResponseEntity.ok(
                        new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
                                "All books fetched successfully", allBooks));
            } else {
                log.warn("No books found in database");
                return ResponseEntity.ok(
                        new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
                                "No books found", allBooks));
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching books", e);
            return ResponseEntity.ok(
                    new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,
                            Constants.FAILED, "Internal server error"));
        }
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<ResponseMessage> getBookById(@PathVariable Long id) {
        log.info("Fetching book by ID: {}", id);
        try {
            BookModule book = bookSer.getCustomerBookById(id);
            if (book != null) {
                log.info("Book found: {}", book);
                return ResponseEntity.ok(
                        new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
                                "Book found successfully", book));
            } else {
                log.warn("Book not found for ID: {}", id);
                return ResponseEntity.ok(
                        new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
                                "Book not found", null));
            }
        } catch (Exception e) {
            log.error("Exception occurred while fetching book by ID: {}", id, e);
            return ResponseEntity.ok(
                    new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR,
                            Constants.FAILED, "Internal server error"));
        }
    }
}
