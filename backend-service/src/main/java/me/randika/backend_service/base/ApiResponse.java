package me.randika.backend_service.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A standardized API response format for all controllers.
 * This class provides a consistent structure for all API responses,
 * including success and error responses.
 *
 * @param <T> The type of data contained in the response
 */
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private HttpStatus status;

    public ApiResponse() {
    }

    public ApiResponse(boolean success, String message, T data, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.status = status;
    }

    /**
     * Creates a success response with the provided data and message.
     *
     * @param data    The data to include in the response
     * @param message The success message
     * @param status  The HTTP status code
     * @param <T>     The type of data
     * @return A ResponseEntity containing the ApiResponse
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(true, message, data, status);
        return new ResponseEntity<>(response, status);
    }

    /**
     * Creates a success response with the provided data and default success message.
     *
     * @param data   The data to include in the response
     * @param status The HTTP status code
     * @param <T>    The type of data
     * @return A ResponseEntity containing the ApiResponse
     */
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, HttpStatus status) {
        return success(data, "Operation successful", status);
    }

    /**
     * Creates an error response with the provided message.
     *
     * @param message The error message
     * @param status  The HTTP status code
     * @param <T>     The type of data (null in error responses)
     * @return A ResponseEntity containing the ApiResponse
     */
    public static <T> ResponseEntity<ApiResponse<T>> error(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(false, message, null, status);
        return new ResponseEntity<>(response, status);
    }

    // Getters and setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}