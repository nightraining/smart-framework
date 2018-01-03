package org.smart4j.framework.mvc.dault;

/**
 * 上传异常（当文件上传失败时抛出）
 */
public class UploadException extends RuntimeException{

    public UploadException() {
        super();
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }
}
